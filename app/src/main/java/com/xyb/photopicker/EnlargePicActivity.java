package com.xyb.photopicker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 放大图片
 */
public class EnlargePicActivity extends ToolBarActivity {

    PhotoViewAttacher attacher;
    @Bind(R.id.iv_pic)
    ImageView ivPic;
    private ArrayList<String> imgPaths;
    private int position;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_enlarge_pic;
    }

    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            imgPaths = bundle.getStringArrayList("imgPaths");
            position=bundle.getInt("position");
        }
        toolbar.setTitle((position+1)+"/"+imgPaths.size());
        attacher = new PhotoViewAttacher(ivPic);
        Glide.with(this).load(imgPaths.get(position)).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                ivPic.setImageBitmap(resource);
                attacher.update();
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                ivPic.setImageDrawable(errorDrawable);
            }
        });

        attacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                hideOrShowToolBar();
            }

            @Override
            public void onOutsidePhotoTap() {
                hideOrShowToolBar();
            }
        });
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pic_delete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                setResult(RESULT_OK);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        attacher.cleanup();
        ButterKnife.unbind(this);
    }

}