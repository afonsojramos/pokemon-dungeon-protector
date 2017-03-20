package dkeep.gfx;

import java.awt.Graphics;

public class MenuState extends State {

	public MenuState(){
		
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.grass, 0, 0, null);
	}

}
