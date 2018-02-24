package com.sleepingdreamlessly.osu.utils;

import com.sleepingdreamlessly.osu.Game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Utils
{
	public static float lerp(float min, float max, float value)
	{
		return (float)((min * (1.0 - value)) + (max * value));
	}
	
	public static float clamp(float val, float min, float max)
	{
    return Math.max(min, Math.min(max, val));
	}
	
	public static float map(float value, float in_min, float in_max, float out_min, float out_max) {
		return ((value - in_min) / (in_max - in_min) * (out_max - out_min) + out_min);
	}
	
	public static float mapAndClamp(float value, float in_min, float in_max, float out_min, float out_max) {
		return clamp(
			map(value, in_min, in_max, out_min, out_max),
			out_min,
			out_max
		);
	}
	
	/*
	taken from JGE (Java Game Engine) https://code.google.com/archive/p/game-engine-for-java/source/default/source
	 */
	public static BufferedImage toBufferedImage(Image img)
	{
		if (img instanceof BufferedImage)
			return (BufferedImage) img;
		
		BufferedImage b = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D bg = b.createGraphics();
		bg.drawImage(img, 0, 0, null);
		bg.dispose();
		
		return b;
	}
	
	public static BufferedImage rotateImage(Image image, double rotationDEG)
	{
		double centerX = image.getWidth(null) / 2;
		double centerY = image.getHeight(null) / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(rotationDEG), centerX, centerY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		
		return op.filter(Utils.toBufferedImage(image), null);
	}
}
