package com.game.Pubball.model;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {
	
	//Data field
	//Models
	Array<Player> players = new Array<Player>();
	Ball ball;
	GoalPost leftPost;
	GoalPost rightPost;
	//Display
	float screenWidth;
	float screenHeight;
	//Flags
	static boolean newPlayerFlag;
	
	public Array<Player> getPlayers(){
		return players;
	}
	
	public World(){
		createGameWorld();
	}
	
	private void createGameWorld(){
		
		//Get screen sizes
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		//Create initial players
		newPlayerJoins("TestPlayer1", true);
		newPlayerJoins("TestPlayer2", false);
		//Create ball
		ball = new Ball(new Vector2(screenWidth / 2, screenHeight / 2));
		//Create 2 goalposts
		leftPost = new GoalPost(new Vector2(0 , screenHeight / 2));
		rightPost = new GoalPost(new Vector2(screenWidth - GoalPost.WIDTH , screenHeight / 2));
	}
	
	public void newPlayerJoins(String name,boolean team){
		Player newPlayer = new Player(new Vector2(screenWidth / 2, screenHeight / 2), name, team);
		players.add(newPlayer);
		//TODO: Show information on screen
	}
	
	public float getScreenWidth() {
		return screenWidth;
	}

	public float getScreenHeight() {
		return screenHeight;
	}

}
