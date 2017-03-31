/**
 *
 */
package com.tacocat.lambda.core.component;

import org.junit.Assert;
import org.junit.Test;

import com.tacocat.lambda.core.component.Component;
import com.tacocat.lambda.core.component.DataField;

/**
 *
 */
public class ComponentTest {

	private static class Position {
		static final DataField<Float> X = DataField.withDefaultValue(3f);
		static final DataField<Float> Y = DataField.withDefaultValue(4f);
	}

	/**
	 * Test method for {@link com.tacocat.lambda.core.component.Component#get(com.tacocat.lambda.core.component.DataField)}.
	 */
	@Test
	public void testGet() {
		Component component = new Component(
			Position.class,
			Position.X, 50f
		);

		Assert.assertEquals("Default value is returned when data is not set",
			new Float(4f),
			component.get(Position.Y)
		);

		Assert.assertEquals("Set value is returned instead of default",
			new Float(50f),
			component.get(Position.X)
		);
	}

}
