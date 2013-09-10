package com.game.Pubball.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;

public class World {
	
	//Data field
	//Models
	Array<Player> players = new Array<Player>();
	Ball ball;
	GoalPost leftPost;
	GoalPost rightPost;
	//Display
	int screenWidth;
	int screenHeight;
	//Flags
	static boolean newPlayerFlag;
	//Convertion values
	static final float WORLD_TO_BOX = 0.01f;
	static final float BOX_TO_WORLD = 100f;
	
	float convertToBox(float x){
		return x*WORLD_TO_BOX;
	}
	
	public void createBody(World world, Vector2 pos, float angle){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(pos.x, pos.y);
		bodyDef.angle = angle;
	}
	
	public Array<Player> getPlayers(){
		return players;
	}
	
	public World(){
		this.screenHeight = Gdx.graphics.getHeight();
		this.screenWidth = Gdx.graphics.getWidth();
		createGameWorld();
	}
	
	private void createGameWorld(){

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

	public Ball getBall() {
		return ball;
	}

	public GoalPost getLeftPost() {
		return leftPost;
	}

	public GoalPost getRightPost() {
		return rightPost;
	}

}
