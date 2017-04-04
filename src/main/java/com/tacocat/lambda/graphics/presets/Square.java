package com.tacocat.lambda.graphics.presets;

import com.tacocat.lambda.graphics.Graphic;
import com.tacocat.lambda.graphics.Vertex;
import com.tacocat.lambda.graphics.VertexArrayObject;

import java.awt.Color;

/**
 * Common shape for quick prototyping
 */
public class Square implements Graphic {
    /**
     * Geometry for square
     */
    private VertexArrayObject vao;

    /**
     * Create a square with the default width and height
     * @param color
     */
    public Square(Color color) {
        createRenderObject(color, 50f, 50f);
    }

    /**
     * Create a white square
     * @param width
     * @param height
     */
    public Square(float width, float height) {
        createRenderObject(Color.WHITE, width, height);
    }

    /**
     * @param width
     * @param height
     * @param color
     */
    public Square(float width, float height, Color color) {
        createRenderObject(color, width, height);
    }

    /**
     * Build square geometry
     */
    private void createRenderObject(Color color, float width, float height) {
        float w = width / 2f;
        float h = height / 2f;

        // Create vertices
        Vertex v0 = new Vertex();
        v0.setXYZ(-w, h, 0);
        v0.setRGBA(color);
        Vertex v1 = new Vertex();
        v1.setXYZ(-w, -h, 0);
        v1.setRGBA(color);
        Vertex v2 = new Vertex();
        v2.setXYZ(w, -h, 0);
        v2.setRGBA(color);
        Vertex v3 = new Vertex();
        v3.setXYZ(w, h, 0);
        v3.setRGBA(color);

        Vertex[] vertices = new Vertex[]{v0, v1, v2, v3};

        // OpenGL expects to draw vertices in counter clockwise order by default
        byte[] indices = {
            // Left bottom triangle
            0, 1, 2,
            // Right top triangle
            2, 3, 0
        };

        vao = new VertexArrayObject(vertices, indices);
    }

    @Override
    public void render() {
        vao.render();
    }
}
