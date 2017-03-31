/**
 * 
 */
package com.tacocat.lambda.common.simplephysics.component;

import com.tacocat.lambda.common.render.component.Transform;
import com.tacocat.lambda.core.component.Component;
import com.tacocat.lambda.core.component.DataField;

/**
 * A vector with a target
 */
public class Velocity extends Transform {
	/**
	 * Component to add (x, y, z) values to
	 */
	public static final DataField<Component> TARGET = DataField.withDefaultValue(new Component(Transform.class));
}
