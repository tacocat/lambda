/**
 * 
 */
package com.tacocat.lambda.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;

import com.tacocat.lambda.core.component.Component;
import com.tacocat.lambda.core.component.ComponentStore;
import com.tacocat.lambda.core.system.GameSystem;

/**
 * TODO handle platforms
 */
public class Game {
	private List<GameSystem> systems;
	private ComponentStore components;
	private GameEngine gameEngine;
	private RenderEngine renderEngine;
	private Window window;
	
	public Game(Window window, RenderEngine renderEngine) {
		systems = new ArrayList<GameSystem>();
		components = new ComponentStore();
		gameEngine = new GameEngine();
		
		this.renderEngine = renderEngine;
		this.window = window;
	}
	
	public void init(BiConsumer<List<GameSystem>, ComponentStore> initFunction) {
		initFunction.accept(systems, components);
	}
	
	public void useSystems(GameSystem ...newSystems) {
		Collections.addAll(systems, newSystems);
	}
	
	public void addComponent(Component component) {
		components.add(component);
	}
	
	public void run() {
		// Game loop
		while (!window.shouldClose()) {
			window.pollForEvents();

			gameEngine.update(systems, components);

			renderEngine.render(window);
		}
	}
}
