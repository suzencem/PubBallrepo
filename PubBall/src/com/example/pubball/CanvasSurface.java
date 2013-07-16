package com.example.pubball;

//PubBall.CanvasSurface draws game elements
//Author: Cem Süzen
//Date: 10.07.2013
//Condition: Under Development...

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CanvasSurface extends SurfaceView implements SurfaceHolder.Callback {

	//Data Field
	//Time keepers
	long lastUpdate = 0;
	long sleepTime = 0;
	//Game Engine
	GameEngine gEngine;
	//Screen info keepers
	SurfaceHolder surfaceHolder;
	Context context;
	//Thread class -houses game loop-
	private PaintThread thread;
	
	void InitView(){
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		
		gEngine = new GameEngine();
		gEngine.Init(context.getResources());
		
		thread = new PaintThread(holder, context, new Handler(), gEngine);
		setFocusable(true);
	}
	
	
	//Constuctors
	public CanvasSurface(Context contextS, AttributeSet attrs, int defStyle){
		super(contextS, attrs, defStyle);
		context = contextS;
		InitView();
	}
	
	public CanvasSurface(Context contextS, AttributeSet attrs){
		super(contextS, attrs);
		context = contextS;
		InitView();
	}
	
	public CanvasSurface(Context context) {
		super(context);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		if(thread.state == PaintThread.PAUSED){
			thread = new PaintThread(getHolder(), context, new Handler(), gEngine);
			thread.start();
		}else{
			thread.start();
		}
	}//end surfaceCreated

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		
		boolean retry = true;
		
		//end game loop
		thread.state = PaintThread.PAUSED;
		while(retry){
			try{
				//kill thread
				thread.join();
				retry = false;
			}catch (InterruptedException e){
			}
		}
		
	}//end surfaceDestroyed
	

}//end CanvasSurface
