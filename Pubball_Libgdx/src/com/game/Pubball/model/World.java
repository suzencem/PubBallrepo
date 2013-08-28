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
		//Create ball
		ball = new Ball(new Vector2(screenWidth / 2, screenHeight / 2));
		//Create 2 goalposts
		leftPost = new GoalPost(new Vector2(0 , screenHeight / 2));
		rightPost = new GoalPost(new Vector2(screenWidth , screenHeight / 2));
	}
}
