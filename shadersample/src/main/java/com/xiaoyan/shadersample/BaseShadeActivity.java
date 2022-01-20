package com.xiaoyan.shadersample;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class BaseShadeActivity extends Activity {
    private GLSurfaceView baseGlSurfaceView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_base);
        baseGlSurfaceView = findViewById(R.id.bglv);
    }

    @Override
    protected void onPause() {
        super.onPause();
        baseGlSurfaceView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        baseGlSurfaceView.onResume();
    }
}
