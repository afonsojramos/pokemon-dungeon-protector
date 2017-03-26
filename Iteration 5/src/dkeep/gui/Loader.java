package dkeep.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Loader {
	/**
	 * trtat do load das imagens necessarias
	 * @param path
	 * @return
	 */
	public static BufferedImage loadImage(String path){
		try {
			return ImageIO.read(Loader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
}
