/**
 * 
 */
package com.tacocat.lambda.common.render.component;

import com.tacocat.lambda.core.component.DataField;

/**
 * Position data
 */
public class Transform {
	/**
	 * X position
	 */
	public static final DataField<Float> X = DataField.withDefaultValue(0f);
	
	/**
	 * Y position
	 */
	public static final DataField<Float> Y = DataField.withDefaultValue(0f);
	
	/**
	 * Z position
	 */
	public static final DataField<Float> Z = DataField.withDefaultValue(0f);
}
