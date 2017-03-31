/**
 * 
 */
package com.tacocat.lambda.graphics;

import org.junit.Assert;
import org.junit.Test;

import com.tacocat.lambda.graphics.Vertex;

public class VertexTest {

	/**
	 * Test method for {@link com.tacocat.lambda.graphics.Vertex#getElements()}.
	 */
	@Test
	public void testGetElements() {
		Vertex vertex = new Vertex();
		vertex.setXYZW(1f, 2f, 3f, 4f);
		vertex.setRGBA(5f, 6f, 7f, 8f);
		vertex.setUV(9f, 10f);
		
		float[] expected = {1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f};
		
		Assert.assertArrayEquals("Vertex elements are packed in (position, color, uv) order",
			expected,
			vertex.getElements(),
			0f
		);
	}

}
