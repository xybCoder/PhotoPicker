package com.xyb.photopicker;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.xyb.photopicker.adapter.PhotoPickerAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import me.iwf.photopicker.PhotoPicker;

public class SelectPicActivity extends ToolBarActivity {

    @Bind(R.id.gridView)
    GridView gridView;
    private PhotoPickerAdapter adapter;
    private ArrayList<String> imgPaths = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_select_pic;
    }

    @Override
    protected void initView() {
        adapter = new PhotoPickerAdapter(imgPaths);
        gridView.setAdapter(adapter);
    }

    @Override
    protected void setListener() {

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == imgPaths.size()) {
                    PhotoPicker.builder()
                            .setPhotoCount(9)
                            .setShowCamera(true)
                            .setSelected(imgPaths)
                            .setShowGif(true)
                            .setPreviewEnabled(true)
                            .start(SelectPicActivity.this, PhotoPicker.REQUEST_CODE);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("imgPath", imgPaths.get(position));
                    goToActivityForResult(SelectPicActivity.this, EnlargePicActivity.class, bundle, position);
                }

            }
        });

    }

    @Override
    public void onClick(View v) {

    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                imgPaths.clear();
                ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                imgPaths.addAll(photos);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
