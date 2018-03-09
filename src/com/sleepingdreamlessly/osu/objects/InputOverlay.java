package com.sleepingdreamlessly.osu.objects;

import com.sleepingdreamlessly.osu.Handler;
import com.sleepingdreamlessly.osu.rulesets.UI;
import com.sleepingdreamlessly.osu.utils.Utils;

public class InputOverlay extends GameObject
{
	private InputOverlayKey[] keys = new InputOverlayKey[4];
	public int width, height;
	
	public InputOverlay(Handler handler)
	{
		super(handler, "inputoverlay-background");
		keys[0] = new InputOverlayKey(handler, "std_left", 0);
		keys[1] = new InputOverlayKey(handler, "std_right", 1);
		keys[2] = new InputOverlayKey(handler, "std_mouse_left", 2);
		keys[3] = new InputOverlayKey(handler, "std_mouse_right", 3);
		this.sprite.i = Utils.rotateImage(this.sprite.i, 90);
		
		//@TODO: calculate correct position?
		pos.x = UI.getScreenVector().x;
		pos.y = UI.getScreenVector().y / 2;
		
		width = sprite.i.getWidth(null);
		height = sprite.i.getHeight(null);
	}
	
	public void tick()
	{
		for (InputOverlayKey iok : keys)
			iok.tick();
	}
	
	public void render(UI ui)
	{
		this.sprite.drawCenteredWithScale(
			this.game,
			(int)(pos.x),
			(int)(pos.y),
			1f,
			1f
		);
		
		for (InputOverlayKey iok : keys)
			iok.render();
	}
}