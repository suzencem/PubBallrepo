package com.example.pubball;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

//PubBall.GameEngine handles game events and ball physics, update, draw
//Author: Cem Süzen
//Date: 10.07.2013
//Condition: Under Development...

public class GameEngine {
	
	//Data Field
	//Dummy data
	private boolean dummyVariable;
	private Paint mLinePaint;
	private Paint blackPaint;

	public void Init(Resources resources) {
		
		
		dummyVariable = false;
		
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
		

		
				if(dummyVariable)
				canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),mLinePaint);
				else if(!dummyVariable)
				canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),blackPaint);


	}//end draw

	public boolean getDummyVariable() {
		return dummyVariable;
	}

}
