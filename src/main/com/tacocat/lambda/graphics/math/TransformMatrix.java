/**
 * 
 */
package com.tacocat.lambda.graphics.math;

/**
 * Transform matrix containing translation, rotation, and scale data
 */
public class TransformMatrix {
	/**
	 * Translation matrix
	 */
	private Matrix4f translation;
	
	/**
	 * 
	 */
	public TransformMatrix() {
		translation = Matrix4f.newIdentity();
	}
	
	/**
	 * @param x translation on x axis
	 * @param y translation on y axis
	 * @param z translation on z axis
	 */
	public void setTranslation(float x, float y, float z) {
		translation = Matrix4f.newTranslation(x, y, z);
	}
	
	/**
	 * @return matrix for rendering
	 */
	public Matrix4f getModelMatrix() {
		return translation;
	}
}
