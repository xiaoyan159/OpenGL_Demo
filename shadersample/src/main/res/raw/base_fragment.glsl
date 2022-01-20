precision mediump float; // 使用中等精度
uniform vec4 aColor; // 顶点着色器的颜色传递给片段着色器
void main() {
    gl_FragColor = aColor;
}
