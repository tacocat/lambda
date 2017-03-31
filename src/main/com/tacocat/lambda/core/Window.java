/**
 * 
 */
package com.tacocat.lambda.core;

import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwGetKey;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.ByteBuffer;

import org.lwjgl.glfw.GLFWvidmode;

/**
 * TODO handle platforms
 */
public class Window {
    // The window handle
    private long window;
	
	public Window(int width, int height, String title) {
		init(width, height, title);	
	}
	
	private void init(int width, int height, String title) {
		// Initialize GLFW 
		if(glfwInit() != GL_TRUE){
			// Throw an error.
			System.err.println("GLFW initialization failed!");
		}
		
		// Allows our window to be resizable
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		
		// Create main window
		window = glfwCreateWindow(width, height, title, NULL, NULL);
	
		// Check if window was created
		if(window == NULL){
			System.err.println("Could not create our Window!");
		}
		
		// Get the resolution of the primary monitor
		ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		
		// Center window
		glfwSetWindowPos(
			window,
			(GLFWvidmode.width(vidmode) - width) / 2,
			(GLFWvidmode.height(vidmode) - height) / 2
		);
		
		// Set context current to this window
		glfwMakeContextCurrent(window);
		
		// Show the window
		glfwShowWindow(window);
		
		// vsync off
		// glfwSwapInterval(0);
	} 
	 
	public void pollForEvents() {
		// Polls for any window events such as the window closing etc.
		glfwPollEvents();
	}
	
	public boolean shouldClose() {
		return glfwWindowShouldClose(window) == GL_TRUE;
	}
	
	public long getHandle() {
		return window;
	}
	
	public boolean isKeyDown(int key){
        return glfwGetKey(window, key) == GLFW_PRESS;
    }
}
