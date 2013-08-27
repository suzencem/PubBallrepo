package com.game.Pubball.model;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Player {
	
	public enum State{
		IDLE, MOVING, QUIT
	}
	
	static final float SPEED = 2f;//unit per second
	static final float SIZE = 25f;//radius
	private float direction;//360 degrees 
	
	Vector2 position = new Vector2();
	Vector2 acceleration = new Vector2();
	Vector2 velocity = new Vector2();
	Circle bounds = new Circle();
	
	public Player(Vector2 position){
		this.position = position;
		this.bounds.radius = SIZE;
	}
	

}
