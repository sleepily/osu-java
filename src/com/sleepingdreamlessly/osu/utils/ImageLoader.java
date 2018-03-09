package com.sleepingdreamlessly.osu.utils;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class ImageLoader
{
	public static Image loadImage(File f)
	{
		try
		{
			if (!f.exists())
				return null;
			
			return ImageIO.read(f);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
