package dkeep.gfx;

import java.io.IOException;

public class Launcher {
	public static void main(String[] args) throws IOException {
		Game game = new Game("Dungeon Protector", 600, 600);
		game.start();
		/*GameFrame window = new GameFrame();
		window.start();*/
	}
}