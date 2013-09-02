package com.game.Pubball.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.game.Pubball.model.Player;
import com.game.Pubball.model.World;

public class WorldRenderer {
	private World world;
	private OrthographicCamera cam;
	
	ShapeRenderer debugRenderer = new ShapeRenderer();
	
	public WorldRenderer(World world){
		this.world = world;
		this.cam = new OrthographicCamera(world.getScreenWidth() / 2, world.getScreenHeight() / 2);
		this.cam.position.set(world.getScreenWidth() / 2, world.getScreenHeight() / 2, 0);
		this.cam.update();
	}
	
	public void render(){
	debugRenderer.setProjectionMatrix(cam.combined);
	
	//render field
	debugRenderer.begin(ShapeType.Rectangle);
	debugRenderer.setColor(new Color(1, 0, 0, 1));
	debugRenderer.rect(world.getScreenWidth() / 2, world.getScreenHeight() / 2, world.getScreenWidth(), world.getScreenHeight());
	debugRenderer.end();
	
	//Render Players
	debugRenderer.begin(ShapeType.Circle);
	for(Player player : world.getPlayers()){
		Circle circ	 = player.getBounds();
		float playerX = player.getPositionX();
		float playerY = player.getPositionY();
		if( player.getTeam() )
			debugRenderer.setColor(0, 0, 255, 1);
		else if( !player.getTeam() )
			debugRenderer.setColor(255, 0, 0, 1);
		debugRenderer.circle(playerX, playerY, player.getSize());
		
	//Render Ball
	Circle ball = world.getBall().getBounds();
	float ballX = world.getBall().getPositionX();
	float ballY = world.getBall().getPositionY();
	debugRenderer.setColor(255, 255, 255, 1);
	debugRenderer.circle(ballX, ballY, world.getBall().getSize());
	debugRenderer.end();
	
	//Render goalposts
	debugRenderer.begin(ShapeType.Rectangle);
	Rectangle goalPostLeft = world.getLeftPost().getBounds();
	float goalPostLeftX = world.getLeftPost().getPosition().x;
	float goalPostLeftY = world.getLeftPost().getPosition().y;
	debugRenderer.setColor(0,0,0,1);
	debugRenderer.rect(goalPostLeftX, goalPostLeftY, goalPostLeft.width, goalPostLeft.height);
	
	Rectangle goalPostRight = world.getRightPost().getBounds();
	float goalPostRightX = world.getRightPost().getPosition().x;
	float goalPostRightY = world.getLeftPost().getPosition().y;
	debugRenderer.setColor(0,0,0,1);
	debugRenderer.rect(goalPostRightX, goalPostRightY, goalPostRight.width, goalPostRight.height);
	debugRenderer.end();
	}
	}
}
