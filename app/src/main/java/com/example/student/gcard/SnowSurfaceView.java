package com.example.student.gcard;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SnowSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    DrawThread thread;
    int r =0, g = 0, b = 0;
    public SnowSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    class DrawThread extends  Thread {
        boolean isRunning = true;
        SurfaceHolder holder;
        DrawThread(SurfaceHolder holder) {
            super();
            this.holder = holder;

        }

        @Override
        public void run() {
            while (isRunning) {
                r += 10;
                if (r > 255) {
                    r = 0;
                    g += 20;
                }
                if (g > 255) {
                    g = 0;
                    b += 40;
                }
                try {
                    sleep(50);
                } catch (InterruptedException e) {}

                Canvas c = holder.lockCanvas();
                c.drawColor(Color.rgb(r,g,b));
                holder.unlockCanvasAndPost(c);
            }
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        thread = new DrawThread(surfaceHolder);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        thread.isRunning = false;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        thread.isRunning = false;
    }
}
