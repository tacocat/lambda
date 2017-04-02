/**
 * 
 */
package com.tacocat.lambda.graphics;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;

/**
 * An image designed for use as an OpenGL texture
 */
public class Image {
	/**
	 * Raw pixel data
	 */
	private final int[] data;
	
	/**
	 * Width in pixels
	 */
	public final int width;
	
	/**
	 * Height in pixels
	 */
	public final int height;
	
	/**
	 * Pixel data as a buffer
	 */
	public final IntBuffer buffer;
	
	/**
	 * @param filename - path to image to load
	 */
	public Image(String filename) {
		BufferedImage image = loadImageFile(filename);
		
		width = image.getWidth();
		height = image.getHeight();
		
		data = getPixelsFromImage(image);
		buffer = Buffers.createIntBuffer(data).asReadOnlyBuffer();
	}
	
	/**
	 * @param filename - path to image to load
	 * @return loaded BufferedImage
	 */
	private BufferedImage loadImageFile(String filename) {
		BufferedImage image = null;
		
		// Attempt to load image file
		try {
			image = ImageIO.read(new FileInputStream(filename));
		} catch (IOException e){
			e.printStackTrace();
			throw new Error("Could not load image: " + filename);
		}
		
		return image;
	}
	
	/**
	 * @param image - BufferedImage to get pixels from
	 * @return pixel data in abgr order
	 */
	private int[] getPixelsFromImage(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		
		// Grab raw pixel data
		int[] pixels = new int[width * height];
		image.getRGB(0, 0, width, height, pixels, 0, width);
		
		// Reorder argb into abgr
		int[] data = new int[width * height];
		for(int i = 0; i < width * height; i++){
			int a = (pixels[i] & 0xff000000) >> 24;
			int r = (pixels[i] & 0xff0000) >> 16;
			int g = (pixels[i] & 0xff00) >> 8;
			int b = (pixels[i] & 0xff);
			
			data[i] = a << 24 | b << 16 | g << 8 | r;
		}
		
		return data;
	}
}
