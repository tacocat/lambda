/**
 * 
 */
package com.tacocat.lambda.core.entity;

import org.junit.Assert;

import org.junit.Test;

import com.tacocat.lambda.core.component.Component;
import com.tacocat.lambda.core.component.DataField;
import com.tacocat.lambda.core.entity.Behavior;
import com.tacocat.lambda.core.entity.Entity;
import com.tacocat.lambda.core.entity.NamedComponent;


public class BehaviorTest {

	private static class Position {
		static final DataField<Float> X = DataField.withDefaultValue(3f);
	}

	/**
	 * Test method for {@link com.tacocat.lambda.core.entity.Behavior#defineComponent(com.tacocat.lambda.core.entity.NamedComponent, java.lang.Class, com.tacocat.lambda.core.component.Component)}.
	 */
	@Test
	public void testDefineComponent() {
		NamedComponent center = new NamedComponent();
		Component position = new Component(Position.class);
		Behavior centered = new Behavior();
		Entity entity = new Entity(centered);
		
		centered.defineComponent(center, Position.class, position);
		centered.registerWith(entity);
		
		Assert.assertEquals("Components defined in behavior have their parent entity set",
			position.getParent(),
			entity
		);
	}

	/**
	 * Test method for {@link com.tacocat.lambda.core.entity.Behavior#defineComponent(com.tacocat.lambda.core.entity.NamedComponent, java.lang.Class, java.util.function.Function)}.
	 */
	@Test
	public void testDefineComponentWithCallback() {
		NamedComponent center = new NamedComponent();
		Behavior centered = new Behavior();
		Entity entity = new Entity(centered);
		Float newX = 25f;
		
		centered.defineComponent(center, Position.class, (callbackEntity) -> {
			Assert.assertEquals("Definition callback is given entity reference",
				entity,
				callbackEntity
			);
						
			return new Component(Position.class, Position.X, newX);
		});
		centered.registerWith(entity);
		
		Component position = centered.getComponents().get(center);
		
		Assert.assertEquals("Components defined with callback have their parent entity set",
			entity,
			position.getParent()
		);
		
		Assert.assertEquals("Values returned from callback are set in component",
			newX,
			position.get(Position.X)
		);
	}

}
