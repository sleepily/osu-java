package com.sleepingdreamlessly.osu.objects.std;

import com.sleepingdreamlessly.osu.Game;
import com.sleepingdreamlessly.osu.assets.Assets;
import com.sleepingdreamlessly.osu.graphics.Sprite;
import com.sleepingdreamlessly.osu.objects.OsuHitObject;
import com.sleepingdreamlessly.osu.rulesets.std.CircleSize;
import com.sleepingdreamlessly.osu.rulesets.std.Timings;
import com.sleepingdreamlessly.osu.rulesets.std.UI;
import com.sleepingdreamlessly.osu.utils.Utils;
import com.sleepingdreamlessly.osu.utils.Vector2;

public class OsuHitCircle extends OsuHitObject
{
	private Sprite approachCircle;
	private Sprite hitcircleoverlay;
	private Sprite sprite_combo;
	
	public OsuHitCircle(Game game, String id, int pos_x, int pos_y, long time)
	{
		super(game, id, pos_x, pos_y, time);
		this.approachCircle = Assets.approachcircle;
		this.hitcircleoverlay = Assets.hitcircleoverlay;
		this.sprite_combo = Assets.font_combo_numbers[combo];
		this.TYPE = "std";
	}
	
	public OsuHitCircle(Game game, String id, int pos_x, int pos_y, long time, int combo)
	{
		super(game, id, pos_x, pos_y, time);
		this.approachCircle = Assets.approachcircle;
		this.hitcircleoverlay = Assets.hitcircleoverlay;
		this.sprite_combo = Assets.font_default_numbers[combo % 10];
		this.isNewCombo = (combo == 1);
		this.TYPE = "std";
	}
	
	public void tick()
	{
		this.calculateAlpha();
	}
	
	public void render(UI ui)
	{
		Vector2 pos = new Vector2(
			(int)Utils.map(this.pos.x, 0, ui.getBaseSize().x, 0, ui.getScreenVector().x),
			(int)Utils.map(this.pos.y, 0, ui.getBaseSize().y, 0, ui.getScreenVector().y)
		);
		
		this.sprite.drawCenteredWithFixedSize(
			this.game,
			(int)(pos.x + ui.getPlayfieldPadding().x),
			(int)(pos.y + ui.getPlayfieldPadding().y),
			CircleSize.circleSize_hitCircle(game.CircleSize),
			this.alpha
		);
		this.hitcircleoverlay.drawCenteredWithFixedSize(
			this.game,
			(int)(pos.x + ui.getPlayfieldPadding().x),
			(int)(pos.y + ui.getPlayfieldPadding().y),
			CircleSize.circleSize_hitCircle(game.CircleSize),
			this.alpha
		);
		this.sprite_combo.drawCentered(
			this.game,
			(int)(pos.x + ui.getPlayfieldPadding().x),
			(int)(pos.y + ui.getPlayfieldPadding().y),
			64f / CircleSize.circleSize_hitCircle(game.CircleSize),
			this.alpha
		);
		this.approachCircle.drawCenteredWithFixedSize(
			this.game,
			(int)(pos.x + ui.getPlayfieldPadding().x),
			(int)(pos.y + ui.getPlayfieldPadding().y),
			CircleSize.circleSize_approachCircle(game.CircleSize, game.ApproachRate, game.getTime_rel_current_ms(), this.time),
			this.alpha
		);
	}
	
	protected void calculateAlpha()
	{
		this.alpha = Utils.map(
			game.getTime_rel_current_ms(),
			Timings.getTimeForCircle_fadeIn(game.ApproachRate, this.time),
			Timings.getTimeForCircle_visible(game.ApproachRate, this.time),
			0,
			1
		);
		// System.out.println(game.getTime_rel_current_ms() + ", " + this.time + ", " + Float.toString(this.alpha));
	}
}
