package com.sleepingdreamlessly.osu.assets;

import com.sleepingdreamlessly.osu.Game;
import com.sleepingdreamlessly.osu.graphics.Sprite;

import java.util.ArrayList;

public class Assets
{
	// general
	private static ArrayList<Sprite> _sprites = new ArrayList<>();
	
	private static String name_skin = "dracula_final";
	private static String path_skin = "\\res\\skins\\" + name_skin + "\\";
	private static String name_font_default = "default";
	private static String name_font_score = "score";
	private static String name_font_combo = "combo";
	
	// osu
	public static Sprite inputoverlay_background, inputoverlay_key;
	public static Sprite hitcircle, hitcircleoverlay, approachcircle, cursor, cursortrail;
	
	// mania
	public static Sprite note;
	
	public static Sprite[] font_default_numbers = new Sprite[10];
	public static Sprite[] font_score_numbers = new Sprite[10];
	public static Sprite[] font_combo_numbers = new Sprite[10];
	public static Sprite font_score_comma, font_score_dot, font_score_percent;
	
	public Assets()
	{
		
	}
	
	public static void init(Game game)
	{
		cursor 				          = new Sprite(game, "cursor");                   _sprites.add(cursor);
		cursortrail             = new Sprite(game, "cursortrail");              _sprites.add(cursortrail);
		inputoverlay_background = new Sprite(game, "inputoverlay-background");  _sprites.add(inputoverlay_background);
		inputoverlay_key        = new Sprite(game, "inputoverlay-key");         _sprites.add(inputoverlay_key);
		
		// std
		hitcircle 				= new Sprite(game, "hitcircle");        _sprites.add(hitcircle);
		approachcircle		= new Sprite(game, "approachcircle");   _sprites.add(approachcircle);
		hitcircleoverlay 	= new Sprite(game, "hitcircleoverlay"); _sprites.add(hitcircleoverlay);
		
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
		
		font_score_comma    = new Sprite(game, name_font_default + "-" + "comma");
		font_score_dot      = new Sprite(game, name_font_default + "-" + "dot");
		font_score_percent  = new Sprite(game, name_font_default + "-" + "percent");
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
