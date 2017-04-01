/**
 * 
 */
package com.tacocat.lambda.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import com.tacocat.lambda.graphics.Graphic;
import com.tacocat.lambda.graphics.math.TransformMatrix;

/**
 * Queue of items to render
 */
public class RenderQueue {
	/**
	 * Internal item list
	 */
	private List<RenderQueueItem> queue;
	
	/**
	 * 
	 */
	public RenderQueue() {
		queue = new ArrayList<RenderQueueItem>();
	}
	
	/**
	 * @param item render item to add
	 */
	public void add(RenderQueueItem item) {
		queue.add(item);
	}
	
	/**
	 * Performs the given action for each element
	 * @param action
	 */
	public void forEach(BiConsumer<TransformMatrix, Graphic> action) {
		queue.forEach(item -> action.accept(item.transform, item.graphic));
	}
	
	/**
	 * Removes all elements
	 */
	public void clear() {
		queue.clear();
	}
}
