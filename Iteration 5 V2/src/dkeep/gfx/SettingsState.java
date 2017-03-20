package dkeep.gfx;

import java.awt.Graphics;

public class SettingsState extends State {

	public SettingsState(Game game){
		super(game);
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.grass, 0, 0, null);
	}

}
