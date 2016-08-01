package com.loft.sharedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.loft.shareutil.ShareUtil;
import com.loft.shareutil.ShareVo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.btn_share)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtil.getInstance(getApplicationContext()).show(MainActivity.this, new ShareVo("分享", "什么", "http://www.baidu.com", "http://d.hiphotos.baidu.com/image/h%3D200/sign=80d7f3fbc8fc1e17e2bf8b317a91f67c/6c224f4a20a44623ca89e18f9f22720e0df3d798.jpg", "wtf", 0));
            }
        });
    }
}
