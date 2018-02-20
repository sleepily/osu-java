package com.sleepingdreamlessly.osu.objects;

import com.sleepingdreamlessly.osu.Game;
import com.sleepingdreamlessly.osu.assets.Assets;
import com.sleepingdreamlessly.osu.graphics.Sprite;
import com.sleepingdreamlessly.osu.rulesets.UI;

public class KeyOverlay extends GameObject
{
	private Sprite key;
	
	public KeyOverlay(Game game)
	{
		super(game, "keyoverlay_background");
		key = Assets.inputoverlay_key;
		x = UI.getScreenVector().x - (sprite.i.getWidth(null) / 2);
		y = UI.getScreenVector().y - (sprite.i.getHeight(null) / 2);
	}
	
	public void render(UI ui)
	{
		this.sprite.drawCenteredWithSize(
			this.game,
			(int)(x),
			(int)(y),
			1f,
			1f
		);
	}
}