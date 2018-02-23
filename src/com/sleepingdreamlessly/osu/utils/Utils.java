package com.sleepingdreamlessly.osu.utils;

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
}
