package com.game.Pubball.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.game.Pubball.model.Player;
import com.game.Pubball.model.World;

public class WorldRenderer {
	
	//Data Field
	private World world;
	private OrthographicCamera cam;
	//Debug related
	ShapeRenderer debugRenderer = new ShapeRenderer();
	private boolean debug= true;
	//Textures
	private Texture fieldTexture;
	private Texture ballTexture;
	private Texture goalPostTexture;
	private Texture team1Texture;
	private Texture team2Texture;
	//SpriteBatch
	private SpriteBatch spriteBatch;
	

	public WorldRenderer(World world){
		this.world = world;
		this.cam = new OrthographicCamera(world.getScreenWidth(), world.getScreenHeight());
		this.cam.position.set(world.getScreenWidth()/2, world.getScreenHeight()/2, 0);
		this.cam.update();
		spriteBatch = new SpriteBatch();
		loadTextures();
	}
	
	private void loadTextures(){
		fieldTexture = new Texture(Gdx.files.internal("images/saha.png"));
		ballTexture = new Texture(Gdx.files.internal("images/ftb.png"));
		goalPostTexture = new Texture(Gdx.files.internal("images/goalpost.png"));
		team1Texture = new Texture(Gdx.files.internal("images/glt.png"));
		team2Texture = new Texture(Gdx.files.internal("images/fener.png"));
	}
	
	public void render(){
	debugRenderer.setProjectionMatrix(cam.combined);
	
	//render field
	debugRenderer.begin(ShapeType.Rectangle);
	debugRenderer.setColor(new Color(1, 0, 0, 1));
	debugRenderer.rect(1, 1, world.getScreenWidth()-1, world.getScreenHeight()-1);
	debugRenderer.end();
	
	//Render Players
	
	for(Player player : world.getPlayers()){
		debugRenderer.begin(ShapeType.Circle);
		Circle circ	 = player.getBounds();
		float playerX = player.getPositionX();
		float playerY = player.getPositionY();
		if( player.getTeam() )
			debugRenderer.setColor(0, 0, 255, 1);
		else if( !player.getTeam() )
			debugRenderer.setColor(255, 0, 0, 1);
		debugRenderer.circle(playerX, playerY, circ.radius);
		debugRenderer.end();
	}
		
	//Render Ball
	debugRenderer.begin(ShapeType.Circle);
	Circle ball = world.getBall().getBounds();
	float ballX = world.getBall().getPositionX();
	float ballY = world.getBall().getPositionY();
	debugRenderer.setColor(255, 255, 255, 1);
	debugRenderer.circle(ballX, ballY, ball.radius);
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
	}//end render
	
}
