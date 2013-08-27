package com.game.Pubball.model;

import com.badlogic.gdx.utils.Array;

public class World {
	Array<Player> players = new Array<Player>();
	Ball ball;
	
	public Array<Player> getPlayers(){
		return players;
	}
	
	public World(){
		
	}
}
