/**
 * 
 */
package com.tacocat.lambda.core.component;

import org.junit.Assert;

import org.junit.Test;

import com.tacocat.lambda.core.component.DataField;

public class DataFieldTest {

	/**
	 * Test method for {@link com.tacocat.lambda.core.component.DataField#withDefaultValue(java.lang.Object)}.
	 */
	@Test
	public void testWithDefaultValue() {
		Float testValue = 23.4f;
		DataField<Float> dataField = DataField.withDefaultValue(testValue);
		
		Assert.assertEquals("Default value is set",
			testValue,
			dataField.defaultValue
		);
	}

}
