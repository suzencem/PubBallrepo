package com.game.Pubball.inputcontroller;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.utils.Array;
import com.game.Pubball.model.Player;
import com.game.Pubball.model.World;
import com.game.Pubball.model.Player.State;

public class InputHandler {
	
		//debug keys
		enum Keys{
			W,A,S,D
		}
		
		private World world;
		private Array<Player> playerList;
		
		static Map<Keys, Boolean> keys = new HashMap<InputHandler.Keys, Boolean>();
		static {
			keys.put(Keys.W, false);
			keys.put(Keys.A, false);
			keys.put(Keys.S, false);
			keys.put(Keys.D, false);
		};
		
		public InputHandler(World world){
			this.world = world;
			this.playerList = world.getPlayers();
		}
		
		public void UpPressed(){
			keys.get(keys.put(Keys.W, true));
		}
		
		public void DownPressed(){
			keys.get(keys.put(Keys.S, true));
		}
		
		public void LeftPressed(){
			keys.get(keys.put(Keys.A, true));
		}
		
		public void RightPressed(){
			keys.get(keys.put(Keys.D, true));
		}
		
		public void UpReleased(){
			keys.get(keys.put(Keys.W, false));
		}
		
		public void DownReleased(){
			keys.get(keys.put(Keys.S, false));
		}
		
		public void LeftReleased(){
			keys.get(keys.put(Keys.A, false));
		}
		
		public void RightReleased(){
			keys.get(keys.put(Keys.D, false));
		}
		
		public void update(float delta){
			processInput();
			for(Player player : world.getPlayers())
			player.update(delta);
		}
		
		//Basic input processing
		private void processInput(){
			for(Player player : world.getPlayers()){
			if(keys.get(Keys.W)){
				player.getVelocity().y += Player.SPEED;	
				player.setState(State.MOVING);
			}
			if(keys.get(Keys.S)){
				player.getVelocity().y -= Player.SPEED;
				player.setState(State.MOVING);
			}
			if(keys.get(Keys.A)){
				player.getVelocity().x -= Player.SPEED;
				player.setState(State.MOVING);
			}
			if(keys.get(Keys.D)){
				player.getVelocity().x += Player.SPEED;
				player.setState(State.MOVING);
			}
			}//end for
		}//end processInput
		
}//end InputHandler
