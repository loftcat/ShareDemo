package com.loft.shareutil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import java.util.ArrayList;
import java.util.Timer;


public class ShareUtil {
    private static ShareUtil shareUtil;
    private Context context;
    private Tencent mTencent;

    private ShareUtil(Context context) {
        this.context = context;
        initOthers();
    }

    public static ShareUtil getInstance(Context context) {
        if (shareUtil == null)
            shareUtil = new ShareUtil(context);
        return shareUtil;
    }

    private PopupWindow menuPopupWindow;
    private ArrayList<Item> items = new ArrayList<>();


    public void initOthers() {
        items.add(new Item("其他", R.drawable.btn_more, Constants.Target.TARGET_TYPE_OTHERS));
    }

    public void initQQ(String appid) {
        mTencent = Tencent.createInstance(appid, context);
        items.add(0, new Item("QQ空间", R.drawable.share_logo_qzone, Constants.Target.TARGET_TYPE_QZONE));
        items.add(0, new Item("QQ", R.drawable.share_logo_qq, Constants.Target.TARGET_TYPE_QQ));
    }

    public void initWeixin() {
        items.add(0, new Item("朋友圈", R.drawable.share_logo_moments, Constants.Target.TARGET_TYPE_WEIXIN_MONENTS));
        items.add(0, new Item("微信", R.drawable.share_logo_weixin, Constants.Target.TARGET_TYPE_WEIXIN));
    }

    public void initSina_Weibo() {
        items.add(0, new Item("新浪微博", R.drawable.share_logo_sinaweibo, Constants.Target.TARGET_TYPE_SINA_WEIBO));
    }

    Timer timer;

    public void show(final Activity context, final ShareVo shareVo) {
        if (menuPopupWindow == null) {
            LayoutInflater tempInflater = LayoutInflater.from(context);
            View v = tempInflater.inflate(R.layout.layout_popup_share, null);
            ViewPager viewPager = (ViewPager) v.findViewById(R.id.vp_share);
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(context, items);
            viewPagerAdapter.setOnItemClick(new ViewPagerAdapter.OnItemClick() {
                @Override
                public void onItemClick(Item item) {
                    switch (item.getTarget()) {
                        case Constants.Target.TARGET_TYPE_QQ:
                            shareToQQ(context, shareVo);
                            break;
                        case Constants.Target.TARGET_TYPE_QZONE:
                            shareToQZone(context, shareVo);
                            break;
                        case Constants.Target.TARGET_TYPE_OTHERS:
                            hide();
                            shareToOthers(context, shareVo);
                            break;
                    }
                }
            });
            viewPager.setAdapter(viewPagerAdapter);
            menuPopupWindow = new PopupWindow(v, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            menuPopupWindow.setFocusable(true);
            menuPopupWindow.setAnimationStyle(R.style.albumbumpopwindow_anim_style);
            ColorDrawable dw = new ColorDrawable(ContextCompat.getColor(context, R.color.white));
            menuPopupWindow.setBackgroundDrawable(dw);
            menuPopupWindow.update();
            menuPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    final WindowManager.LayoutParams lp = context.getWindow().getAttributes();
                    lp.alpha = 1.0f;
                    context.getWindow().setAttributes(lp);
                    menuPopupWindow = null;
                }
            });
        }
        final WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = 0.4f;
        context.getWindow().setAttributes(lp);
        menuPopupWindow.showAtLocation(context.getWindow().getDecorView().getRootView(), Gravity.BOTTOM, 0, 0);
    }

    public void hide() {
        if (menuPopupWindow != null && menuPopupWindow.isShowing()) {
            menuPopupWindow.dismiss();
        }
    }

    Handler handler = new Handler();

    private void shareToQQ(final Context context, ShareVo shareVo) {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, shareVo.getTitle());
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, shareVo.getSummary());
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, shareVo.getUrl());
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, shareVo.getImgUrl());
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, shareVo.getAppName());
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, shareVo.getExt());
        mTencent.shareToQQ((Activity) context, params, new IUiListener() {
            @Override
            public void onComplete(Object o) {
                Toast.makeText(context, "分享成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }
        });
    }

    private void shareToQZone(final Context context, ShareVo shareVo) {
        final Bundle params = new Bundle();
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, shareVo.getTitle());//必填
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, shareVo.getSummary());//选填
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, shareVo.getUrl());//必填
        ArrayList<String> imgs = new ArrayList<>();
        imgs.add(shareVo.getImgUrl());
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imgs);
        mTencent.shareToQzone((Activity) context, params, new IUiListener() {
            @Override
            public void onComplete(Object o) {
                Toast.makeText((Activity) context, "分享成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }
        });
    }


    private void shareToOthers(final Context context, ShareVo shareVo) {
        Intent intent1 = IntentUtil.shareText(shareVo.getTitle() + ":\n" + shareVo.getSummary());
        context.startActivity(intent1);
    }

}



