/**
 * 
 */
package com.tacocat.lambda.common.render.behavior;

import org.junit.Assert;
import org.junit.Test;

import com.tacocat.lambda.common.render.behavior.Renderable;
import com.tacocat.lambda.common.render.component.RenderItem;
import com.tacocat.lambda.common.render.component.Transform;
import com.tacocat.lambda.core.component.Component;
import com.tacocat.lambda.core.component.ComponentStore;
import com.tacocat.lambda.core.entity.Entity;
import com.tacocat.lambda.graphics.Graphic;


public class RenderableTest {
	
	private class TestGraphic implements Graphic {
		@Override
		public void render() {}
	}

	/**
	 * Test method for {@link com.tacocat.lambda.common.render.behavior.Renderable#Renderable(com.tacocat.lambda.graphics.Graphic)}.
	 */
	@Test
	public void testRenderableGraphic() {
		TestGraphic graphic = new TestGraphic();
		Entity entity = new Entity(new Renderable(graphic));
		entity.registerWith(new ComponentStore());

		Component renderItem = entity.get(Renderable.RENDER_ITEM);
		Component transform = entity.get(Renderable.TRANSFORM);
		
		Assert.assertEquals("Entity has a RenderItem component with given graphic",
			graphic,
			renderItem.get(RenderItem.GRAPHIC)
		);
		
		Assert.assertEquals("Entity has a RenderItem component that references entity's transform component",
			transform,
			renderItem.get(RenderItem.TRANSFORM)
		);
		
		Float[] actualPosition = { transform.get(Transform.X), transform.get(Transform.Y), transform.get(Transform.Z)};
		Float[] expectedPosition = { 0f, 0f, 0f };
		Assert.assertArrayEquals("Entity has Transform component that defaults to (0, 0, 0)",
			expectedPosition,
			actualPosition
		);
	}

	/**
	 * Test method for {@link com.tacocat.lambda.common.render.behavior.Renderable#Renderable(com.tacocat.lambda.graphics.Graphic, float, float)}.
	 */
	@Test
	public void testRenderableGraphicFloatFloat() {
		TestGraphic graphic = new TestGraphic();
		Entity entity = new Entity(new Renderable(graphic, 12f, 34f));
		entity.registerWith(new ComponentStore());

		Component renderItem = entity.get(Renderable.RENDER_ITEM);
		Component transform = entity.get(Renderable.TRANSFORM);
		
		Assert.assertEquals("Entity has a RenderItem component with given graphic",
			graphic,
			renderItem.get(RenderItem.GRAPHIC)
		);
		
		Assert.assertEquals("Entity has a RenderItem component that references entity's transform component",
			transform,
			renderItem.get(RenderItem.TRANSFORM)
		);
		
		Float[] actualPosition = { transform.get(Transform.X), transform.get(Transform.Y), transform.get(Transform.Z)};
		Float[] expectedPosition = { 12f, 34f, 0f };
		Assert.assertArrayEquals("Entity has Transform component with given x, y and default z value",
			expectedPosition,
			actualPosition
		);
	}

}
