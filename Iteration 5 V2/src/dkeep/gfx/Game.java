package dkeep.gfx;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dkeep.logic.*;

public class Game implements Runnable{
	
	private Display display;
	private String title;
	private int width, height;
	private Thread thread;
	//private static GameMap game = null;
	private boolean running;
	int x = -24, f = 0;
	private BufferStrategy bs;
	private Graphics g;
	
	public Game(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;		
	}
	
	private void initialize(){
		display = new Display (title,width, height);
		Assets.init();
	}
	
	private void update(){
		x+=1;
		f+=1;
		if (f == 20)
			f = 0;
		if (x == 600)
			x = -24;
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		
		g.drawImage(Assets.grass, 0, 0, null);
		if (f < 10)
			g.drawImage(Assets.heroRightWalk1, x, 0, null);
		else 
			g.drawImage(Assets.heroRightWalk2, x, 0, null);
		
		
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
