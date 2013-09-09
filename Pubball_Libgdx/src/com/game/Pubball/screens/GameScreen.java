package com.game.Pubball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.game.Pubball.inputcontroller.InputHandler;
import com.game.Pubball.model.World;
import com.game.Pubball.view.WorldRenderer;

public class GameScreen implements Screen, InputProcessor{

	private World world;
	private WorldRenderer renderer;
	public InputHandler inputHandler;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		inputHandler.update(delta);
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		world = new World();
		renderer = new WorldRenderer(world);
		inputHandler = new InputHandler(world);
		Gdx.input.setInputProcessor(this);
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(this);
		
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.W)
			inputHandler.UpPressed();
		if(keycode == Keys.S)
			inputHandler.DownPressed();
		if(keycode == Keys.A)
			inputHandler.LeftPressed();
		if(keycode == Keys.D)
			inputHandler.RightPressed();
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.W)
			inputHandler.UpReleased();
		if(keycode == Keys.S)
			inputHandler.DownReleased();
		if(keycode == Keys.A)
			inputHandler.LeftReleased();
		if(keycode == Keys.D)
			inputHandler.RightReleased();
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
