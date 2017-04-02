/**
 * 
 */
package com.tacocat.lambda.graphics;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import com.tacocat.lambda.graphics.math.Matrix4f;

/**
 * Class representing OpenGL shader program
 */
public class ShaderProgram {
	/**
	 * OpenGL program id
	 */
	public final int id;
	
	/**
	 * List of shaders being used in program
	 */
	private final Shader[] shaders;
	
	/**
	 * 
	 */
	private Map<String, Integer> uniforms = new HashMap<String, Integer>();
	
	/**
	 * @param shaders list of shaders to use in program
	 */
	public ShaderProgram(Shader ... shaders) {
		id = GL20.glCreateProgram();
		this.shaders = shaders;
		
		// Attach shaders
		for (Shader shader : shaders) {
			GL20.glAttachShader(id, shader.id);
		}
		
		// Link
		GL20.glLinkProgram(id);
		if (GL20.glGetProgrami(id, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
			throw new Error("Failed to link shader program.");
		}
			   
		// Validate
		GL20.glValidateProgram(id);
		if (GL20.glGetProgrami(id, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE) {
			throw new Error("Failed to validate shader program.");
		}
	}
	
	/**
	 * Activate this as the current program used by OpenGL
	 */
	public void use() {
		GL20.glUseProgram(id);
	}
	
	/**
	 * ???
	 * @param name
	 * @param location
	 */
	public void bindAttribute(String name, int location) {
		GL20.glBindAttribLocation(id, location, name);
	}
	
	/**
	 * Sets a named matrix variable in the shader
	 * @param name variable name in shader
	 * @param mat4 value to set
	 */
	public void setUniform(String name, Matrix4f mat4) {
		int location = getUniformLocation(name);
		GL20.glUniformMatrix4fv(location, true, mat4.asBuffer());
	}
	
	/**
	 * Cached lookup for uniform variable locations
	 * @param name variable name in shader
	 * @return location
	 */
	private int getUniformLocation(String name) {
		// Return location if it is already known
		if (uniforms.containsKey(name)) {
			return uniforms.get(name);
		} else {
			int location = GL20.glGetUniformLocation(id, name);
			uniforms.put(name, location);
			
			return location;
		}
	}
	
	/**
	 * Cleanup
	 */
	public void destroy() {
		GL20.glUseProgram(0);

		// Delete the shaders
		for (Shader shader : shaders) {
			GL20.glDetachShader(id, shader.id);
			shader.destroy();
		}

		// Delete the program
		GL20.glDeleteProgram(id);
	}
}
