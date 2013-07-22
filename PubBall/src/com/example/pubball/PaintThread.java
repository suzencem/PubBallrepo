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
//Author: Cem S�zen
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
//	//FPS related
//	private long sleepTime;//in millisec
//	private long delay = 70;//old value: 70
	//FPS related *new*
	private final static int MAX_FPS = 50;//desired fps
	private final static int MAX_FRAME_SKIPS = 5;//maximum number of frames
	private final static int FRAME_PERIOD = 1000 / MAX_FPS;
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
		
		long beginTime;
		long timeDiff;
		int sleepTime;
		int framesSkipped;
		
		sleepTime = 0;
		
		//update 
		while(state==RUNNING){
			
//			long beforeTime = System.nanoTime();//get current time
//			Log.e("beforeTime",""+beforeTime);
			
			beginTime = System.currentTimeMillis();
			framesSkipped = 0;
			
			gEngine.Update();
			
		//draw
		canvas = null;
		
		try{
			canvas = mSurfaceHolder.lockCanvas(null);
			synchronized(mSurfaceHolder){
				gEngine.Draw(canvas);
			}
		} finally {
			if(canvas != null){
				mSurfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
		
		timeDiff = System.currentTimeMillis() - beginTime;
		sleepTime = (int)(FRAME_PERIOD - timeDiff);
		
		if(sleepTime > 0){
			try{	
			Thread.sleep(sleepTime);
			} catch(InterruptedException e){}
		}
		
		while(sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS){
			//update without rendering (running late)
			gEngine.Update();
			sleepTime+= FRAME_PERIOD;
			framesSkipped++;
		}
		
//		this.sleepTime = delay-((System.nanoTime()-beforeTime)/1000000L);
//		Log.e("sleepTime",""+sleepTime);
//		try{
//			if(sleepTime>0)
//				this.sleep(sleepTime);
//			Log.e("SleepTime:", " " + sleepTime);//Debug Code
//			} catch (InterruptedException ex){
//				Logger.getLogger(PaintThread.class.getName()).log(Level.SEVERE,null,ex);
//			}
		
		}//end while
	}//end run
	

}//end PaintThread
