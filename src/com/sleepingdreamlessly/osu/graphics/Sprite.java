package com.sleepingdreamlessly.osu.graphics;

import com.sleepingdreamlessly.osu.Game;
import com.sleepingdreamlessly.osu.assets.Assets;
import com.sleepingdreamlessly.osu.utils.ImageLoader;

import java.awt.*;
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
		
		System.out.println("Loading " + String.format("%1$20s", "..." + id) + " at " + spritePath); // 8 is padding
		this.i = ImageLoader.loadImage(this.f);
		this.size = this.i.getWidth(null);
	}
	
	public void drawCenteredWithFixedSize(Game game, int x, int y, double size, float opacity)
	{
		Graphics2D g2 = (Graphics2D) game.getGraphics();
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		
		Image scaled = i.getScaledInstance((int)size, (int)size, Image.SCALE_SMOOTH);
		int scaledSize = scaled.getWidth(null);
		
		g2.drawImage(
				scaled,
				x - (scaledSize / 2),
				y - (scaledSize / 2),
				null
				);
	}
	
	public void drawCentered(Game game, int x, int y, double scale, float opacity)
	{
		Graphics2D g2 = (Graphics2D) game.getGraphics();
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		
		Image scaledImage = i.getScaledInstance((int)(i.getWidth(null) * scale), (int)(i.getHeight(null) * scale), Image.SCALE_SMOOTH);
		int scaledSize = scaledImage.getWidth(null);
		
		g2.drawImage(
			scaledImage,
			x - (scaledSize / 2),
			y - (scaledSize / 2),
			null
		);
	}
}
