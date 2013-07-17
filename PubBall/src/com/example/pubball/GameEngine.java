package com.example.pubball;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

//PubBall.GameEngine handles game events and ball physics, update, draw
//Author: Cem S�zen
//Date: 10.07.2013
//Condition: Under Development...

public class GameEngine {
	
	//Data Field
	//Dummy data
	private boolean dummyVariable;
	private Paint mLinePaint;
	private Paint blackPaint;
	private SurfaceHolder mSurfaceHolder;

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
		
		canvas = null;
		
		try{
			canvas = mSurfaceHolder.lockCanvas(null);
			synchronized(mSurfaceHolder){
				//clear screen
				if(dummyVariable)
				canvas.drawColor(255);
				else if(!dummyVariable)
				canvas.drawRect(15,15,canvas.getWidth(),canvas.getHeight(),blackPaint);
				mSurfaceHolder.unlockCanvasAndPost(canvas);
			}
		} finally {
			if(canvas != null){
				mSurfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
		

		
		
	}//end draw

	public boolean getDummyVariable() {
		return dummyVariable;
	}

}
