/**
 *
 */
package com.tacocat.lambda.input;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.*;

import com.tacocat.lambda.core.Window;
import com.tacocat.lambda.core.component.Component;

/**
 *
 */
public class KeyboardHandler extends GLFWKeyCallback {

	private final List<InputEvent> eventQueue;

	public KeyboardHandler(Window window) {
		eventQueue = new ArrayList<InputEvent>();

		// Link the KeyboardHandler with the given window
		GLFW.glfwSetKeyCallback(window.getHandle(), this);
	}

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		eventQueue.add(new InputEvent(key, action));
	}

	public List<InputEvent> getEventQueue() {
		return eventQueue;
	}

	public void clearEventQueue() {
		eventQueue.clear();
	}

}
