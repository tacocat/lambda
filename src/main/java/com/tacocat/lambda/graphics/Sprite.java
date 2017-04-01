/**
 * 
 */
package com.tacocat.lambda.graphics;

/**
 * Basic 2D image graphic that can be drawn to the screen.
 * Supports sprite sheets, where multiple sprites of the same size are contained in the same image.
 */
public class Sprite implements Graphic {
	
	/**
	 * Geometry for sprite
	 */
	private VertexArrayObject vao;
	
	/**
	 * Image for sprite
	 */
	private Texture texture;
	
	/**
	 * Width of a tile in pixels
	 */
	private int tileWidth;
	
	/**
	 * Height of a tile in pixels
	 */
	private int tileHeight;
	
	/**
	 * Number of tiles horizontally in the sprite sheet
	 */
	private int tilesX;
	
	/**
	 * Number of tiles vertically in the sprite sheet
	 */
	private int tilesY;
		
	/**
	 * UV dimensions of a tile
	 */
	private float tileU;
	
	/**
	 * UV dimensions of a tile
	 */
	private float tileV;
	
	/**
	 * one indexed id of tile from sprite sheet currently being drawn
	 * TODO switch to 0 indexed
	 */
	private int tile;
	
	/**
	 * Builds a sprite from the given image file. Assumes there is only one tile.
	 * @param filename image to load
	 */
	public Sprite(String filename) {
		texture = new Texture(filename);
		setupTiles(texture.getWidth(), texture.getHeight());
		
		tile = 1;
		
		createRenderObject();
	}
	
	/**
	 * Builds a sprite from the given image file. Automatically sets up a sprite sheet based on the given tile size.
	 * If there is only one tile, the tile width and height should be the same as the image width and height.
	 * @param filename image to load
	 * @param newTileWidth horizontal size in pixels of a single tile
	 * @param newTileHeight vertical size in pixels of a single tile
	 */
	public Sprite(String filename, int newTileWidth, int newTileHeight) {
		texture = new Texture(filename);
		setupTiles(newTileWidth, newTileHeight);
		
		tile = 1;
		
		createRenderObject();
	}
	
	/**
	 * Build a square geometry to represent the sprite 
	 */
	private void createRenderObject() {
		// Get the u,v coordinates of the tile
		float u = getStartU(tile);
		float v = getStartV(tile);
		float w = tileWidth / 2f;
		float h = tileHeight / 2f;
		
		// Create vertices
		Vertex v0 = new Vertex(); 
		v0.setXYZ(-w, h, 0); v0.setUV(u, v);
		Vertex v1 = new Vertex(); 
		v1.setXYZ(-w, -h, 0); v1.setUV(u, v + tileV);
		Vertex v2 = new Vertex(); 
		v2.setXYZ(w, -h, 0); v2.setUV(u + tileU, v + tileV);
		Vertex v3 = new Vertex(); 
		v3.setXYZ(w, h, 0); v3.setUV(u + tileU, v);
		
		Vertex[] vertices = new Vertex[] {v0, v1, v2, v3};
		
        // OpenGL expects to draw vertices in counter clockwise order by default
        byte[] indices = {
                // Left bottom triangle
                0, 1, 2,
                // Right top triangle
                2, 3, 0
        };
        
        vao = new VertexArrayObject(vertices, indices);	
	}
	
	/**
	 * Sets up tileset data. Automatically handles textures that aren't a power of 2.
	 * If the tile size doesn't match the image size, a warning will be printed and the
	 * function will attempt to correct the tile size.
	 * @param newTileWidth horizontal size in pixels of a single tile
	 * @param newTileHeight vertical size in pixels of a single tile
	 */
	private void setupTiles(int newTileWidth, int newTileHeight) {
		int width = texture.getWidth();
		int height = texture.getHeight();
		
		// TODO redo this
		// Make sure at least one tile can be made
		if (width % newTileWidth != 0 || newTileWidth < 0) {
			System.out.println("Warning: Tile width invalid for image dimensions. Correcting width of " + newTileWidth + " to " + width );
			newTileWidth = texture.getWidth();
		}
		if (height % newTileHeight != 0 || newTileWidth < 0) {
			System.out.println("Warning: Tile height invalid for image dimensions. Correcting height of " + newTileHeight + " to " + height );
			newTileHeight = height;
		}
		
		// TODO figure this out
		// Account for texture sizes that aren't a power of 2
		float maxU = 1f; //(float) texture.getImageWidth() / texture.getTextureWidth();
		float maxV = 1f; //(float) texture.getImageHeight() / texture.getTextureHeight();
		
		// Setup tile structure
		tileWidth = newTileWidth;
		tileHeight = newTileHeight;
		tilesX = width / tileWidth;
		tilesY = height / tileHeight;
		tileU = maxU / tilesX;
		tileV = maxV / tilesY;
	}

	/* (non-Javadoc)
	 * @see com.tacocat.myengine.graphics.Graphic#render()
	 */
	@Override
	public void render() {
		texture.bind();
		vao.render();
	}
	
	/**
	 * @param tile tile to display (one indexed)
	 * @return starting U coordinate of the tile
	 */
	private float getStartU(int tile) {
		int posX = (tile - 1) % tilesX;
		return posX * tileU;
	}
	
	/**
	 * @param tile tile to display (one indexed)
	 * @return starting V coordinate of the tile
	 */
	private float getStartV(int tile) {
		int posY = (tile - 1) / tilesX;
		return posY * tileV;
	}

}
