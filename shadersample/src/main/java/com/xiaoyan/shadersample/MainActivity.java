package com.xiaoyan.shadersample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnBaseShader; // 基础形状绘制

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        btnBaseShader.setOnClickListener(clickListener);
    }

    private void initViews() {
        btnBaseShader = findViewById(R.id.btn_base_sharde);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_base_sharde:
                    Intent baseShaderIntent = new Intent(MainActivity.this, BaseShadeActivity.class);
                    startActivity(baseShaderIntent);
                    break;
            }
        }
    };
}