package dkeep.gfx;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Kbd implements KeyListener{
	
	private boolean[] keys;
	public boolean up, down, left, right;
	public State GameState;
	
	public Kbd(){
		keys = new boolean[256];
	}
	
	public void update(){
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}