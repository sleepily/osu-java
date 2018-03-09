package com.sleepingdreamlessly.osu.graphics;

import com.sleepingdreamlessly.osu.Game;
import com.sleepingdreamlessly.osu.objects.GameObject;

public class GameCamera
{
	
	private Game game;
	private float offsetX, offsetY;
	
	public GameCamera(Game game, float offsetX, float offsetY) {
		this.game = game;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	
	public void centerOnObject(GameObject object) {
		offsetX = object.getPos().x - game.getWidth() / 2;
		offsetY = object.getPos().y - game.getHeight() / 2;
	}
	
	public void move(float amtX, float amtY) {
		offsetX += amtX;
		offsetY += amtY;
	}

	public float getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(float offsetX) {
		this.offsetX = offsetX;
	}

	public float getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(float offsetY) {
		this.offsetY = offsetY;
	}

}
