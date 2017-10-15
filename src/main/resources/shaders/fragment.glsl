#version 330


uniform sampler2D diffuseTexture;

// Passed in from vertex shader
in vec4 Color;
in vec2 UV;
 
// Output color 
layout (location = 0) out vec4 FragColor;
 
void main(void) {
    // Assume only one of vertex color or texture was provided
    FragColor = Color + texture(diffuseTexture, UV);
}
