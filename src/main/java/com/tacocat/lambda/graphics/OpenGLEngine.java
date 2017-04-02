/**
 * 
 */
package com.tacocat.lambda.graphics;

import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL;

import com.tacocat.lambda.core.RenderEngine;
import com.tacocat.lambda.core.RenderQueue;
import com.tacocat.lambda.core.Window;
import com.tacocat.lambda.graphics.math.Matrix4f;
import com.tacocat.lambda.util.Timer;

/**
 * RenderEngine implementation using LWJGL OpenGl bindings
 */
public class OpenGLEngine extends RenderEngine {
	
	/**
	 * Ticks every second
	 */
	private Timer fpsTimer = new Timer(1000);
	
	/**
	 * Frames rendered this second
	 */
	private int frameCount = 0;
	
	/**
	 * Last known fps count
	 */
	private int fps; 
	
	// TODO shader support
	private ShaderProgram shader;
	
	/**
	 * Queue to render
	 */
	private RenderQueue renderQueue;

	public OpenGLEngine() {
		// Set OpenGL context
		GL.createCapabilities();
	 
		// Set the clear color
		glClearColor(0.0f, 0.4f, 0.8f, 0.0f);
		
		// Enable transparent textures
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND); 
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		renderQueue = new RenderQueue();
        
		// TODO shader support
        shader = new ShaderProgram(
			new Shader("shaders/vertex.glsl", GL20.GL_VERTEX_SHADER),
			new Shader("shaders/fragment.glsl", GL20.GL_FRAGMENT_SHADER)
		);
	}

	/* (non-Javadoc)
	 * @see com.tacocat.myengine.core.Renderer#render()
	 */
	@Override
	public void render(Window window) {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		drawGraphics();
		
		glfwSwapBuffers(window.getHandle());
		
		// FPS
		frameCount++;
		if (fpsTimer.ready()) {
			fps = frameCount;
			frameCount = 0;
		}	
	}
	
	/**
	 * Draws all items currently in render queue
	 */
	private void drawGraphics() {
		// TODO dynamic shaders
        shader.use();

        // TODO dynamic camera
        shader.setUniform("viewMatrix", Matrix4f.newTranslation(0f, 0f, -416f));
        shader.setUniform("projectionMatrix", Matrix4f.newProjection(640, 480, 60f, 0.1f, 1000f));
        
        // Render each item
        renderQueue.forEach((transform, graphic) -> {
        	shader.setUniform("translationMatrix", transform.getModelMatrix());
        	graphic.render();
        });
        
        renderQueue.clear();
	}

	/* (non-Javadoc)
	 * @see com.tacocat.myengine.core.Renderer#getRenderqueue()
	 */
	@Override
	public RenderQueue getRenderQueue() {
		return renderQueue;
	}

}
