/**
 * 
 */
package com.tacocat.lambda.core;

import org.junit.Assert;

import org.junit.Test;

import com.tacocat.lambda.core.RenderQueue;
import com.tacocat.lambda.core.RenderQueueItem;
import com.tacocat.lambda.graphics.Graphic;
import com.tacocat.lambda.graphics.math.TransformMatrix;

public class RenderQueueTest {
	
	private class TestGraphic implements Graphic {
		@Override
		public void render() {}
	}

	/**
	 * Test method for {@link com.tacocat.lambda.core.RenderQueue#forEach(java.util.function.BiConsumer)}.
	 */
	@Test
	public void testForEach() {
		TransformMatrix transform = new TransformMatrix();
		Graphic graphic = new TestGraphic();
		RenderQueueItem item = new RenderQueueItem(transform, graphic);
		RenderQueue renderQueue = new RenderQueue();
		renderQueue.add(item);
		
		renderQueue.forEach((givenTransform, givenGraphic) -> {
			Assert.assertEquals("forEach is given transform",
				transform,
				givenTransform
			);
			
			Assert.assertEquals("forEach is given graphic",
				graphic,
				givenGraphic
			);
		});
	}

}
