/**
 * 
 */
package com.tacocat.lambda.graphics.math;

import java.nio.FloatBuffer;

import com.tacocat.lambda.graphics.Buffers;

/**
 * A 4x4 matrix
 */
public class Matrix4f {
	/**
	 * 1D representation of a 4x4 matrix where each 4 consecutive elements forms a row 
	 */
	private float[] matrix;
	
	/**
	 * Creates an identity matrix
	 */
	public Matrix4f() {
		setIdentity();
	}
	
	/**
	 * Build a matrix from the given array
	 * @param matrix 1D representation of a 4x4 matrix where each 4 consecutive elements forms a row
	 */
	private Matrix4f(float[] matrix) {
		this.matrix = matrix;
	}
	
	/**
	 * Converts to an identity matrix
	 */
	public void setIdentity() {
		matrix = new float[]{
			1, 0, 0, 0,
			0, 1, 0, 0,
			0, 0, 1, 0,
			0, 0, 0, 1
		};
	}
	
	/**
	 * @return new identity matrix
	 */
	public static Matrix4f newIdentity() {
		return new Matrix4f(new float[]{
			1, 0, 0, 0,
			0, 1, 0, 0,
			0, 0, 1, 0,
			0, 0, 0, 1
		});
	}
	
	/**
	 * @param x translation on x axis
	 * @param y translation on y axis
	 * @param z translation on z axis
	 * @return new translation matrix
	 */
	public static Matrix4f newTranslation(float x, float y, float z) {
		return new Matrix4f(new float[] {
			1, 0, 0, x,
			0, 1, 0, y,
			0, 0, 1, z,
			0, 0, 0, 1
		});
	}
	
	/**
	 * @param x scale on x axis
	 * @param y scale on y axis
	 * @param z scale on z axis
	 * @return new scale matrix
	 */
	public static Matrix4f newScale(float x, float y, float z) {
		return new Matrix4f(new float[] {
			x, 0, 0, 0,
			0, y, 0, 0,
			0, 0, z, 0,
			0, 0, 0, 1
		});
	}
	
	/**
	 * @param width horizontal size of viewport
	 * @param height vertical size of viewport
	 * @param fieldOfView angle in degrees
	 * @param near geometry closer than this value will be clipped
	 * @param far geometry farther away than this value will be clipped
	 * @return new projection matrix
	 */
	public static Matrix4f newProjection(float width, float height, float fieldOfView, float near, float far) {
		float aspectRatio = width / height;
		float yScale = (float) (1 / (Math.tan(Math.toRadians((double )fieldOfView / 2f))));
		float xScale = yScale / aspectRatio;
		float frustumLength = far - near;
		
		return new Matrix4f(new float[] {
			xScale, 0, 0, 0,
			0, yScale, 0, 0,
			0, 0, -((far + near) / frustumLength), -((2 * near * far) / frustumLength),
			0, 0, -1, 0
		});
	}
	
	/**
	 * 
	 * @return matrix represented as FloatBuffer
	 */
	public FloatBuffer asBuffer() {
		return Buffers.createFloatBuffer(matrix);
	}
}
