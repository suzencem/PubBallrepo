package com.example.pubball;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.SurfaceHolder;

//PubBall.GameEngine handles game events and ball physics, update, draw
//Author: Cem Süzen
//Date: 10.07.2013
//Condition: Under Development...

public class GameEngine {
	
	//Data Field
	//Dummy data
	private boolean dummyVariable;//toggle for solid color used for testing
	private Paint mLinePaint;//solid test color
	private Paint blackPaint;//solid test color
	private Bitmap footFieldBmp;

	public void Init(Resources resources) {
		
		dummyVariable = false;
		
		Drawable footField = resources.getDrawable(R.drawable.saha);
		footFieldBmp = ((BitmapDrawable)footField).getBitmap();
		
		mLinePaint = new Paint();
		mLinePaint.setARGB(255,0,255,0);
		//clear screen
		blackPaint = new Paint();
		blackPaint.setARGB(255,0,0,0);
		
	}//end init

	public void Update() {
		
		if(dummyVariable)
			dummyVariable = false;
		else if (!dummyVariable)
			dummyVariable = true;
		Log.e("dummyVariable", "" + dummyVariable);
		
	}//end update

	public void Draw(Canvas canvas) {

		canvas.drawBitmap(footFieldBmp, matrix, null);
				if(dummyVariable)
				canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),mLinePaint);
				else if(!dummyVariable)
				canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),blackPaint);


	}//end draw
	
	public void bitmapConverter(){
		
	}

	public boolean getDummyVariable() {
		return dummyVariable;
	}

}//end GameEngine
