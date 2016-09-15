package com.xyb.photopicker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import butterknife.ButterKnife;

/**
 *
 * Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        initView();
        initData();
        setListener();
    }
    /**
     * 抽象方法，获取布局资源
     *
     * @return
     */
    protected abstract int getLayoutResId();
    /**
     * 初始化UI
     */
    protected abstract void initView();
    /**
     * 监听
     */
    protected abstract void setListener();
    /**
     * 初始化数据
     */
    protected void initData() {
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //在Action Bar的最左边，就是Home icon和标题的区域
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * 跳转activity
     */
    public void goToActivity(Context context, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        if (bundle == null) {
            bundle = new Bundle();
        }
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void goToActivityForResult(Context context, Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent(context, cls);
        if (bundle == null) {
            bundle = new Bundle();
        }
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}