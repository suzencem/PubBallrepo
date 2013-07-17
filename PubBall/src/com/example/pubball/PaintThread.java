package com.example.pubball;

import java.util.logging.Level;
import java.util.logging.Logger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;

//PubBall.PaintThread thread class that houses game loop
//Author: Cem Süzen
//Date: 10.07.2013
//Condition: Under Development...

public class PaintThread extends Thread {
	
	//Data Field
	private SurfaceHolder mSurfaceHolder;
	private Handler mHandler;
	private Context mContext;
	private Paint mLinePaint;
	private Paint blackPaint;
	GameEngine gEngine;
	Canvas canvas;
	
	private long sleepTime;
	//sleeptime in millisec
	private long delay = 70;
	//state of game
	int state = 1;
	public final static int RUNNING = 1;
	public final static int PAUSED = 2;

	public PaintThread(SurfaceHolder surfaceHolder, Context context, Handler handler
			) {
		
		gEngine = new GameEngine();
		gEngine.Init(context.getResources());
		
		//screen data
		mSurfaceHolder = surfaceHolder;
		this.mHandler = handler;
		this.mContext = context;
		
		//standart paint
		mLinePaint = new Paint();
		mLinePaint.setStrokeWidth(3);
		mLinePaint.setARGB(0,0,128,0);
		//clear screen
		blackPaint = new Paint();
		blackPaint.setStrokeWidth(3);
		blackPaint.setARGB(255,0,0,0);
		//mLinePaint.setAntiAlias(true);
		
	}//end constuctor
	
	//Game Loop
	@Override
	public void run(){
		
		//update 
		while(state==RUNNING){
			long beforeTime = System.nanoTime();//get current time
			gEngine.Update();
			
		//draw
		canvas = null;
		
		try{
			canvas = mSurfaceHolder.lockCanvas(null);
			synchronized(mSurfaceHolder){
				//clear screen
				canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),blackPaint);
				canvas.drawCircle(0, 0, 250, mLinePaint);
				gEngine.Draw(canvas);
			}
		} finally {
			if(canvas != null){
				mSurfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
		
		//sleep
		
		this.sleepTime = delay-((System.nanoTime()-beforeTime)/1000000L);
		try{
			if(sleepTime>0)
				this.sleep(sleepTime);
			Log.e("SleepTime:", " " + sleepTime);//Debug Code
			} catch (InterruptedException ex){
				Logger.getLogger(PaintThread.class.getName()).log(Level.SEVERE,null,ex);
			}
		
		}//end while
	}//end run
	

}//end PaintThread
