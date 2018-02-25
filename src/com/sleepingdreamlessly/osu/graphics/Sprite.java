package com.sleepingdreamlessly.osu.graphics;

import com.sleepingdreamlessly.osu.Game;
import com.sleepingdreamlessly.osu.assets.Assets;
import com.sleepingdreamlessly.osu.utils.ImageLoader;
import com.sleepingdreamlessly.osu.utils.Utils;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.io.File;

public class Sprite
{
	Graphics g;
	
	public File f;
	public String id;
	public Image i;
	public int size = 128;
	
	public Sprite(Game game, String id)
	{
		this.g = game.getGraphics();
		this.id = id;
		String spritePath = System.getProperty("user.dir") + new Assets().getSkinPath() + (id + ".png");
		this.f = new File(spritePath);
		
		// System.out.println("Loading " + String.format("%1$26s", "..." + id) + " at " + spritePath);
		this.i = ImageLoader.loadImage(this.f);
		this.size = this.i.getWidth(null);
	}
	
	//@TODO: rework drawing to use an anchor instead of seperate methods
	
	public void drawCenteredWithSize(Game game, int x, int y, double size, float opacity)
	{
		Image image_scaled = i.getScaledInstance((int)size, (int)size, Image.SCALE_SMOOTH);
		
		draw(game, x, y, image_scaled, opacity);
	}
	
	public void drawCenteredWithScale(Game game, int x, int y, double scale, float opacity)
	{
		Image image_scaled =
			i.getScaledInstance(
				(int)(i.getWidth(null) * scale),
				(int)(i.getHeight(null) * scale),
				Image.SCALE_SMOOTH
			);
		draw(game, x, y, image_scaled, opacity);
	}
	
	/*
		draw with rotation referenced from https://stackoverflow.com/questions/8639567/java-rotating-images#8639615
	 */
	
	private void draw(Game game, int x, int y, Image image, float opacity)
	{
		Graphics2D g2 = (Graphics2D) game.getGraphics();
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		
		g2.drawImage(
			image,
			x - (image.getWidth(null) / 2),
			y - (image.getHeight(null) / 2),
			null
		);
	}
}
