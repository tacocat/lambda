/**
 * 
 */
package com.tacocat.lambda.graphics;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Helper methods for turning arrays into buffers for OpenGL
 */
public class Buffers {

	/**
	 * @param array
	 * @return ByteBuffer representation of array
	 */
	public static ByteBuffer createByteBuffer(byte[] array){
		ByteBuffer result = ByteBuffer.allocateDirect(array.length).order(ByteOrder.nativeOrder());
		result.put(array).flip();
		return result;
	}
	
	/**
	 * @param array
	 * @return FloatBuffer representation of array
	 */
	public static FloatBuffer createFloatBuffer(float[] array){
		FloatBuffer result = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
		result.put(array).flip();
		return result;
	}
	
	/**
	 * @param array
	 * @return IntBuffer representation of array
	 */
	public static IntBuffer createIntBuffer(int[] array){
		IntBuffer result = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
		result.put(array).flip();
		return result;
	}
}
