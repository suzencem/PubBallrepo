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
	private String name;
	private boolean team;
	
	Vector2 position = new Vector2();
	Vector2 acceleration = new Vector2();
	Vector2 velocity = new Vector2();
	Circle bounds = new Circle();
	
	public Player(Vector2 position, String name, boolean team){
		this.position = position;
		this.bounds.radius = SIZE;
		this.name = name;
		this.team = team;
	}
	
	public Circle getBounds() {
		return bounds;
	}

	public float getPositionX(){
		return this.position.x;
	}
	
	public float getPositionY(){
		return this.position.y;
	}
	
	public boolean getTeam(){
		return this.team;
	}

	public static float getSize() {
		return SIZE;
	}
}
