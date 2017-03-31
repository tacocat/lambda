/**
 * 
 */
package com.tacocat.lambda.common.render;

import com.tacocat.lambda.common.render.component.RenderItem;
import com.tacocat.lambda.common.render.component.Transform;
import com.tacocat.lambda.core.RenderQueue;
import com.tacocat.lambda.core.RenderQueueItem;
import com.tacocat.lambda.core.component.Component;
import com.tacocat.lambda.core.system.GameSystem;
import com.tacocat.lambda.graphics.Graphic;
import com.tacocat.lambda.graphics.math.TransformMatrix;

/**
 * System that handles rendering objects to the screen.
 * Each call of `update` will add all RenderItems to the given RenderQueue.
 */
public class Render extends GameSystem {
	
	/**
	 * Reference to queue to fill with items to render
	 */
	private RenderQueue renderQueue;
	
	/**
	 * @param renderQueue reference to queue to fill with items to render
	 */
	public Render(RenderQueue renderQueue) {
		this.renderQueue = renderQueue;
	}

	/* (non-Javadoc)
	 * @see com.tacocat.myengine.core.GameSystem#update(com.tacocat.myengine.core.ComponentStore)
	 */
	@Override
	protected void update() {
		findAll(RenderItem.class).forEach(component -> {
			// Setup transform data
			Component transformData = component.get(RenderItem.TRANSFORM);
			TransformMatrix transform = new TransformMatrix();
			transform.setTranslation(transformData.get(Transform.X), transformData.get(Transform.Y), transformData.get(Transform.Z));
			
			// Add item to RenderQueue
			Graphic graphic = component.get(RenderItem.GRAPHIC);
			renderQueue.add(new RenderQueueItem(transform, graphic));
		});

	}

}
