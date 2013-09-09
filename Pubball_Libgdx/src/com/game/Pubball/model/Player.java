package com.game.Pubball.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Player {
	
	public enum State{
		IDLE, MOVING, QUIT
	}
	
	public static final float SPEED = 2f;//unit per second
	static final float SIZE = 9f;//radius
	private float direction;//360 degrees
	private String name;
	private boolean team;
	private State state = State.IDLE;	
	
	Vector2 position = new Vector2();
	Vector2 acceleration = new Vector2();
	Vector2 velocity = new Vector2();
	Circle bounds = new Circle();
	
	public Player(Vector2 position, String name, boolean team){
		this.position = position;
		this.bounds.radius = SIZE;
		this.bounds.x = Gdx.graphics.getWidth()/2;
		this.bounds.y = Gdx.graphics.getHeight()/2;
		this.name = name;
		this.team = team;
	}
	
	public void update (float delta){
		position.add(velocity.cpy().mul(delta));
	}
	
	public void setState(State newState){
		this.state = newState;
	}
	
	public Vector2 getVelocity() {
		return velocity;
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

	public float getSize() {
		return SIZE;
	}

	
}
