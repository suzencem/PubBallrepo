package com.example.pubball;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import android.content.Context;
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
import android.view.WindowManager;

//PubBall.GameEngine handles game events and ball physics, update, draw
//Author: Cem Süzen
//Date: 10.07.2013
//Condition: Under Development...

public class GameEngine {
	
	//Data Field
	Canvas localCanvas;
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
	float roll;
	float direction;
	double radians;
	float dX;
	float dY;
	Paint topPaint;
	Paint rightPaint;
	Paint leftPaint;
	Paint bottomPaint;
	Paint playerNoPaint;
	Paint collCircPaint;//Semi-transparent collisioncirclepaint
	Paint coordinatePaint;
	Paint debugPaint;
	float directionalX;
	float directionalY;
	//Display
	//Display
	Display display;
	Point size;
	int displayWidth;
	int displayHeight;
	
	public void Init(Resources resources, Context context) {
		
		//borderpaints
		topPaint = new Paint();
		topPaint.setColor(Color.rgb(30,144,255));
		rightPaint = new Paint();
		rightPaint.setColor(Color.rgb(255,64,64));
		leftPaint = new Paint();
		leftPaint.setColor(Color.rgb(128,0,0));
		bottomPaint = new Paint();
		bottomPaint.setColor(Color.rgb(191,62,255));
		//some debug paints
		playerNoPaint = new Paint();
		playerNoPaint.setColor(Color.rgb(0,255,0));
		collCircPaint = new Paint();
		collCircPaint.setColor(Color.rgb(255,0,255));
		collCircPaint.setAlpha(60);
		//coordinate direction paints
		coordinatePaint = new Paint();
		coordinatePaint.setColor(Color.BLACK);
		debugPaint = new Paint();
		debugPaint.setColor(Color.rgb(0, 255, 0));//green
		
		//XY data
		pointX = 0f;
		pointY = 0f;
		scalePlayerX = 26;//horizontal R of player circle
		scalePlayerY = 26;//diagonal R of player circle
		pointBallX = -1;
		pointBallY = -1;
		scaleBallX = 22;//horizontal R of ball circle
		scaleBallY = 22;//vertical R of ball circle
		
		rand = new Random();
		
		playerList = new ArrayList<Player>();
		
		matrixT = new Matrix();
		
		//display data
		display =  ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		size = new Point();
		display.getSize(size);
		displayWidth = size.x;
		displayHeight = size.y;
		
		
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
		
		
	}//end init

	public void Update() {
		
		//New player update
		if(playerInitiateFlag == true){
			for(int i=0;i<10;i++){
		newPlayerJoins("testPlayerteam1", true);
		newPlayerJoins("testplayerteam2", false);
			}
		playerInitiateFlag = false;	
		}
		
		//Player position update
		//TODO: get player input and update player positions
		int playerListItr = 0;//synced with array index
		
		while(playerListItr < totalPlayerNumber){
		Player playerHolder = playerList.get(playerListItr);
		playerListItr++;
		
		//Reset values
		playerHolder.setVelocityDX(10);
		playerHolder.setVelocityDY(10);
		
		//TODO: GET INPUT: Direction
		
		//Collision
		playerHolder = collisionDetector(playerHolder);
		
		//roll = //DEBUG: random numbers are used to test multiple players
//		direction = rand.nextFloat() * 360f;
		
		direction = playerHolder.getDirection();
		
		Log.e("direction:", ""+playerHolder.getDirection());
		
		radians = Math.toRadians(direction);
		
		//playerHolder.setDirection(direction);
		
		//velocity with direction(movement vector)
		dX = (float) (Math.cos(radians) * playerHolder.getVelocityDX());
		dY = (float) (Math.sin(radians) * playerHolder.getVelocityDY());
		
		directionalX = playerHolder.getPointX();
		directionalY = playerHolder.getPointY();
		
		//Resolve Direction2Movement
		if(playerHolder.getDirection() < 90){
			// cos: + sin: +, X + Y -
			playerHolder.setPointX( dX + playerHolder.getPointX());
			playerHolder.setPointY(- dY + playerHolder.getPointY());
		}
		else if(playerHolder.getDirection() == 90){
			//cos: 0 sin 1, X - Y +
			playerHolder.setPointX( playerHolder.getPointX());
			playerHolder.setPointY(- dY + playerHolder.getPointY());
		}
		else if(playerHolder.getDirection() > 90 && direction <  180){
			//cos: - sin:+, X - Y -
			playerHolder.setPointX( dX + playerHolder.getPointX());
			playerHolder.setPointY(- dY + playerHolder.getPointY());
		}
		else if(playerHolder.getDirection() == 180){
			//cos: -1 sin 0, X - Y 0
			playerHolder.setPointX( dX + playerHolder.getPointX());
			playerHolder.setPointY( playerHolder.getPointY());
		}
		else if(playerHolder.getDirection() > 180 && direction < 270){
			//cos: - sin: -, X - Y +
			playerHolder.setPointX( dX + playerHolder.getPointX());
			playerHolder.setPointY(- dY + playerHolder.getPointY());
		}
		else if(playerHolder.getDirection() == 270){
			//cos: 0 sin: -, X 0 Y -
			playerHolder.setPointX( playerHolder.getPointX());
			playerHolder.setPointY( dY + playerHolder.getPointY());
		}
		else if(playerHolder.getDirection() > 270){
			//cos: + sin: -, X + Y +
			playerHolder.setPointX( dX + playerHolder.getPointX());
			playerHolder.setPointY(- dY + playerHolder.getPointY());
		}
		else{
			Log.e("OUT_OFF_BOUND:", ""+direction);
		}
		

//		dX = (float) (Math.cos(radians) * playerHolder.getVelocityDX());
////		Log.e("dx",""+dX);
////		Log.e("velDX",""+playerHolder.getVelocityDX());
//		dY = (float) (Math.sin(radians) * playerHolder.getVelocityDY());
//		Log.e("dy",""+dY);
//		Log.e("velDY",""+playerHolder.getVelocityDY());
//		playerHolder.setPointX(dX + playerHolder.getPointX());
//		playerHolder.setPointY(dY + playerHolder.getPointY());
		//END DEBUG
		
		
		
		}//end while:player loop
		
		
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
		
		matrixT.reset();
		pointX = playerHolder.getPointX() - (float)(scalePlayerX  / 2);
		pointY = playerHolder.getPointY() - (float)(scalePlayerY  / 2);
		matrixT.setTranslate(pointX, pointY);
		
		
		if(playerHolder.getTeam())
		canvas.drawBitmap(playerBmpScaledTeam1, matrixT, null);
		else if(!playerHolder.getTeam())
		canvas.drawBitmap(playerBmpScaledTeam2, matrixT, null);
		
		//DEBUG:
//		canvas.drawText("" + playerHolder.getDirection(), playerHolder.getPointX(), playerHolder.getPointY(), playerNoPaint);
//		canvas.drawCircle(playerHolder.getPointX(), playerHolder.getPointY(), scalePlayerX  / 2, collCircPaint);
		canvas.drawPoint(playerHolder.getPointX(), playerHolder.getPointY(), topPaint);
		
		//Debug coordinates
		canvas.drawLine(playerHolder.getPointX(), playerHolder.getPointY(), playerHolder.getPointX(), playerHolder.getPointY() - scalePlayerX /2,  coordinatePaint);//up
		canvas.drawLine(playerHolder.getPointX(), playerHolder.getPointY(), playerHolder.getPointX() + scalePlayerX / 2, playerHolder.getPointY(), coordinatePaint);//right
		canvas.drawLine(playerHolder.getPointX(), playerHolder.getPointY(), playerHolder.getPointX() - scalePlayerX / 2, playerHolder.getPointY(), coordinatePaint);//left
		canvas.drawLine(playerHolder.getPointX(), playerHolder.getPointY(), playerHolder.getPointX(), playerHolder.getPointY() + scalePlayerX / 2, coordinatePaint);//down
		canvas.drawLine(playerHolder.getPointX(), playerHolder.getPointY(),(float) (directionalX + Math.cos(Math.toRadians(playerHolder.getDirection())) *(scalePlayerX / 2)) ,(float) (playerHolder.getPointY() + Math.sin(Math.toRadians(playerHolder.getDirection())) *(scalePlayerX / 2)), debugPaint);
		playerListItr++;
			
		}//end while draw players

		
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
		
