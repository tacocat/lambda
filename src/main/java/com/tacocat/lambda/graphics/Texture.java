/**
 * 
 */
package com.tacocat.lambda.graphics;


import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;

/**
 * Class representing an OpenGL texture
 */
public class Texture {

	/**
	 * OpenGL reference id
	 */
	private int id;
	
	/**
	 * Image representing texture
	 */
	private final Image image;
	
	/**
	 * @param filename image to use for texture
	 */
	public Texture(String filename){
		image = new Image(filename);
		
		// Generate ID
		id = GL11.glGenTextures();
		
		createTexture(id, image);
	}
	
	/**
	 * Creates an OpenGL texture based on a given Image
	 * @param id OpenGL reference id to create texture in
	 * @param image 
	 */
	private void createTexture(int id, Image image) {
		// Set as texture 0
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		
		bind();
		
		// Set pixel storage mode
		GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
		
		// Setup texture
		GL11.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		GL11.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		GL11.glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, image.width, image.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, image.buffer);
		GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
		
		unbind();
	}
	
	/**
	 * Start using the texture
	 */
	public void bind(){
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	/**
	 * Stop using the texture
	 */
	public void unbind(){
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	/**
	 * @return image width in pixels
	 */
	public int getWidth() {
		return image.width;
	}
	
	/**
	 * @return image height in pixels
	 */
	public int getHeight() {
		return image.height;
	}
}