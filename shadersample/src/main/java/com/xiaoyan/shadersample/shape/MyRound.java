package com.xiaoyan.shadersample.shape;

import android.content.Context;
import android.opengl.GLES20;

import com.xiaoyan.shadersample.R;
import com.xiaoyan.shadersample.utils.ShaderUtil;
import com.xiaoyan.shadersample.utils.VertexArray;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
/**
 * 圆形
 * */
public class MyRound extends BaseShape{
    private float[] vertextData = new float[62*3]; // 每个坐标有3个分量
    private int program; // program的引用id

    private final String A_POSITION = "aPosition";
    private final String A_COLOR = "aColor";

    private int aPositionLocation, aColorPosition;

    public MyRound(Context context) {
        super(context);
        vertextData[0] = 0f;
        vertextData[1] = 0f;
        vertextData[2] = 0f;
        for (int i = 0; i < 61; i++) { // 初始化一个数组，包含一个中心点和60个弧度数据
            int position = i*3+3;
            vertextData[position] = 0.6f * (float) Math.cos(Math.toRadians(i*6));
            vertextData[position+1] = 0.6f * (float) Math.sin(Math.toRadians(i*6));
            vertextData[position+2] = 0;
        }
        program = ShaderUtil.createProgram(context, R.raw.base_vertex, R.raw.base_fragment);
        GLES20.glUseProgram(program);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // 构建一个圆的图案，第一个元素为圆心坐标，剩余60个点为组成圆的外环坐标
        vertexArray = new VertexArray(vertextData);
        // 从program中获取对应的字段
        aPositionLocation = GLES20.glGetAttribLocation(program, A_POSITION);
        aColorPosition = GLES20.glGetUniformLocation(program, A_COLOR);
        vertexArray.setVertexAttribPointer(0, aPositionLocation, 3, 0);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        super.onDrawFrame(gl);

        GLES20.glUniform4f(aColorPosition, 1f, 0f, 0f, 1f);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, vertextData.length/3);
    }

    @Override
    public void onDrawFrame(GL10 gl, float[] mvpMatrix) {
        super.onDrawFrame(gl, mvpMatrix);
    }

    @Override
    public void onSurfaceDestroyed() {

    }
}
