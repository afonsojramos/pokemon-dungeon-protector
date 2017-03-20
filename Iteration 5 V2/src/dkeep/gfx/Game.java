package dkeep.gfx;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dkeep.logic.*;
import dkeep.logic.Guard.Personality;

public class Game implements Runnable{
	
	private Display display;
	private String title;
	private int width, height;
	private Thread thread;
	//private static GameMap game = null;
	private boolean running;
	
	private static GameMap game = null;
	private int level = 1;
	private int finalLevel = 3;
	private int nOgres = 1;
	private Personality guardPersonality = Personality.valueOf("Rookie");
	private boolean gameStarted = false;
	
	int x = -24, f = 0;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	private State gameState;
	private State menuState;
	private State settingsState;
	
	//Input
	private Kbd keyManager;
	
	public Game(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		keyManager = new Kbd();
	}
	
	private void initialize(){
		display = new Display (title,width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		gameState = new GameState(this);
		menuState = new GameState(this);
		settingsState = new GameState(this);
		State.setState(gameState);
	}
	
	private void update(){
		keyManager.update();
		if (State.getState() != null)
			State.getState().update();
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		if (State.getState() != null)
			State.getState().render(g);
		
		bs.show();
		g.dispose();
	}
	
	@Override
	public void run() {
		
		initialize();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				update();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
		
	}
	
	public synchronized void start(){

        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){

        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
