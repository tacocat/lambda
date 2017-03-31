/**
 * 
 */
package com.tacocat.lambda.graphics;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/**
 * Class representing a renderable geometry
 * 
 * TODO destroy
 */
public class VertexArrayObject {
	/**
	 * Number of indices
	 */
	private final int indicesCount;
	
	/**
	 * OpenGL reference id for buffer holding index data 
	 */
	private final int indicesId;
	
	/**
	 * OpenGL reference id for vertex array
	 */
	private final int vaoId;
	
	/**
	 * OpenGL reference id for buffer holding vertex data
	 */
	private final int vboId;
	
	/**
	 * Builds renderable geometry from a list of vertices and indices.
	 * Each triplet of indices references three distinct vertices to form a triangle.
	 * 
	 * @param vertices ordered list of vertices
	 * @param indices ordered list of indices references vertices used for determining render order
	 */
	public VertexArrayObject(Vertex[] vertices, byte[] indices){
		indicesCount = indices.length;
		
		// Create a new Vertex Array Object in memory and select it (bind)
		vaoId = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vaoId);
		
		// Create a new Vertex Buffer Object in memory and select it (bind)
		vboId = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, bufferFromVertices(vertices), GL15.GL_STREAM_DRAW);
		
		// Put the position coordinates in attribute list 0
		GL20.glVertexAttribPointer(0, Vertex.positionElementCount, GL11.GL_FLOAT, 
				false, Vertex.stride, Vertex.positionByteOffset);
		// Put the color components in attribute list 1
		GL20.glVertexAttribPointer(1, Vertex.colorElementCount, GL11.GL_FLOAT, 
				false, Vertex.stride, Vertex.colorByteOffset);
		// Put the texture coordinates in attribute list 2
		GL20.glVertexAttribPointer(2, Vertex.textureElementCount, GL11.GL_FLOAT, 
				false, Vertex.stride, Vertex.textureByteOffset);
		
		// Deselect
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
		
		// Create a new VBO for the indices and select it (bind) - INDICES
		indicesId = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesId);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, Buffers.createByteBuffer(indices), GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	/**
	 * Combines the data from all vertices into a single FloatBuffer
	 * @param vertices array of vertices to convert to FloatBuffer
	 * @return FloatBuffer
	 */
	public FloatBuffer bufferFromVertices(Vertex[] vertices) {
		float[] vertexArray = new float[vertices.length * Vertex.elementCount];
		
		// Combine vertex arrays
		for (int i = 0; i < vertices.length; i++) {
			Vertex vertex = vertices[i];
			System.arraycopy(vertex.getElements(), 0, vertexArray, i * Vertex.elementCount, Vertex.elementCount);
		}
		
		return Buffers.createFloatBuffer(vertexArray);
	}
	
	/**
	 * Start using vertex array
	 */
	public void bind(){
		glBindVertexArray(vaoId);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesId);
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
	}
	
	/**
	 * Stop using vertex array
	 */
	public void unbind(){
		glBindVertexArray(0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}
	
	/**
	 * Draw vertex indices
	 */
	public void draw(){
		glDrawElements(GL_TRIANGLES, indicesCount, GL_UNSIGNED_BYTE, 0);
	}
	
	/**
	 * Draw object to the screen
	 */
	public void render(){
		bind();
		draw();
	}
}