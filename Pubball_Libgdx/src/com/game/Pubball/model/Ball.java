package com.game.Pubball.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Ball {
	
	public enum State{
		IDLE, MOVING, GOAL
	}
	
	static final float SPEED = 2f;
	static final float SIZE = 8f;
	private float direction;
	
	Vector2 position = new Vector2();
	Vector2 acceleration = new Vector2();
	Vector2 velocity = new Vector2();
	Circle bounds = new Circle();
	
	public Ball(Vector2 position){
		this.position = position;
		this.bounds.radius = SIZE;
		this.bounds.x = Gdx.graphics.getWidth()/2;
		this.bounds.y = Gdx.graphics.getHeight()/2;
	}

	public float getSize() {
		return SIZE;
	}
	
	public float getPositionX(){
		return position.x;
	}
	
	public float getPositionY(){
		return position.y;
	}

	public Circle getBounds() {
		return bounds;
	}
	
	
}
