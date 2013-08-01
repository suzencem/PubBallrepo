package com.example.pubball;

//PubBall.Player handles player inputs and player calculations
//Author: Cem Süzen
//Date: 10.07.2013
//Condition: Under Development...

public class Player {
	
	//Data Field
	private float pointX;//location X
	private float pointY;//location Y
	private String playerName;
	private boolean team;
	private double velocity;
	private double velocityDX;
	private double velocityDY;
	private int direction;
	private float radius;
	
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	Player(String name, boolean teamSide){
		playerName = name;
		team = teamSide;
		
		//Test values
		pointX = 150;
		pointY = 150;
	}

	public float getPointX() {
		return pointX;
	}
	public void setPointX(float pointX) {
		this.pointX = pointX;
	}
	public float getPointY() {
		return pointY;
	}
	public void setPointY(float pointY) {
		this.pointY = pointY;
	}

	public boolean getTeam() {
		return team;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public double getVelocityDX() {
		return velocityDX;
	}

	public void setVelocityDX(double velocityDX) {
		this.velocityDX = velocityDX;
	}

	public double getVelocityDY() {
		return velocityDY;
	}

	public void setVelocityDY(double velocityDY) {
		this.velocityDY = velocityDY;
	}
	
}
