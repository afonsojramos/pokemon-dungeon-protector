package dkeep.gui;

import java.awt.image.BufferedImage;
/**
 * SpriteSheet.java - Class that deals with the sprite sheet's reading
 */
public class SpriteSheet {

	private BufferedImage sheet;
	/**
	 * Constructor for the spritesheet
	 * @param sheet
	 */
	public SpriteSheet(BufferedImage sheet){
		this.sheet = sheet;
	}
	/**
	 * Crops the sheet according to the provided pixel position and size
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return BufferedImage
	 */
	public BufferedImage crop(int x, int y, int width, int height){
		return sheet.getSubimage(x, y, width, height);
	}
	
}