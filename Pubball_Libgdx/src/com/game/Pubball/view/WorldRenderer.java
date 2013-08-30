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
		this.cam = new OrthographicCamera(10, 7);
		this.cam.position.set(5, 3.5f, 0);
		this.cam.update();
	}
	
	public void render(){
	debugRenderer.setProjectionMatrix(cam.combined);
	
	//render field
	debugRenderer.begin(ShapeType.Rectangle);
	debugRenderer.setColor(new Color(1, 0, 0, 1));
	debugRenderer.rect(world.getScreenWidth() / 2, world.getScreenHeight() / 2, world.getScreenWidth(), world.getScreenHeight());
	
	//Render Players
	for(Player player : world.getPlayers()){
		Circle circ	 = player.getBounds();
		float x1 = player.getPositionX();
		float y1 = player.getPositionY();
		if( player.getTeam() )
			debugRenderer.setColor(0, 0, 255, 1);
		else if( !player.getTeam() )
			debugRenderer.setColor(255, 0, 0, 1);
		debugRenderer.circle(x1, y1, player.getSize());
		
	//Render Ball
		
	//Render goalposts
	}
	}
}
