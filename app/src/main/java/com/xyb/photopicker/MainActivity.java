package com.xyb.photopicker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;

public class MainActivity extends ToolBarActivity {

    @Bind(R.id.btn_select_pic)
    Button btnSelectPic;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("高仿微信朋友圈图片选择");

    }

    @Override
    protected void setListener() {
        btnSelectPic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        goToActivity(this,SelectPicActivity.class,null);
    }

}
