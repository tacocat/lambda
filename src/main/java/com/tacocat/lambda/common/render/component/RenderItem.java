/**
 * 
 */
package com.tacocat.lambda.common.render.component;

import com.tacocat.lambda.core.component.Component;
import com.tacocat.lambda.core.component.DataField;
import com.tacocat.lambda.graphics.Graphic;

/**
 * Data needed to render a graphic to the screen
 */
public class RenderItem {
	/**
	 * Graphic to render
	 */
	public static final DataField<Graphic> GRAPHIC = DataField.withDefaultValue(null);
	
	/**
	 * Position to render it
	 */
	public static final DataField<Component> TRANSFORM = DataField.withDefaultValue(new Component(Transform.class));
	
}
