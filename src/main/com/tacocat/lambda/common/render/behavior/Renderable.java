/**
 * 
 */
package com.tacocat.lambda.common.render.behavior;

import com.tacocat.lambda.common.render.component.RenderItem;
import com.tacocat.lambda.common.render.component.Transform;
import com.tacocat.lambda.core.component.Component;
import com.tacocat.lambda.core.entity.Behavior;
import com.tacocat.lambda.core.entity.NamedComponent;
import com.tacocat.lambda.graphics.Graphic;

/**
 * Provides an entity with all components needed for rendering 
 */
public class Renderable extends Behavior {
	/**
	 * RenderItem
	 */
	public static NamedComponent RENDER_ITEM = new NamedComponent();
	
	/**
	 * Transform
	 */
	public static NamedComponent TRANSFORM = new NamedComponent();
	
	/**
	 * @param graphic object to render
	 */
	public Renderable(Graphic graphic) {
		init(graphic, 0f, 0f, 0f);
	}
	
	/**
	 * @param graphic object to render
	 * @param x initial location
	 * @param y initial location
	 */
	public Renderable(Graphic graphic, float x, float y) {
		init(graphic, x, y, 0f);
	}
	
	/**
	 * @param graphic object to render
	 * @param x initial location
	 * @param y initial location
	 * @param z initial location
	 */
	private void init(Graphic graphic, float x, float y, float z) {
		Component transform = new Component(
			Transform.class,
			Transform.X, x,
			Transform.Y, y,
			Transform.Z, z
		);
		Component renderItem = new Component(
			RenderItem.class,
			RenderItem.GRAPHIC, graphic,
			RenderItem.TRANSFORM, transform
		);

		defineComponent(TRANSFORM, Transform.class, transform);
		defineComponent(RENDER_ITEM, RenderItem.class, renderItem);
	}
}
