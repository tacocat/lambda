/**
 * 
 */
package com.tacocat.lambda.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.tacocat.lambda.common.render.component.Transform;
import com.tacocat.lambda.core.component.Component;
import com.tacocat.lambda.core.component.ComponentStore;
import com.tacocat.lambda.core.system.GameSystem;

public class GameEngineTest {
	
	// X + 1
	private class MoveSystem extends GameSystem {
		@Override
		protected void update() {
			findAll(Transform.class).forEach((transform) -> {
				set(transform, Transform.X, transform.get(Transform.X) + 1f);
			});
			
		}
	}
	
	// Destroy when X == 2
	private class DestroySystem extends GameSystem {
		@Override
		protected void update() {
			findAll(Transform.class).forEach((transform) -> {
				if (transform.get(Transform.X) == 2f) {
					destroy(transform);
				}
			});
		}
	}

	/**
	 * Test method for {@link com.tacocat.lambda.core.GameEngine#update(java.util.List, com.tacocat.lambda.core.component.ComponentStore)}.
	 */
	@Test
	public void testNoSystems() {
		Float originalXValue = 3f;
		ComponentStore components = new ComponentStore();
		Component transform = new Component(Transform.class, Transform.X, originalXValue);
		components.add(transform);
		List<GameSystem> emptyList = new ArrayList<GameSystem>();
		
		// Run update with no systems
		GameEngine engine = new GameEngine();
		engine.update(emptyList, components);
		
		Assert.assertEquals("Component remains unchanged when no systems run",
			transform.get(Transform.X),
			originalXValue
		);
	}
	
	/**
	 * Test method for {@link com.tacocat.lambda.core.GameEngine#update(java.util.List, com.tacocat.lambda.core.component.ComponentStore)}.
	 */
	@Test
	public void testUpdate() {
		ComponentStore components = new ComponentStore();
		Component transform = new Component(Transform.class, Transform.X, 1f);
		components.add(transform);
		List<GameSystem> systems = new ArrayList<GameSystem>();
		systems.add(new MoveSystem());
		systems.add(new DestroySystem());
		
		// First update
		GameEngine engine = new GameEngine();
		engine.update(systems, components);
		
		Assert.assertEquals("Move system adds 1 to X value",
			2f,
			(float) transform.get(Transform.X),
			0f
		);
		
		Assert.assertEquals("Destroy system does not remove component since X was 1 at the start of update",
			1,
			components.find(Transform.class).size()
		);
		
		// Second update
		engine.update(systems, components);
		
		Assert.assertEquals("Destroy system removes component since X was 2 at the start of update",
			0,
			components.find(Transform.class).size()
		);
		
		Assert.assertEquals("Move system updates component regardless of whether or not it was destroyed this update cycle",
			3f,
			(float) transform.get(Transform.X),
			0f
		);
	}

}
