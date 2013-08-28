package com.game.Pubball.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GoalPost {
	
	static final float HEIGHT = 50f;
	static final float WIDTH = 20f;
	
	Vector2 position = new Vector2();
	Rectangle bounds = new Rectangle();

	public GoalPost(Vector2 position){
		this.position = position;
		this.bounds.width = WIDTH;
		this.bounds.height = HEIGHT;
	}
}
