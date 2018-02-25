package com.sleepingdreamlessly.osu;

import com.sleepingdreamlessly.osu.display.Display;
import com.sleepingdreamlessly.osu.assets.Assets;
import com.sleepingdreamlessly.osu.graphics.GameCamera;
import com.sleepingdreamlessly.osu.input.InputManager;
import com.sleepingdreamlessly.osu.objects.GameObject;
import com.sleepingdreamlessly.osu.objects.HitObject;
import com.sleepingdreamlessly.osu.objects.InputOverlay;
import com.sleepingdreamlessly.osu.objects.mania.ManiaHitObject;
import com.sleepingdreamlessly.osu.objects.std.OsuHitCircle;
import com.sleepingdreamlessly.osu.rulesets.UI;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Game implements Runnable
{
	private Thread thread;
	
	private Display display;
	
	private BufferStrategy bs;
	private Graphics g;
	private double _fps = 0;
	
	private boolean running, graphicsready = false;
	
	private InputManager inputManager;
	
	private GameCamera gameCamera;
	private InputOverlay inputOverlay;
	private UI ui;
	
	public ArrayList<HitObject> _hitobjects = new ArrayList<>();
	
	private Handler handler;
	
	private long time_init = System.nanoTime();
	private long time_init_ms = time_init / 1000000;
	private long time_current = time_init;
	private long time_current_ms = time_init / 1000000;
	private long time_rel_current = 0;
	private long time_rel_current_ms = 0;
	
	public ArrayList<HitObject> _hitobjectGarbageCollected = new ArrayList<>();
	private long time_garbageCollection_interval_ms = 40;
	private long time_garbageCollection_last = 0;
	private boolean garbageCollection_inProgress = false;
	
	public double ApproachRate = 9, CircleSize = 4.2, OverallDifficulty = 7, HPDrainRate = 6;
	
	private int width, height = 0;
	
	public Game(String title, int width, int height)
	{
		this.width = width;
		this.height = height;
		this.display = new Display(title, width, height);
		this.ui = new UI(this);
	}
	
	private void init()
	{
		handler = new Handler(this);
		
		display.createDisplay();
		
		Assets.init(this);

		_hitobjects.add(new ManiaHitObject(this, "note", 0, 2000));
		_hitobjects.add(new ManiaHitObject(this, "note", 1, 2100));
		_hitobjects.add(new ManiaHitObject(this, "note", 2, 2200));
		_hitobjects.add(new ManiaHitObject(this, "note", 3, 2300));
		_hitobjects.add(new ManiaHitObject(this, "note", 1, 2500));
		_hitobjects.add(new ManiaHitObject(this, "note", 3, 2600));
		_hitobjects.add(new ManiaHitObject(this, "note", 0, 2700));
		_hitobjects.add(new ManiaHitObject(this, "note", 2, 2800));

		_hitobjects.add(new OsuHitCircle(this, "hitcircle", 140, 200, 4000, 1));
		_hitobjects.add(new OsuHitCircle(this, "hitcircle", 230, 214, 4200, 2));
		_hitobjects.add(new OsuHitCircle(this, "hitcircle", 310, 134, 4400, 3));
		
		_hitobjects.add(new OsuHitCircle(this, "hitcircle", 0, 0, 4600, 1));
		_hitobjects.add(new OsuHitCircle(this, "hitcircle", 0, 348, 4700, 2));
		_hitobjects.add(new OsuHitCircle(this, "hitcircle", 512, 0, 4800, 3));
		_hitobjects.add(new OsuHitCircle(this, "hitcircle", 512, 348, 4900, 4));
		
		inputManager = new InputManager(handler);
		
		gameCamera = new GameCamera(this, 0, 0);
		inputOverlay = new InputOverlay(handler);
	}
	
	private void tick()
	{
		this.updateCurrentTime();
		
		inputManager.tick();
		inputOverlay.tick();
		
		for (HitObject h : _hitobjects)
		{
			h.tick();
			
			if (h.dispose)
			{
				System.out.println(h.toString());
				if (!_hitobjectGarbageCollected.contains(h))
					_hitobjectGarbageCollected.add(h);
			}
		}
		
		if (!garbageCollection_inProgress)
			garbageCollection();
	}
	
	private void garbageCollection()
	{
		if (time_garbageCollection_interval_ms <= time_rel_current_ms - time_garbageCollection_last)
		{
			garbageCollection_inProgress = true;
			
			time_garbageCollection_last += time_garbageCollection_interval_ms;
			
			for (HitObject h : _hitobjectGarbageCollected)
				_hitobjects.remove(h);
			
			// System.out.println("Destroyed " + _hitobjectGarbageCollected.size() + " objects.");
			_hitobjectGarbageCollected.clear();
			
			garbageCollection_inProgress = false;
		}
	}
	
	private void render()
	{
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		//set graphics to buffer
		g = bs.getDrawGraphics();
		
		//clear screen
		g.clearRect(0, 0, width, height);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		// draw playfield @TODO: finish this later and make the playfield scaling actually work
		g.setColor(Color.GREEN);
		g.drawRect
		(
			(int)(UI.getPlayfieldPadding().x),
			(int)(UI.getPlayfieldPadding().y),
			(int)(UI.getScreenVector().x),
			(int)(UI.getScreenVector().y)
		);
		
		// draw mania judgement line @TODO: implement a graphical transition from mode to mode
		g.setColor(Color.GREEN);
		g.drawLine(this.width / 2 - 90, this.height - 100, this.width / 2 + 90, this.height - 100);
		
		// draw debug strings
		g.setColor(Color.WHITE);
		g.setFont(new Font("Consolas", Font.PLAIN, 12));
		g.drawString("FPS: " + Double.toString(_fps), 0, 10);
		g.drawString("ms:  " + Double.toString(time_rel_current_ms), 0, 20);
		
		// @TODO: render objects backwards (use object time as depth)
		for (GameObject h : _hitobjects)
			h.render(this.ui);
		
		inputOverlay.render(this.ui);
		
		if (!graphicsready)
		{
			graphicsready = true;
			display.show();
		}
		
		//clean up
		bs.show();
		g.dispose();
	}
	
	@Override
	public void run()
	{
		init();
		
		//set up max fps, deltatime and fps counter
		int fps = 120;
		double deltaTimeMax = 1000000000 / fps;
		double delta = 0;
		long time;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		int second = 1000000000;
		double reports = 1; // update rate per second
		
		while(running){
			//update delta time
			time = System.nanoTime();
			delta += (time - lastTime) / deltaTimeMax;
			timer += time - lastTime;
			lastTime = time;
			
			//check for delta time to update game
			if(delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			
			//get fps
			if(timer >= second / reports) {
				this._fps = ticks * reports;
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
		if (!running)
			return;
		
		running = false;
		
		try
		{
			thread.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public GameCamera getGameCamera()
	{
		return gameCamera;
	}
	
	public InputManager getInputManager()
	{
		return inputManager;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}

	public Graphics getGraphics()
	{
		return g;
	}
	
	public Display getDisplay()
	{
		return display;
	}
	
	public long getStartTime()
	{
		return time_init;
	}
	
	private void updateCurrentTime()
	{
		if (!graphicsready)
		{
			this.time_init = System.nanoTime();
			this.time_init_ms = System.nanoTime() / 1000000;
			return;
		}
		
		this.time_current = System.nanoTime();
		this.time_current_ms = System.nanoTime() / 1000000;
		this.time_rel_current = this.time_current - this.time_init;
		this.time_rel_current_ms = this.time_current_ms - this.time_init_ms;
	}

	public long getTime_current()
	{
		return time_current;
	}

	public long getTime_current_ms()
	{
		return time_current_ms;
	}

	public long getTime_rel_current()
	{
		return time_rel_current;
	}

	public long getTime_rel_current_ms()
	{
		return time_rel_current_ms;
	}
	
	public UI getUI()
	{
		return ui;
	}

	public ArrayList<HitObject> getHitObjects()
	{
		return _hitobjects;
	}
}
