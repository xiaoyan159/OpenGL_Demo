package com.xiaoyan.shadersample.glview;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.xiaoyan.shadersample.R;
import com.xiaoyan.shadersample.shape.BaseShape;
import com.xiaoyan.shadersample.shape.MyRect;
import com.xiaoyan.shadersample.shape.MyRound;
import com.xiaoyan.shadersample.utils.ShaderUtil;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class BaseGLView extends GLSurfaceView {
    private AttributeSet attrs;
    public BaseGLView(Context context) {
        super(context);
        // 设置使用OpenGL2,必须调用在setRender前
        this.setEGLContextClientVersion(2);
        this.setRenderer(new BaseRender(context));
    }

    public BaseGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 设置使用OpenGL2,必须调用在setRender前
        this.setEGLContextClientVersion(2);
        this.setRenderer(new BaseRender(context));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private class BaseRender implements Renderer {
        private Context mContext;
        private MyRound myRect;

        public BaseRender(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            GLES20.glClearColor(0,0,0,1);
            myRect = new MyRound(mContext);
            myRect.onSurfaceCreated(gl, config);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            // 设置屏幕的长宽比
            myRect.onSurfaceChanged(gl, width, height);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            // 绘制每一帧
            myRect.onDrawFrame(gl);
        }
    }
}
