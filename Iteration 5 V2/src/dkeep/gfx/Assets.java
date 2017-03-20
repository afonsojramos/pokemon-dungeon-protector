package dkeep.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage heroFrontStop, heroFrontWalk1, heroFrontWalk2, heroBackStop, heroBackWalk1, heroBackWalk2, 
								heroLeftStop, heroLeftWalk1, heroLeftWalk2, heroRightStop, heroRightWalk1, heroRightWalk2,
								tree1, tree2, tree3, tree4, tree5, tree6, tree7, tree8,
								grass;
	private static final int xh = 24, yh = 32, xt = 88, yt = 96;
	public static void init(){
		SpriteSheet sheet = new SpriteSheet(Loader.loadImage("/heroSprite.png"));
		SpriteSheet sheet2 = new SpriteSheet(Loader.loadImage("/treeSprite.png"));
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
		tree1 = sheet2.crop(0, 0, xt, yt);
		tree2 = sheet2.crop(0, yt, xt, yt);
		tree3 = sheet2.crop(0, 2*yt, xt, yt);
		tree4 = sheet2.crop(0, 3*yt, xt, yt);
		tree5 = sheet2.crop(xt, 0, xt, yt);
		tree6 = sheet2.crop(xt, yt, xt, yt);
		tree7 = sheet2.crop(xt, 2*yt, xt, yt);
		tree8 = sheet2.crop(xt, 3*yt, xt, yt);
		grass = Loader.loadImage("/grass.png");
	}
	
}
