#version 400
 
uniform mat4 translationMatrix;
uniform mat4 viewMatrix;
uniform mat4 projectionMatrix;
 
// Expected vertex structure
// 4 floats for position
// 4 floats for color
// 2 floats for uv coordinates 
layout (location = 0) in vec4 VertexPosition;
layout (location = 1) in vec4 VertexColor;
layout (location = 2) in vec2 VertexUV;

out vec4 Color;
out vec2 UV;

void main(void) {
    gl_Position = projectionMatrix * viewMatrix * translationMatrix * VertexPosition;
    //gl_Position = VertexPosition;
    
    Color = VertexColor;
    UV = VertexUV;
}