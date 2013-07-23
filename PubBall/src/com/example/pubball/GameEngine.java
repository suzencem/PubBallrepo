package com.example.pubball;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;

//PubBall.GameEngine handles game events and ball physics, update, draw
//Author: Cem Süzen
//Date: 10.07.2013
//Condition: Under Development...

public class GameEngine {
	
	//Data Field
	//FootBall Field related
	private Bitmap footFieldBmp;//Initial field bmp
	private Bitmap footFieldBmpScaled;//Screenwide field bmp
	private Matrix matrixT;//translation matrix
	private float pointX;//location X
	private float pointY;//location Y
	//Player related
	private int scalePlayerX;//scale X
	private int scalePlayerY;//scale Y
	private ArrayList<Player> playerList;
	private int totalPlayerNumber;//array index + 1
//	private Iterator<Player> playerListItr;
	private Player currentPlayer;
	private Bitmap playerBmp;
	private Bitmap playerBmpScaled;
	//Ball related
	private int pointBallX;//Ball location X
	private int pointBallY;//Ball location Y
	private int scaleBallX;//Scale X
	private int scaleBallY;//Scale Y
	private Bitmap ballBmp;
	private Bitmap ballBmpScaled;
	//Dummy data
	boolean playerInitiateFlag = true;

	public void Init(Resources resources) {
		
		//XY data
		pointX = 0f;
		pointY = 0f;
		scalePlayerX = 25;
		scalePlayerY = 25;
		pointBallX = -1;
		pointBallY = -1;
		scaleBallX = 22;
		scaleBallY = 22;
		
		playerList = new ArrayList<Player>();
//		playerListItr = playerList.iterator();
		
		matrixT = new Matrix();
		
		//Initiate Field
		Drawable footField = resources.getDrawable(R.drawable.saha);
		footFieldBmp = ((BitmapDrawable)footField).getBitmap();
		
		//Initiate Players
		totalPlayerNumber = 0;
		Drawable playerDr = resources.getDrawable(R.drawable.glt);
		playerBmp = ((BitmapDrawable)playerDr).getBitmap();
		playerBmpScaled = Bitmap.createScaledBitmap(playerBmp, scalePlayerX, scalePlayerY, false);
		
		//Initiate Ball
		Drawable ballDr = resources.getDrawable(R.drawable.ftb);
		ballBmp = ((BitmapDrawable)ballDr).getBitmap();
		ballBmpScaled = Bitmap.createScaledBitmap(ballBmp, scaleBallX, scaleBallY, false);
		
	}//end init

	public void Update() {
		
		if(playerInitiateFlag == true){
		newPlayerJoins("testPlayer", true);
		playerInitiateFlag = false;
		}
		
		Log.e("Update Fired!", "Update Fired");
		
	}//end update

	public void Draw(Canvas canvas) {
		
		int playerListItr = 0;//synced with array index
		
		//Draw FootballField
		if(footFieldBmpScaled == null)
		footFieldBmpScaled = Bitmap.createScaledBitmap(footFieldBmp, canvas.getWidth(), canvas.getHeight(), false);
		//matrixT.postTranslate(pointX, pointY);
		canvas.drawBitmap(footFieldBmpScaled, new Matrix(), null);
		
		//Draw player(s)
		while(playerListItr < totalPlayerNumber){
		matrixT.reset();
		Player playerHolder = playerList.get(playerListItr);
		playerListItr++;
		pointX = playerHolder.getPointX();
		pointY = playerHolder.getPointY();
		matrixT.postTranslate(pointX, pointY);
		canvas.drawBitmap(playerBmpScaled, matrixT, null);
		}
		
		//Draw Ball
		matrixT.reset();
		if(pointBallX == -1 || pointBallY == -1){
		pointBallX = canvas.getWidth() / 2;
		pointBallY = canvas.getHeight() / 2;
		}
		else{
		pointBallX = getBallLocationX();
		pointBallY = getBallLocationY();
		matrixT.postTranslate(pointBallX,pointBallY);
		canvas.drawBitmap(ballBmpScaled, matrixT, null);
		}

	}//end draw
	
	private int getBallLocationY() {
		// TODO calculate ball location Y
		return 0;
	}

	private int getBallLocationX() {
		// TODO calculate ball location X
		return 0;
	}

	
	public void newPlayerJoins(String name,boolean team){
		
		Player newPlayer = new Player(name,team);
		playerList.add(newPlayer);
		totalPlayerNumber++;
		//TODO: Show information on screen
	}

}//end GameEngine
