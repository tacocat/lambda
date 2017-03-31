/**
 * 
 */
package com.tacocat.lambda.core.component;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.tacocat.lambda.core.component.Data;
import com.tacocat.lambda.core.component.DataField;


public class DataTest {
	@Rule
    public ExpectedException thrown = ExpectedException.none();

	/**
	 * Test method for {@link com.tacocat.lambda.core.component.Data#Data(java.lang.Object[])}.
	 */
	@Test
	public void testDataBadArguments() {
		thrown.expect(Error.class);
		thrown.expectMessage("Always provide arguments in (field, value) pairs");
		new Data(123);
	}
	
	/**
	 * Test method for {@link com.tacocat.lambda.core.component.Data#Data(java.lang.Object[])}.
	 */
	@Test
	public void testDataBadOrder() {
		DataField<Float> x = new DataField<Float>();
		DataField<Float> y = new DataField<Float>();
		
		thrown.expect(Error.class);
		thrown.expectMessage("DataField must come before value");
		new Data(x, 3f, 4f, y);
	}

	/**
	 * Test method for {@link com.tacocat.lambda.core.component.Data#modify(java.lang.Object[])}.
	 */
	@Test
	public void testModify() {
		DataField<Float> x = new DataField<Float>();
		DataField<Float> y = new DataField<Float>();
		Data original = new Data(
			x, 3f,
			y, 4f
		);
		
		Data modified = original.modify(x, 1f);
		
		Assert.assertNotEquals("New data is returned",
			original,
			modified
		);
		
		Assert.assertEquals("New data has modified value",
			new Float(1f),
			modified.get(x)
		);
		
		Assert.assertEquals("Original data is not modified",
			new Float(3f),
			original.get(x)
		);
	}

	/**
	 * Test method for {@link com.tacocat.lambda.core.component.Data#get(com.tacocat.lambda.core.component.DataField)}.
	 */
	@Test
	public void testGet() {
		DataField<Float> x = DataField.withDefaultValue(3f);
		DataField<Float> y = DataField.withDefaultValue(99f);
		DataField<Float> z = new DataField<Float>();
		Data data = new Data(y, 4f);
		
		Assert.assertEquals("Default value is returned when data is not set",
			new Float(3f),
			data.get(x)
		);
		
		Assert.assertEquals("Set value is returned instead of default",
			new Float(4f),
			data.get(y)
		);
		
		Assert.assertNull("Null is returned when value is not set and there is no default",
			data.get(z)
		);
	}

}
