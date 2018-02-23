package com.sleepingdreamlessly.osu.objects;

import java.awt.Color;

import com.sleepingdreamlessly.osu.Game;
import com.sleepingdreamlessly.osu.assets.Assets;
import com.sleepingdreamlessly.osu.graphics.Sprite;
import com.sleepingdreamlessly.osu.input.KeyManager;
import com.sleepingdreamlessly.osu.rulesets.UI;

public class KeyOverlay extends GameObject
{
	private KeyManager keyManager;
	private Sprite key;
	public int width, height;
	
	public KeyOverlay(Game game, KeyManager keyManager)
	{
		super(game, "inputoverlay-background");
		this.keyManager = keyManager;
		key = Assets.inputoverlay_key;
		x = UI.getScreenVector().x - (sprite.i.getWidth(null) / 2);
		y = UI.getScreenVector().y - (sprite.i.getHeight(null) / 2);
		width = sprite.i.getWidth(null);
		height = sprite.i.getHeight(null);
	}
	
	public void tick()
	{
	
	}
	
	public void render(UI ui)
	{
		this.sprite.drawCenteredWithScale(
			this.game,
			(int)(x),
			(int)(y),
			1f,
			1f
		);
	}
}