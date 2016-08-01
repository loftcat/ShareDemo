package com.loft.shareutil;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private ArrayList<View> pageViews = new ArrayList<>();

    ViewPagerAdapter(Context context, ArrayList<Item> items) {
        int size = items.size();
        int pageSize = (size / 8) + (((size % 8) == 0) ? 0 : 1);
        for (int i = 0; i < pageSize; i++) {
            GridView view = new GridView(context);
            int count = (size <= 8) ? size :
                    (((size - (i * 8)) >= 8) ? 8 : (size - (i * 8)));
            PageAdaper adapter = new PageAdaper(context, items.subList(i * 8, i * 8 + count));
            view.setAdapter(adapter);
            view.setNumColumns(4);
            view.setBackgroundColor(Color.TRANSPARENT);
            view.setHorizontalSpacing(1);
            view.setVerticalSpacing(1);
            view.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
            view.setCacheColorHint(0);
            view.setPadding(5, 0, 5, 0);
            view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            view.setGravity(Gravity.CENTER);
            pageViews.add(view);
        }
    }

    // 显示数目
    @Override
    public int getCount() {
        return pageViews.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public void destroyItem(ViewGroup arg0, int arg1, Object arg2) {
        arg0.removeView(pageViews.get(arg1));
    }

    @Override
    public Object instantiateItem(ViewGroup arg0, int arg1) {
        arg0.addView(pageViews.get(arg1));
        return pageViews.get(arg1);
    }


    private class PageAdaper extends BaseAdapter {

        private List<Item> items;
        private Context context;

        PageAdaper(Context context, List<Item> items) {
            this.context = context;
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = ((Activity) context).getLayoutInflater().inflate(R.layout.layout_share_item, null);
                viewHolder = new ViewHolder();
                viewHolder.tv_item = (TextView) convertView.findViewById(R.id.tv_item);
                viewHolder.iv_item = (ImageView) convertView.findViewById(R.id.iv_item);
                convertView.setTag(viewHolder);
            } else
                viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.tv_item.setText(items.get(position).getName());
            viewHolder.iv_item.setImageResource(items.get(position).getResid());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClick != null)
                        onItemClick.onItemClick(items.get(position));
                }
            });
            return convertView;
        }

        class ViewHolder {
            TextView tv_item;
            ImageView iv_item;
        }
    }

    private OnItemClick onItemClick;

    public OnItemClick getOnItemClick() {
        return onItemClick;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    interface OnItemClick {
        public abstract void onItemClick(Item item);
    }

}
