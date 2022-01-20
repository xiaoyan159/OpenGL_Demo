package com.xiaoyan.shadersample.utils;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Log;

import com.xiaoyan.shadersample.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.opengl.GLES20.GL_VALIDATE_STATUS;
import static android.opengl.GLES20.glGetProgramInfoLog;
import static android.opengl.GLES20.glGetProgramiv;
import static android.opengl.GLES20.glValidateProgram;

public class ShaderUtil {
    private static final String LOGName = "ShaderUtil";
    private static String readRawGLSL(Context mContext, int rawId) {
        InputStream inputStream = mContext.getResources().openRawResource(rawId);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer resultSB = new StringBuffer();
        String line;
        try {
            while ((line = bufferedReader.readLine())!=null) {
                resultSB.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultSB.toString();
    }

    private static int genShader(Context mContext, int shadeType, int shadeSourceId) {
        String shadeSource = readRawGLSL(mContext, shadeSourceId);
        if (shadeSource == null||shadeSource.trim().length()==0) {
            Log.e(LOGName, "给定的shader文件为空？：" );
            return 0;
        }
        int shader = GLES20.glCreateShader(shadeType); // 创建shader
        if (shader == 0) { // 如果shader返回0，说明创建shader失败
            Log.e(LOGName, GLES20.glGetShaderInfoLog(shader));
            GLES20.glDeleteShader(shader);
            return 0;
        }
        GLES20.glShaderSource(shader, shadeSource);
        GLES20.glCompileShader(shader);
        int[] compileResult = new int[1];
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compileResult, 0);
        if (compileResult[0] == 0) { // 编译失败
            Log.e(LOGName, "编译Shader失败：" + GLES20.glGetShaderInfoLog(shader));
            GLES20.glDeleteShader(shader); // 编译失败，删除申请的shader
        }
        return shader;
    }

    public static int createProgram(Context mContext, int vertexShaderId, int fragmentShaderId) {
        // 根据id获取对应的shader的glsl源码
        int vertexShader = genShader(mContext, GLES20.GL_VERTEX_SHADER, vertexShaderId);
        int fragmentShader = genShader(mContext, GLES20.GL_FRAGMENT_SHADER, fragmentShaderId);
        if (vertexShader == 0 || fragmentShader == 0) {
            return 0;
        }
        int program = GLES20.glCreateProgram(); // 创建program
        if (program == 0) {
            Log.e(LOGName, "创建Program失败");
            return 0;
        }
        GLES20.glAttachShader(program, vertexShader);
        checkGlError("glAttachShader");
        GLES20.glAttachShader(program, fragmentShader);
        checkGlError("glAttachShader");

        GLES20.glLinkProgram(program);
        // 检查linkProgram是否成功
        int[] linkResult = new int[1];
        GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkResult, 0);
        if (linkResult[0] == 0) {
            Log.e(LOGName, "链接Program失败:"+GLES20.glGetProgramInfoLog(program));
            return 0;
        }
        boolean isValidate = validateProgram(program);

        return program;
    }

    //检查每一步操作是否有错误的方法
    public static void checkGlError(String op) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR)
        {
            Log.e("ES20_ERROR", op + ": glError " + error);
            throw new RuntimeException(op + ": glError " + error);
        }
    }

    public static boolean validateProgram(int programObjectId) {
        glValidateProgram(programObjectId);
        final int[] validateStatus = new int[1];
        glGetProgramiv(programObjectId, GL_VALIDATE_STATUS, validateStatus, 0);

        Log.w(LOGName, "Result of validating program: " + validateStatus[0] + "\nLog:" + glGetProgramInfoLog(programObjectId));

        return validateStatus[0] != 0;

    }
}
