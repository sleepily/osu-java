package com.sleepingdreamlessly.osu.assets;

import com.sleepingdreamlessly.osu.Game;
import com.sleepingdreamlessly.osu.graphics.Sprite;

import java.util.ArrayList;

public class Assets
{
	// general
	private static ArrayList<Sprite> _sprites = new ArrayList<>();
	
	private static String name_skin = "funorange";
	private static String path_skin = "\\res\\skins\\" + name_skin + "\\";
	private static String name_font_default = "default";
	private static String name_font_score = "score";
	private static String name_font_combo = "combo";
	
	// osu
	public static Sprite hitcircle, hitcircleoverlay, approachcircle, cursor, cursortrail;
	
	// mania
	public static Sprite note;
	
	public static Sprite[] font_default_numbers = new Sprite[10];
	public static Sprite[] font_score_numbers = new Sprite[10];
	public static Sprite[] font_combo_numbers = new Sprite[10];
	public static Sprite font_score_percent, font_score_dot, font_score_comma;
	
	public Assets()
	{
		
	}
	
	public static void init(Game game)
	{
		// std
		hitcircle 				= new Sprite(game, "hitcircle");        _sprites.add(hitcircle);
		approachcircle		= new Sprite(game, "approachcircle");   _sprites.add(approachcircle);
		hitcircleoverlay 	= new Sprite(game, "hitcircleoverlay"); _sprites.add(hitcircleoverlay);
		cursor 				    = new Sprite(game, "cursor");           _sprites.add(cursor);
		cursortrail       = new Sprite(game, "cursortrail");      _sprites.add(cursortrail);
		
		// mania
		note              = new Sprite(game, "note");             _sprites.add(note);
		
		// fonts
		for (int i = 0; i < 10; i++)
		{
			font_default_numbers[i] = new Sprite(game, name_font_default + "-" + Integer.toString(i));
			font_combo_numbers[i]   = new Sprite(game, name_font_combo + "-" + Integer.toString(i));
			font_score_numbers[i]   = new Sprite(game, name_font_score + "-" + Integer.toString(i));
			
			_sprites.add(font_default_numbers[i]);
			_sprites.add(font_combo_numbers[i]);
			_sprites.add(font_score_numbers[i]);
		}
	}
	
	public String getSkinName()
	{
		return name_skin;
	}
	
	public static String getSkinPath()
	{
		return path_skin;
	}
	
	public static Sprite get(String id)
	{
		for (Sprite s : _sprites)
			if (s.id.equals(id))
				return s;
		
		System.out.println("WARNING: Sprite " + id + " not found.");
		return null;
	}
}
