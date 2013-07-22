package com.example.pubball;

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
	private int pointXInt;//size X
	private int pointYInt;//size Y
	private Player[] PlayerList;
	private Bitmap playerBmp;
	private Bitmap playerBmpScaled;


	public void Init(Resources resources) {
		
		pointX = 0f;
		pointY = 0f;
		pointXInt = 15;
		pointYInt = 15;
		
		matrixT = new Matrix();
		
		//Initiate Field
		Drawable footField = resources.getDrawable(R.drawable.saha);
		footFieldBmp = ((BitmapDrawable)footField).getBitmap();
		
		//Initiate Players
		Player Player1 = new Player();
		Drawable playerDr = resources.getDrawable(R.drawable.glt);
		playerBmp = ((BitmapDrawable)playerDr).getBitmap();
		playerBmpScaled = Bitmap.createScaledBitmap(playerBmp, pointXInt, pointYInt, false);
		
		
	}//end init

	public void Update() {
		
		Log.e("Update Fired!", "Update Fired");
		
	}//end update

	public void Draw(Canvas canvas) {

		//Draw FootballField
		if(footFieldBmpScaled == null)
		footFieldBmpScaled = Bitmap.createScaledBitmap(footFieldBmp, canvas.getWidth(), canvas.getHeight(), false);
		matrixT.postTranslate(pointX, pointY);
		canvas.drawBitmap(footFieldBmpScaled, matrixT, null);
		
		//Draw player(s)
		//TODO: Loop to draw every player at the designated coordinates.
		pointX = 150;
		pointY = 400;
		matrixT.postTranslate(pointX, pointY);
		canvas.drawBitmap(playerBmpScaled, matrixT, null);
		


	}//end draw
	


}//end GameEngine
