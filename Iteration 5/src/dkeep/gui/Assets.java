package dkeep.gui;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage heroFrontStop, heroFrontWalk1, heroFrontWalk2, heroBackStop, heroBackWalk1, heroBackWalk2, 
								heroLeftStop, heroLeftWalk1, heroLeftWalk2, heroRightStop, heroRightWalk1, heroRightWalk2,
								heroFrontKey, heroBackKey, heroLeftKey, heroRightKey, heroFrontArmed, heroBackArmed, heroLeftArmed, heroRightArmed,
								heroFrontKeyArmed, heroBackKeyArmed, heroLeftKeyArmed, heroRightKeyArmed,
								guardFrontStop, guardFrontWalk1, guardFrontWalk2, guardBackStop, guardBackWalk1, guardBackWalk2,
								guardLeftStop, guardLeftWalk1, guardLeftWalk2, guardRightStop, guardRightWalk1, guardRightWalk2,
								ogreFrontStop, ogreFrontWalk1, ogreFrontWalk2, ogreBackStop, ogreBackWalk1, ogreBackWalk2,
								ogreLeftStop, ogreLeftWalk1, ogreLeftWalk2, ogreRightStop, ogreRightWalk1, ogreRightWalk2,
								ogreFrontKey, ogreBackKey, ogreLeftKey, ogreRightKey, ogres,
								clubFront, clubBack, clubLeft, clubRight, clubFrontKey, clubBackKey, clubLeftKey, clubRightKey,
								tree1, tree2, tree3, tree4, tree5, tree6, tree7, tree8,
								grass, door, key;
	private static final int yh = 32, xt = 88, yt = 96;
	public static void init(){
		SpriteSheet sheet = new SpriteSheet(Loader.loadImage("/heroSprite.png"));
		SpriteSheet sheet1 = new SpriteSheet(Loader.loadImage("/guardSprite.png"));
		SpriteSheet sheet2 = new SpriteSheet(Loader.loadImage("/treeSprite.png"));
		SpriteSheet sheet3 = new SpriteSheet(Loader.loadImage("/ogre.png"));
		SpriteSheet sheet4 = new SpriteSheet(Loader.loadImage("/club.png"));
		
		heroRightStop = sheet.crop(0 , 0 , yh, yh);
		heroRightWalk1 = sheet.crop(yh, 0 , yh, yh);
		heroRightWalk2 = sheet.crop(2*yh, 0 , yh, yh);
		heroRightKey = sheet.crop(3*yh, 0 , yh, yh);
		heroRightArmed = sheet.crop(4*yh, 0 , yh, yh);
		heroRightKeyArmed = sheet.crop(5*yh, 0 , yh, yh);
		heroBackStop = sheet.crop(0 , yh, yh, yh);
		heroBackWalk1 = sheet.crop(yh, yh, yh, yh);
		heroBackWalk2 = sheet.crop(2*yh, yh, yh, yh);
		heroBackKey = sheet.crop(3*yh, yh , yh, yh);
		heroBackArmed = sheet.crop(4*yh, yh , yh, yh);
		heroBackKeyArmed = sheet.crop(5*yh, yh , yh, yh);
		heroFrontStop = sheet.crop(0 , 2*yh, yh, yh);
		heroFrontWalk1 = sheet.crop(yh, 2*yh, yh, yh);
		heroFrontWalk2 = sheet.crop(2*yh, 2*yh, yh, yh);
		heroFrontKey = sheet.crop(3*yh, 2*yh , yh, yh);
		heroFrontArmed = sheet.crop(4*yh, 2*yh , yh, yh);
		heroFrontKeyArmed = sheet.crop(5*yh, 2*yh , yh, yh);
		heroLeftStop = sheet.crop(0 , 3*yh, yh, yh);
		heroLeftWalk1 = sheet.crop(yh, 3*yh, yh, yh);
		heroLeftWalk2 = sheet.crop(2*yh, 3*yh, yh, yh);
		heroLeftKey = sheet.crop(3*yh, 3*yh , yh, yh);
		heroLeftArmed = sheet.crop(4*yh, 3*yh , yh, yh);
		heroLeftKeyArmed = sheet.crop(5*yh, 3*yh , yh, yh);
		
		guardBackStop = sheet1.crop(0 , 0 , yh, yh);
		guardBackWalk1 = sheet1.crop(yh, 0 , yh, yh);
		guardBackWalk2 = sheet1.crop(2*yh, 0 , yh, yh);
		guardFrontStop = sheet1.crop(0 , yh, yh, yh);
		guardFrontWalk1 = sheet1.crop(yh, yh, yh, yh);
		guardFrontWalk2 = sheet1.crop(2*yh, yh, yh, yh);
		guardLeftStop = sheet1.crop(0 , 2*yh, yh, yh);
		guardLeftWalk1 = sheet1.crop(yh, 2*yh, yh, yh);
		guardLeftWalk2 = sheet1.crop(2*yh, 2*yh, yh, yh);
		guardRightStop = sheet1.crop(0 , 3*yh, yh, yh);
		guardRightWalk1 = sheet1.crop(yh, 3*yh, yh, yh);
		guardRightWalk2 = sheet1.crop(2*yh, 3*yh, yh, yh);
		
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
		ogreBackKey = sheet3.crop(3*yh , 0 , yh, yh);
		ogreFrontKey = sheet3.crop(3*yh , yh, yh, yh);
		ogreLeftKey = sheet3.crop(3*yh , 2*yh, yh, yh);
		ogreRightKey = sheet3.crop(3*yh , 3*yh, yh, yh);
		ogres = sheet3.crop(4*yh , 3*yh, yh, yh);
		
		clubBack = sheet4.crop(0 , 0 , yh, yh);
		clubFront = sheet4.crop(0 , yh, yh, yh);
		clubLeft = sheet4.crop(0 , 2*yh, yh, yh);
		clubRight = sheet4.crop(0 , 3*yh, yh, yh);
		clubBackKey = sheet4.crop(yh , 0 , yh, yh);
		clubFrontKey = sheet4.crop(yh , yh, yh, yh);
		clubLeftKey = sheet4.crop(yh , 2*yh, yh, yh);
		clubRightKey = sheet4.crop(yh , 3*yh, yh, yh);
		
		tree1 = sheet2.crop(0, 0, xt, yt);
		tree2 = sheet2.crop(0, yt, xt, yt);
		tree3 = sheet2.crop(0, 2*yt, xt, yt);
		tree4 = sheet2.crop(0, 3*yt, xt, yt);
		tree5 = sheet2.crop(xt, 0, xt, yt);
		tree6 = sheet2.crop(xt, yt, xt, yt);
		tree7 = sheet2.crop(xt, 2*yt, xt, yt);
		tree8 = sheet2.crop(xt, 3*yt, xt, yt);
		grass = Loader.loadImage("/grass.png");
		key = Loader.loadImage("/cut.png");
		door = Loader.loadImage("/door.png");
	}
	
	public static BufferedImage getTree(int treee){
		switch (treee){
			case 1: return tree1;
			case 2: return tree2;
			case 3: return tree3;
			case 4: return tree4;
			case 5: return tree5;
			case 6: return tree6;
			case 7: return tree7;
			case 8: return tree8;
			default: return tree1;
		}
	}
	
}
