package dkeep.gui;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage sheet;
	/**
	 * vai buscar ficheriro de imagens
	 * @param sheet
	 */
	public SpriteSheet(BufferedImage sheet){
		this.sheet = sheet;
	}
	/**
	 * corta ficheiro de imagens nas coordenadas e com as dimensoes especificadas nos parametros
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public BufferedImage crop(int x, int y, int width, int height){
		return sheet.getSubimage(x, y, width, height);
	}
	
}