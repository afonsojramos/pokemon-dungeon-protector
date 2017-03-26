package dkeep.gui;

import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;
/**
 * Assets.java - Class that saves the sprites
 */
public class Assets {
	private static final int yh = 32, xt = 88, yt = 96;
	private static int[][] Trees = new int[20][20];
	public static BufferedImage heroFrontStop,heroFrontKey, heroFrontArmed, heroFrontKeyArmed,
								guardFrontStop, guardSleep,
								ogreFrontStop, ogreFrontKey,  ogreStun, ogres,
								clubFront, clubFrontKey,
								tree1, tree2, tree3, tree4, tree5, tree6, tree7, tree8,
								grass, door, key, pokemon;
	public static ImageIcon icon;
	/**
	 * Initializes Sprites attribution
	 */
	public static void init(){
		try {
			setHero();
			setTrees();
			setOgre();
			setClub();
			setGuard();
			setOthers();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Get's the random tree that was attributed to that position in the map
	 * @param x
	 * @param y
	 * @return BufferedImage
	 */
	public static BufferedImage getTree(int x, int y){
		switch (Trees[y][x]){
			case 1: return tree1;					case 2: return tree2;					case 3: return tree3;
			case 4: return tree4;					case 5: return tree5;					case 6: return tree6;
			case 7: return tree7;					case 8: return tree8;					default: return tree1;}
	}
	/**
	 * Deals with hero sprites
	 */
	public static void setHero(){
		SpriteSheet sheet = new SpriteSheet(Loader.loadImage("/heroSprite.png"));
		heroFrontStop = sheet.crop(0 , 2*yh, yh, yh);
		heroFrontKey = sheet.crop(3*yh, 2*yh , yh, yh);
		heroFrontArmed = sheet.crop(4*yh, 2*yh , yh, yh);
		heroFrontKeyArmed = sheet.crop(5*yh, 2*yh , yh, yh);
	}
	/**
	 * Deals with guard sprites
	 */
	public static void setGuard(){
		SpriteSheet sheet = new SpriteSheet(Loader.loadImage("/guardSprite.png"));
		guardFrontStop = sheet.crop(0 , yh, yh, yh);
		guardSleep = sheet.crop(2*yh , yh, yh, yh);
	}
	/**
	 * Deals with ogre sprites
	 */
	public static void setOgre(){
		SpriteSheet sheet = new SpriteSheet(Loader.loadImage("/ogre.png"));
		ogreFrontStop = sheet.crop(0 , yh, yh, yh);
		ogreFrontKey = sheet.crop(3*yh , yh, yh, yh);
		ogreStun = sheet.crop(4*yh , yh, yh, yh);
		ogres = sheet.crop(4*yh , 0, yh, yh);
	}
	/**
	 * Deals with tree sprites while attributing random trees to each place
	 */
	public static void setTrees(){
		for (int i = 0 ; i < Trees.length ; i++){
			for (int j = 0 ; j < Trees[0].length ; j++){
				Trees[i][j] = ThreadLocalRandom.current().nextInt(1, 8);
		}}
		SpriteSheet sheet = new SpriteSheet(Loader.loadImage("/treeSprite.png"));
		tree1 = sheet.crop(0, 0, xt, yt);
		tree2 = sheet.crop(0, yt, xt, yt);
		tree3 = sheet.crop(0, 2*yt, xt, yt);
		tree4 = sheet.crop(0, 3*yt, xt, yt);
		tree5 = sheet.crop(xt, 0, xt, yt);
		tree6 = sheet.crop(xt, yt, xt, yt);
		tree7 = sheet.crop(xt, 2*yt, xt, yt);
		tree8 = sheet.crop(xt, 3*yt, xt, yt);
	}
	/**
	 * Deals with club sprites
	 */
	public static void setClub(){
		SpriteSheet sheet = new SpriteSheet(Loader.loadImage("/club.png"));
		clubFront = sheet.crop(0 , yh, yh, yh);
		clubFrontKey = sheet.crop(yh , yh, yh, yh);
	}
	/**
	 * Deals with other game sprites
	 */
	public static void setOthers(){
		grass = Loader.loadImage("/grass.png");
		key = Loader.loadImage("/cut.png");
		door = Loader.loadImage("/door.png");
		pokemon = Loader.loadImage("/Pokemon.png");
		icon = new ImageIcon("Utils/Example2.PNG");
	}
}
