package com.example.pubball;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

//PubBall.GameEngine handles game events and ball physics, update, draw
//Author: Cem Süzen
//Date: 10.07.2013
//Condition: Under Development...

public class GameEngine {
	
	//Data Field
	Canvas c;
	//Dummy data
	private boolean dummyVariable;
	private Paint mLinePaint;
	private Paint blackPaint;
	private SurfaceHolder mSurfaceHolder;

	public void Init(Resources resources) {
		
		c = null;
		
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
		
	}//end update

	public void Draw(Canvas c) {
		
		c = null;
		
		try{
			c = mSurfaceHolder.lockCanvas(null);
			synchronized(mSurfaceHolder){
				//clear screen
				if(dummyVariable)
				c.drawRect(0,0,c.getWidth(),c.getHeight(),mLinePaint);
				else if(!dummyVariable)
				c.drawRect(0,0,c.getWidth(),c.getHeight(),blackPaint);
				this.Draw(c);
			}
		} finally {
			if(c != null){
				mSurfaceHolder.unlockCanvasAndPost(c);
			}
		}
		

		
		
	}//end draw

	public boolean getDummyVariable() {
		return dummyVariable;
	}

}
