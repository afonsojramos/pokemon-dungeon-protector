package dkeep.gfx;

import java.awt.Graphics;


public abstract class State {

	private static State currentState = null;
	
	public static void setState(State state){
		currentState = state;
	}
	
	public static State getState(){
		return currentState;
	}
	
	//CLASS
	
	protected Game game;
	
	public State(Game game){
		this.game = game;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
}