		//DEBUG: draw borderlines
		canvas.drawLine(0f,0f, displayWidth,(float) 0, topPaint);//top
		canvas.drawLine(displayWidth-1, 0f, displayWidth-1, displayHeight, topPaint);//right
		canvas.drawLine(0f, 0f, 0f, displayHeight, topPaint);//left
		canvas.drawLine(0f, displayHeight-1, displayWidth, displayHeight-1, topPaint);//bottom
		
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
		newPlayer.setRadius(scalePlayerX);
		newPlayer.setVelocityDX(10);//DEBUG: test velocity
		newPlayer.setVelocityDY(10);//DEBUG: test velocity
		playerList.add(newPlayer);
		totalPlayerNumber++;
		
		
		//TODO: Show information on screen
	}
	
	//Rolls 360degrees to find a collision
	public Player collisionDetector(Player collObj){
		
			//Borderline collision
			//Top collision
			if(  collObj.getPointY() -  collObj.getRadius()/2 <= 0 && collObj.getDirection() >= 0 && collObj.getDirection() < 180 ){
				collObj.setPointY(collObj.getRadius()/2);
				collObj.setVelocityDY(0);
				
				Log.e("collision detected!", "TOP");
				collObj.setDirection(rand.nextFloat() * 360f);
			}	
			//Left collision
			if( collObj.getPointX() - collObj.getRadius()/2 <= 0 && collObj.getDirection() > 90 && collObj.getDirection() < 270){
				collObj.setPointX(collObj.getRadius()/2);
				collObj.setVelocityDX(0);
				
				Log.e("collision detected!", "LEFT");
				collObj.setDirection(rand.nextFloat() * 360f);
			}
			//Bottom collision
			if( collObj.getPointY() + collObj.getRadius()/2 >= displayHeight && collObj.getDirection() > 180 && collObj.getDirection() < 360){
				collObj.setPointY(displayHeight - collObj.getRadius()/2);
				collObj.setVelocityDY(0);
				
				Log.e("collision detected!", "BOTTOM");
				collObj.setDirection(rand.nextFloat() * 360f);
			}
			//Right collision
			if( collObj.getPointX() + collObj.getRadius()/2 >= displayWidth && (collObj.getDirection() > 270 || collObj.getDirection() < 90)){
				collObj.setPointX(displayWidth - collObj.getRadius()/2);
				collObj.setVelocityDX(0);
				
				Log.e("collision detected!", "RIGHT");
				collObj.setDirection(rand.nextFloat() * 360f);
			}
		
			
			return collObj;
	}
}//end GameEngine
