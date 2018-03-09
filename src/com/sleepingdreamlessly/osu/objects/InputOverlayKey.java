package com.sleepingdreamlessly.osu.objects;

import com.sleepingdreamlessly.osu.Handler;
import com.sleepingdreamlessly.osu.assets.Assets;
import com.sleepingdreamlessly.osu.graphics.Sprite;
import com.sleepingdreamlessly.osu.rulesets.UI;
import com.sleepingdreamlessly.osu.utils.Vector2;

public class InputOverlayKey
{
	private Handler handler;
	private boolean isActive = false;
	private String inputID;
	private Vector2 pos = new Vector2();
	private double padding = 6f;
	
	private Sprite sprite;
	
	public InputOverlayKey(Handler handler, String inputID, int posIndex)
	{
		this.handler = handler;
		this.sprite = Assets.getSprite("inputoverlay-key");
		int height = this.sprite.i.getHeight(null);
		int width = this.sprite.i.getWidth(null);
		this.pos.x = (UI.getScreenVector().x - width);
		this.pos.y =
			(UI.getScreenVector().y / 2)
			- (float)(2 * (padding + height))
			+ (float)(posIndex * (padding + height));
		this.inputID = inputID;
	}
	
	public void tick()
	{
		isActive =
			(this.inputID.equals("std_left"))         ? handler.getInputManager().std_left				.hold :
			(this.inputID.equals("std_right"))        ? handler.getInputManager().std_right				.hold :
			(this.inputID.equals("std_mouse_left"))   ? handler.getInputManager().std_mouse_left	.hold :
			(this.inputID.equals("std_mouse_right"))  ? handler.getInputManager().std_mouse_right	.hold :
				false;
	}
	
	public void render()
	{
		//@TODO: make this work
		
		if (!isActive)
		{
			this.sprite.drawCenteredWithScale(handler.getGame(), (int)pos.x, (int)pos.y, 1f, 1f);
			return;
		}
		
		this.sprite.drawCenteredWithScale(handler.getGame(), (int)pos.x, (int)pos.y, .9f, 1f);
	}
}
