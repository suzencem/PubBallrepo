package com.example.pubball;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
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
	private float scalePlayerX;//scale X
	private float scalePlayerY;//scale Y
	private ArrayList<Player> playerList;
	private int totalPlayerNumber;//array index + 1
//	private Iterator<Player> playerListItr;
	private Player currentPlayer;
	private Bitmap playerBmp;
	private Bitmap playerBmpScaledTeam1;
	private Bitmap playerBmpScaledTeam2;
	//Ball related
	private float pointBallX;//Ball location X
	private float pointBallY;//Ball location Y
	private float scaleBallX;//Scale X
	private float scaleBallY;//Scale Y
	private Bitmap ballBmp;
	private Bitmap ballBmpScaled;
	private float forceOnBall;
	//Dummy data
	boolean playerInitiateFlag = true;
	Random rand;
	int roll;
	int direction;
	double radians;
	float dX;
	float dY;
	Bitmap centerBmp;
	Bitmap centerBmpScaled;
	float scaleCenterX;
	float scaleCenterY;

	public void Init(Resources resources) {
		
		//XY data
		pointX = 0f;
		pointY = 0f;
		scalePlayerX = 26;//horizontal R of player circle
		scalePlayerY = 26;//diagonal R of player circle
		pointBallX = -1;
		pointBallY = -1;
		scaleBallX = 22;//horizontal R of ball circle
		scaleBallY = 22;//vertical R of ball circle
		scaleCenterX = 1;
		scaleCenterY = 1;
		rand = new Random();
		
		playerList = new ArrayList<Player>();
		
		matrixT = new Matrix();
		
		//Initiate Field
		Drawable footField = resources.getDrawable(R.drawable.saha);
		footFieldBmp = ((BitmapDrawable)footField).getBitmap();
		
		//Initiate Players
		totalPlayerNumber = 0;
		Drawable playerDr = resources.getDrawable(R.drawable.glt);
		playerBmp = ((BitmapDrawable)playerDr).getBitmap();
		playerBmpScaledTeam1 = Bitmap.createScaledBitmap(playerBmp, (int)scalePlayerX, (int)scalePlayerY, false);
		playerDr = resources.getDrawable(R.drawable.fener);
		playerBmp = ((BitmapDrawable)playerDr).getBitmap();
		playerBmpScaledTeam2 = Bitmap.createScaledBitmap(playerBmp, (int)scalePlayerX, (int)scalePlayerY, false);
		
		//Initiate Ball
		Drawable ballDr = resources.getDrawable(R.drawable.ftb);
		ballBmp = ((BitmapDrawable)ballDr).getBitmap();
		ballBmpScaled = Bitmap.createScaledBitmap(ballBmp, (int)scaleBallX, (int)scaleBallY, false);
		
		//Initiate center
		Drawable centerDr = resources.getDrawable(R.drawable.center);
		centerBmp = ((BitmapDrawable)centerDr).getBitmap();
		centerBmpScaled = Bitmap.createScaledBitmap(centerBmp, (int)scaleCenterX, (int)scaleCenterY, false);
	}//end init

	public void Update() {
		
		if(playerInitiateFlag == true){
			for(int i=0;i<1;i++){
		newPlayerJoins("testPlayerteam1", true);
		newPlayerJoins("testplayerteam2", false);
			}
		playerInitiateFlag = false;	
		}
		
		//TODO: get player input and update player positions
		int playerListItr = 0;//synced with array index
		
		
		while(playerListItr < totalPlayerNumber){
		Player playerHolder = playerList.get(playerListItr);
		playerListItr++;
		
		//TEST: random numbers are used for testing for multiple players(no collision)
		roll = rand.nextInt(360);
		direction = roll;
		radians = Math.toDegrees(direction);
		dX = (float) (Math.cos(radians) * playerHolder.getVelocity());//original value was double dX
		dY = (float) (Math.sin(radians) * playerHolder.getVelocity());
		playerHolder.setPointX(dX + playerHolder.getPointX());
		playerHolder.setPointY(dY + playerHolder.getPointY());
		}
		
		
		//TODO: detect collisions: p2p, ball2player, walls2player, walls2ball, goalline2ball
		
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
		Player playerHolder = playerList.get(playerListItr);
		
			//DEBUG: center is drawn
			float dummyX = playerHolder.getPointX();//X location for center to be drawn
			float dummyY = playerHolder.getPointY();
			//END DEBUG
			
		matrixT.reset();
		pointX = playerHolder.getPointX() - ((float)Math.cos(Math.toDegrees(135)) * (float)(scalePlayerX * Math.sqrt(2) / 2));
		pointY = playerHolder.getPointY() - ((float)Math.sin(Math.toDegrees(45)) * (float)(scalePlayerY * Math.sqrt(2) / 2));
		matrixT.setTranslate(pointX, pointY);
		if(playerHolder.getTeam())
		canvas.drawBitmap(playerBmpScaledTeam1, matrixT, null);
		else if(!playerHolder.getTeam())
		canvas.drawBitmap(playerBmpScaledTeam2, matrixT, null);
		playerListItr++;
		
			//DEBUG:center is drawn
//			matrixT.reset();
//			matrixT.setTranslate(dummyX, dummyY);
//			canvas.drawBitmap(centerBmpScaled, matrixT, null);
			//END DEBUG
			Paint paint = new Paint();
			paint.setColor(Color.WHITE);
			canvas.drawPoint(dummyX, dummyY, paint);
		}

		
		//Draw Ball
		matrixT.reset();
		if(pointBallX == -1 || pointBallY == -1){
		pointBallX = (float)canvas.getWidth() / 2f;
		pointBallY = (float)canvas.getHeight() / 2f;
		}
		else{
		pointBallX = getBallLocationX(); //- scaleBallX/2
		pointBallY = getBallLocationY();
		}
		matrixT.setTranslate(pointBallX,pointBallY);
		canvas.drawBitmap(ballBmpScaled, matrixT, null);
		//TEST: center is drawn
		matrixT.reset();
		matrixT.setTranslate(pointBallX , pointBallY );
		canvas.drawBitmap(centerBmpScaled, matrixT, null);
		

	}//end draw
	
	private float getBallLocationX() {
		// TODO calculate ball location X
		return pointBallX;
	}

	
	private float getBallLocationY() {
		
		return pointBallY;
	}


	
	public void newPlayerJoins(String name,boolean team){
		
		
		Player newPlayer = new Player(name,team);
		newPlayer.setVelocity(1);//TEST: test velocity set
		playerList.add(newPlayer);
		totalPlayerNumber++;
		
		
		//TODO: Show information on screen
	}

}//end GameEngine
