package com.example.pubball;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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
//Author: Cem S�zen
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
	private Bitmap playerBmpScaledTeam1;
	private Bitmap playerBmpScaledTeam2;
	//Ball related
	private int pointBallX;//Ball location X
	private int pointBallY;//Ball location Y
	private int scaleBallX;//Scale X
	private int scaleBallY;//Scale Y
	private Bitmap ballBmp;
	private Bitmap ballBmpScaled;
	//Dummy data
	boolean playerInitiateFlag = true;
	Random rand;
	int roll;
	int direction;
	double radians;
	float dX;
	float dY;

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
		rand = new Random();
		
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
		playerBmpScaledTeam1 = Bitmap.createScaledBitmap(playerBmp, scalePlayerX, scalePlayerY, false);
		playerDr = resources.getDrawable(R.drawable.fener);
		playerBmp = ((BitmapDrawable)playerDr).getBitmap();
		playerBmpScaledTeam2 = Bitmap.createScaledBitmap(playerBmp, scalePlayerX, scalePlayerY, false);
		
		//Initiate Ball
		Drawable ballDr = resources.getDrawable(R.drawable.ftb);
		ballBmp = ((BitmapDrawable)ballDr).getBitmap();
		ballBmpScaled = Bitmap.createScaledBitmap(ballBmp, scaleBallX, scaleBallY, false);
		
	}//end init

	public void Update() {
		
		if(playerInitiateFlag == true){
			for(int i=0;i<5;i++){
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
		radians = Math.toRadians(direction);
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
		matrixT.reset();
		Player playerHolder = playerList.get(playerListItr);
		playerListItr++;
		pointX = playerHolder.getPointX();
		pointY = playerHolder.getPointY();
		matrixT.postTranslate(pointX, pointY);
		if(playerHolder.getTeam())
		canvas.drawBitmap(playerBmpScaledTeam1, matrixT, null);
		else if(!playerHolder.getTeam())
		canvas.drawBitmap(playerBmpScaledTeam2, matrixT, null);
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
		newPlayer.setVelocity(1);//TEST: test velocity set
		playerList.add(newPlayer);
		totalPlayerNumber++;
		
		
		//TODO: Show information on screen
	}

}//end GameEngine
