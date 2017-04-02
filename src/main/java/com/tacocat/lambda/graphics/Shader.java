/**
 * 
 */
package com.tacocat.lambda.graphics;

import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glShaderSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

/**
 * Class representing a single GLSL shader
 */
public class Shader {
	/**
	 * OpenGL shader id
	 */
	public final int id;
	
	/**
	 * @param filename location for shader source code
	 * @param type GL20.GL_VERTEX_SHADER or GL20.GL_FRAGMENT_SHADER
	 */
	public Shader(String filename, int type) {
		// Load shader source as a string
		StringBuilder source = loadFromFile(filename);
		
		// Create and compile shader
	    id = glCreateShader(type);
	    glShaderSource(id, source);
	    glCompileShader(id);
	    
	    // Check for compilation errors
	    if (GL20.glGetShaderi(id, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
	    	System.err.println(GL20.glGetShaderInfoLog(id));
	    	throw new Error("Shader compilation failed: " + filename);
	    }
	}
	
	/**
	 * 
	 * @param filename location for shader source code
	 * @return StringBuilder containing entire shader source
	 */
	private StringBuilder loadFromFile(String filename) {
		StringBuilder shaderSource = new StringBuilder();
	     
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader(filename));
	        String line;
	        while ((line = reader.readLine()) != null) {
	            shaderSource.append(line).append("\n");
	        }
	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	        throw new Error("Could not read shader file:" + filename);
	    }
	    
	    return shaderSource;
	}
	
	/**
	 * Cleanup
	 */
	public void destroy() {
		glDeleteShader(id);
	}
}
