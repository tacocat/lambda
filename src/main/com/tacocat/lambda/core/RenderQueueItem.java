/**
 * 
 */
package com.tacocat.lambda.core;

import com.tacocat.lambda.graphics.Graphic;
import com.tacocat.lambda.graphics.math.TransformMatrix;

/**
 * Information needed to render something to the screen
 */
public class RenderQueueItem {
	public final TransformMatrix transform;
	public final Graphic graphic;
	
	public RenderQueueItem(TransformMatrix transform, Graphic graphic) {
		this.transform = transform;
		this.graphic = graphic;
	}
}
