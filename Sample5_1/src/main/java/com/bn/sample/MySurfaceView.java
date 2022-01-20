package com.bn.sample;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MySurfaceView extends GLSurfaceView {
	public MySurfaceView(Context context) {
		super(context);
		this.setEGLContextClientVersion(2); //设置使用OPENGL ES2.0
		this.setRenderer(new MyRenderer5_1(context));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return true;
	}

	private class MyRenderer5_1 implements GLSurfaceView.Renderer {
		private Context mContext;

		public MyRenderer5_1(Context mContext) {
			this.mContext = mContext;
		}

		@Override
		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			// 清空缓冲区，深度缓冲和颜色缓冲区
			GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT|GLES20.GL_COLOR_BUFFER_BIT);
			// 通过glsl源码，获取program
			String vertexShaderSource = ShaderUtil.loadFromAssetsFile("", mContext.getResources());
			String fragmentShaderSource = ShaderUtil.loadFromAssetsFile("", mContext.getResources());
			int program = ShaderUtil.createProgram(vertexShaderSource, fragmentShaderSource);
			if (program!=0) {
				GLES20.glGetAttribLocation(program, "")
			}
		}

		@Override
		public void onSurfaceChanged(GL10 gl, int width, int height) {

		}

		@Override
		public void onDrawFrame(GL10 gl) {

		}
	}
}