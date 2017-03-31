/**
 * 
 */
package com.tacocat.lambda.graphics;

/**
 * Class representing a single vertex
 */
public class Vertex {
	/**
	 * Position coordinates
	 */
	private float[] xyzw = new float[] {0f, 0f, 0f, 1f};
	
	/**
	 * Color data
	 */
	private float[] rgba = new float[] {1f, 1f, 1f, 1f};
	
	/**
	 * Texture coordinates
	 */
	private float[] uv = new float[] {0f, 0f};
	
	/**
	 * The amount of bytes an element has
	 */
	public static final int elementBytes = 4;
	
	/**
	 * Elements used in positioning
	 */
	public static final int positionElementCount = 4;
	
	/**
	 * Elements used in coloring
	 */
	public static final int colorElementCount = 4;
	
	/**
	 * Elements used in texturing
	 */
	public static final int textureElementCount = 2;
	
	/**
	 * Bytes used in positioning
	 */
	public static final int positionBytesCount = positionElementCount * elementBytes;
	
	/**
	 * Bytes used in coloring
	 */
	public static final int colorByteCount = colorElementCount * elementBytes;
	
	/**
	 * Bytes used in texturing
	 */
	public static final int textureByteCount = textureElementCount * elementBytes;
	
	/**
	 * Offset for start of position data
	 */
	public static final int positionByteOffset = 0;
	
	/**
	 * Offset for start of color data
	 */
	public static final int colorByteOffset = positionByteOffset + positionBytesCount;
	
	/**
	 * Offset for start of texture data
	 */
	public static final int textureByteOffset = colorByteOffset + colorByteCount;
	
	/**
	 * The amount of elements that a vertex has
	 */
	public static final int elementCount = positionElementCount + 
			colorElementCount + textureElementCount;
	
	/**
	 * The size of a vertex in bytes, like in C/C++: sizeof(Vertex)
	 */
	public static final int stride = positionBytesCount + colorByteCount + 
			textureByteCount;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public void setXYZ(float x, float y, float z) {
		this.setXYZW(x, y, z, 1f);
	}
	
	/**
	 * 
	 * @param r
	 * @param g
	 * @param b
	 */
	public void setRGB(float r, float g, float b) {
		this.setRGBA(r, g, b, 1f);
	}
	
	/**
	 * 
	 * @param u
	 * @param v
	 */
	public void setUV(float u, float v) {
		this.uv = new float[] {u, v};
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public void setXYZW(float x, float y, float z, float w) {
		this.xyzw = new float[] {x, y, z, w};
	}
	
	/**
	 * 
	 * @param r
	 * @param g
	 * @param b
	 * @param a
	 */
	public void setRGBA(float r, float g, float b, float a) {
		this.rgba = new float[] {r, g, b, a};
	}
	
	/**
	 * 
	 * @return an array packed with all vertex attributes
	 */
	public float[] getElements() {
		float[] out = new float[Vertex.elementCount];
		int i = 0;
		
		// Insert XYZW elements
		out[i++] = this.xyzw[0];
		out[i++] = this.xyzw[1];
		out[i++] = this.xyzw[2];
		out[i++] = this.xyzw[3];
		// Insert RGBA elements
		out[i++] = this.rgba[0];
		out[i++] = this.rgba[1];
		out[i++] = this.rgba[2];
		out[i++] = this.rgba[3];
		// Insert UV elements
		out[i++] = this.uv[0];
		out[i++] = this.uv[1];
		
		return out;
	}
	
	/**
	 * 
	 * @return
	 */
	public float[] getXYZW() {
		return new float[] {this.xyzw[0], this.xyzw[1], this.xyzw[2], this.xyzw[3]};
	}
	
	/**
	 * 
	 * @return
	 */
	public float[] getXYZ() {
		return new float[] {this.xyzw[0], this.xyzw[1], this.xyzw[2]};
	}
	
	/**
	 * 
	 * @return
	 */
	public float[] getRGBA() {
		return new float[] {this.rgba[0], this.rgba[1], this.rgba[2], this.rgba[3]};
	}
	
	/**
	 * 
	 * @return
	 */
	public float[] getRGB() {
		return new float[] {this.rgba[0], this.rgba[1], this.rgba[2]};
	}
	
	/**
	 * 
	 * @return
	 */
	public float[] getUV() {
		return new float[] {this.uv[0], this.uv[1]};
	}
}
