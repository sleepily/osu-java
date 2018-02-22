package com.sleepingdreamlessly.osu.objects.std;

import com.sleepingdreamlessly.osu.Game;
import com.sleepingdreamlessly.osu.assets.Assets;
import com.sleepingdreamlessly.osu.audio.AudioPlayer;
import com.sleepingdreamlessly.osu.graphics.Sprite;
import com.sleepingdreamlessly.osu.objects.HitObject;
import com.sleepingdreamlessly.osu.objects.OsuHitObject;
import com.sleepingdreamlessly.osu.rulesets.std.CircleSize;
import com.sleepingdreamlessly.osu.rulesets.std.Timings;
import com.sleepingdreamlessly.osu.rulesets.UI;
import com.sleepingdreamlessly.osu.utils.Utils;
import com.sleepingdreamlessly.osu.utils.Vector2;

public class OsuHitCircle extends OsuHitObject
{
	private Sprite approachCircle;
	private Sprite hitcircleoverlay;
	private Sprite sprite_combo;
	
	private long time_start_fadeIn = Timings.getTimeForCircle_fadeIn(game.ApproachRate, this.time);
	private long time_fadedCompletely;
	
	public OsuHitCircle(Game game, String id, int pos_x, int pos_y, long time)
	{
		super(game, id, pos_x, pos_y, time);
		this.init();
	}
	
	public OsuHitCircle(Game game, String id, int pos_x, int pos_y, long time, int combo)
	{
		super(game, id, pos_x, pos_y, time);
		this.init();
		this.sprite_combo = Assets.font_default_numbers[combo % 10];
		this.isNewCombo = (combo == 1);
	}
	
	private void init()
	{
		this.time_fadedCompletely = this.time + this.time - Timings.getTimeForCircle_fadeIn(game.ApproachRate, this.time);
		this.approachCircle = Assets.approachcircle;
		this.hitcircleoverlay = Assets.hitcircleoverlay;
		this.sprite_combo = Assets.font_combo_numbers[combo];
		this.TYPE = "std";
		this.pos = new Vector2(
			(int)Utils.map(this.pos.x, 0, game.getUI().getBaseSize().x, 0, game.getUI().getScreenVector().x),
			(int)Utils.map(this.pos.y, 0, game.getUI().getBaseSize().y, 0, game.getUI().getScreenVector().y)
		);
		this.sample = Assets.getSample("soft-hitnormal");
	}
	
	public void tick()
	{
		this.calculateAlpha();
		
		if (!this.samplePlayed)
			if (game.getTime_rel_current_ms() >= this.time)
			{
				this.samplePlayed = true;
				AudioPlayer.play(this.sample);
			}
			
		if (game.getTime_rel_current_ms() >= time_fadedCompletely)
			this.dispose = true;
	}
	
	public void render(UI ui)
	{
		if (game.getTime_rel_current_ms() < time_start_fadeIn)
			return;
		
		this.sprite.drawCenteredWithSize(
			this.game,
			(int)(pos.x + ui.getPlayfieldPadding().x),
			(int)(pos.y + ui.getPlayfieldPadding().y),
			CircleSize.circleSize_hitCircle(game.CircleSize),
			this.alpha
		);
		
		this.hitcircleoverlay.drawCenteredWithSize(
			this.game,
			(int)(pos.x + ui.getPlayfieldPadding().x),
			(int)(pos.y + ui.getPlayfieldPadding().y),
			CircleSize.circleSize_hitCircle(game.CircleSize),
			this.alpha
		);
		
		this.sprite_combo.drawCenteredWithScale(
			this.game,
			(int)(pos.x + ui.getPlayfieldPadding().x),
			(int)(pos.y + ui.getPlayfieldPadding().y),
			Utils.map((float)CircleSize.circleSize_hitCircle(game.CircleSize), 20, 120,.4f, 1f),
			this.alpha
		);
		
		this.approachCircle.drawCenteredWithSize(
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
	}
}
