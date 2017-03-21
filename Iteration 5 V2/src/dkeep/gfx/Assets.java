package dkeep.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage heroFrontStop, heroFrontWalk1, heroFrontWalk2, heroBackStop, heroBackWalk1, heroBackWalk2, 
								heroLeftStop, heroLeftWalk1, heroLeftWalk2, heroRightStop, heroRightWalk1, heroRightWalk2,
								guardFrontStop, guardFrontWalk1, guardFrontWalk2, guardBackStop, guardBackWalk1, guardBackWalk2,
								guardLeftStop, guardLeftWalk1, guardLeftWalk2, guardRightStop, guardRightWalk1, guardRightWalk2,
								ogreFrontStop, ogreFrontWalk1, ogreFrontWalk2, ogreBackStop, ogreBackWalk1, ogreBackWalk2,
								ogreLeftStop, ogreLeftWalk1, ogreLeftWalk2, ogreRightStop, ogreRightWalk1, ogreRightWalk2,
								clubFront, clubBack, clubLeft, clubRight,
								tree1, tree2, tree3, tree4, tree5, tree6, tree7, tree8,
								grass, door, key;
	private static final int xh = 24, yh = 32, xt = 88, yt = 96;
	public static void init(){
		SpriteSheet sheet = new SpriteSheet(Loader.loadImage("/heroSprite.png"));
		SpriteSheet sheet1 = new SpriteSheet(Loader.loadImage("/guardSprite.png"));
		SpriteSheet sheet2 = new SpriteSheet(Loader.loadImage("/treeSprite.png"));
		SpriteSheet sheet3 = new SpriteSheet(Loader.loadImage("/ogre.png"));
		SpriteSheet sheet4 = new SpriteSheet(Loader.loadImage("/club.png"));
		
		heroRightStop = sheet.crop(0 , 0 , xh, yh);
		heroRightWalk1 = sheet.crop(xh, 0 , xh, yh);
		heroRightWalk2 = sheet.crop(2*xh, 0 , xh, yh);
		heroBackStop = sheet.crop(0 , yh, xh, yh);
		heroBackWalk1 = sheet.crop(xh, yh, xh, yh);
		heroBackWalk2 = sheet.crop(2*xh, yh, xh, yh);
		heroFrontStop = sheet.crop(0 , 2*yh, xh, yh);
		heroFrontWalk1 = sheet.crop(xh, 2*yh, xh, yh);
		heroFrontWalk2 = sheet.crop(2*xh, 2*yh, xh, yh);
		heroLeftStop = sheet.crop(0 , 3*yh, xh, yh);
		heroLeftWalk1 = sheet.crop(xh, 3*yh, xh, yh);
		heroLeftWalk2 = sheet.crop(2*xh, 3*yh, xh, yh);
		
		guardBackStop = sheet1.crop(0 , 0 , xh, yh);
		guardBackWalk1 = sheet1.crop(xh, 0 , xh, yh);
		guardBackWalk2 = sheet1.crop(2*xh, 0 , xh, yh);
		guardFrontStop = sheet1.crop(0 , yh, xh, yh);
		guardFrontWalk1 = sheet1.crop(xh, yh, xh, yh);
		guardFrontWalk2 = sheet1.crop(2*xh, yh, xh, yh);
		guardLeftStop = sheet1.crop(0 , 2*yh, xh, yh);
		guardLeftWalk1 = sheet1.crop(xh, 2*yh, xh, yh);
		guardLeftWalk2 = sheet1.crop(2*xh, 2*yh, xh, yh);
		guardRightStop = sheet1.crop(0 , 3*yh, xh, yh);
		guardRightWalk1 = sheet1.crop(xh, 3*yh, xh, yh);
		guardRightWalk2 = sheet1.crop(2*xh, 3*yh, xh, yh);
		
		ogreBackStop = sheet3.crop(0 , 0 , yh, yh);
		ogreBackWalk1 = sheet3.crop(yh, 0 , yh, yh);
		ogreBackWalk2 = sheet3.crop(2*yh, 0 , yh, yh);
		ogreFrontStop = sheet3.crop(0 , yh, yh, yh);
		ogreFrontWalk1 = sheet3.crop(yh, yh, yh, yh);
		ogreFrontWalk2 = sheet3.crop(2*yh, yh, yh, yh);
		ogreLeftStop = sheet3.crop(0 , 2*yh, yh, yh);
		ogreLeftWalk1 = sheet3.crop(yh, 2*yh, yh, yh);
		ogreLeftWalk2 = sheet3.crop(2*yh, 2*yh, yh, yh);
		ogreRightStop = sheet3.crop(0 , 3*yh, yh, yh);
		ogreRightWalk1 = sheet3.crop(yh, 3*yh, yh, yh);
		ogreRightWalk2 = sheet3.crop(2*yh, 3*yh, yh, yh);
		
		clubBack = sheet4.crop(0 , 0 , yh, yh);
		clubFront = sheet4.crop(0 , yh, yh, yh);
		clubLeft = sheet4.crop(0 , 2*yh, yh, yh);
		clubRight = sheet4.crop(0 , 3*yh, yh, yh);
		
		tree1 = sheet2.crop(0, 0, xt, yt);
		tree2 = sheet2.crop(0, yt, xt, yt);
		tree3 = sheet2.crop(0, 2*yt, xt, yt);
		tree4 = sheet2.crop(0, 3*yt, xt, yt);
		tree5 = sheet2.crop(xt, 0, xt, yt);
		tree6 = sheet2.crop(xt, yt, xt, yt);
		tree7 = sheet2.crop(xt, 2*yt, xt, yt);
		tree8 = sheet2.crop(xt, 3*yt, xt, yt);
		grass = Loader.loadImage("/grass.png");
		key = Loader.loadImage("/key.png");
		door = Loader.loadImage("/door.png");
	}
	
}
