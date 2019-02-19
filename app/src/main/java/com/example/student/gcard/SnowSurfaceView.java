package com.example.student.gcard;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SnowSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    public SnowSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    class DrawThread extends  Thread {
        SurfaceHolder holder;
        public DrawThread(SurfaceHolder holder) {
            super();
            this.holder = holder;

        }

        @Override
        public void run() {
            Canvas c = holder.lockCanvas();
            c.drawColor(Color.MAGENTA);
            holder.unlockCanvasAndPost(c);
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        DrawThread thread = new DrawThread(surfaceHolder);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
