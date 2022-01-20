package com.xiaoyan.shadersample.shape;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;

import com.xiaoyan.shadersample.R;
import com.xiaoyan.shadersample.utils.ShaderUtil;
import com.xiaoyan.shadersample.utils.VertexArray;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_LINES;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glUniform3f;
import static android.opengl.GLES20.glUniform4f;
import static android.opengl.GLES20.glUniformMatrix4fv;

public class MyRect extends BaseShape {
    private int program;

    private float[] pointArray = {
            0.5f, 0f,
            0f, 1.0f,
            1.0f, 1.0f
    };

    private final int COMPONENT_COUNT = 2;
    private final String A_POSITION = "aPosition";
    private final String A_COLOR = "aColor";
    private final String U_PROJECT_MATRIX = "uProjectMatrix";
    private final String U_VIEW_MATRIX = "uViewMatrix";
    private final String U_MODEL_MATRIX = "uModelMatrix";

    private int aPositionLocaiton, aColorLocation;
    private int uProjectMatrixLocation, uViewMatrixLocation, uModelMatrixLocation;
    public MyRect(Context context) {
        super(context);

        // 获取program
        program = ShaderUtil.createProgram(mContext, R.raw.base_vertex, R.raw.base_fragment);
        GLES20.glUseProgram(program);
        vertexArray = new VertexArray(pointArray);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        aPositionLocaiton = GLES20.glGetAttribLocation(program, A_POSITION);
        aColorLocation = GLES20.glGetUniformLocation(program, A_COLOR);

//        uProjectMatrixLocation = GLES20.glGetUniformLocation(program, U_PROJECT_MATRIX);
//        uViewMatrixLocation = GLES20.glGetUniformLocation(program, U_VIEW_MATRIX);
//        uModelMatrixLocation = GLES20.glGetUniformLocation(program, U_MODEL_MATRIX);
        vertexArray.setVertexAttribPointer(0, aPositionLocaiton, COMPONENT_COUNT, 0);
//
//        // 初始化单位矩阵给model和view矩阵
//        Matrix.setIdentityM(modelMatrix, 0);
//        Matrix.setIdentityM(viewMatrix, 0);
//
//        Matrix.translateM(modelMatrix, 0, 0.2f, 0f, 0f);
//        Matrix.setLookAtM(viewMatrix, 0, 0, 0, 10, 0, 0, 5, 0, 1f, 5);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        float aspectRatio = width > height ? (float) width / (float) height : (float) height / (float) width;
//        Matrix.frustumM(projectionMatrix, 0, -1f, 1f, -1f, 1f, 2f, 10f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        super.onDrawFrame(gl);
        glUniform4f(aColorLocation, 0.0f, 0.0f, 1.0f, 0.5f);

//        // 使用矩阵平移，将坐标 x 轴平移 0.5 个单位
//        glUniformMatrix4fv(uModelMatrixLocation, 1, false, modelMatrix, 0);
//
//        glUniformMatrix4fv(uProjectMatrixLocation, 1, false, projectionMatrix, 0);
//
//        glUniformMatrix4fv(uViewMatrixLocation, 1, false, viewMatrix, 0);

        glDrawArrays(GL_TRIANGLES, 0, 3);
    }

    @Override
    public void onSurfaceDestroyed() {

    }

    public void drawSelf() {

    }
}
