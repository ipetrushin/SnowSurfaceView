package com.example.student.gcard;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SnowSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    DrawThread thread;
    
    // центр объекта, например, круга
    float center_x = 0, center_y = 0;
    public SnowSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float tx = event.getX();
        float ty = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            center_x = tx;
            center_y = ty;
        }
        else  {

        }
        return true;
    }

    class DrawThread extends  Thread {
        boolean isRunning = true; // флаг для управления потоком
        SurfaceHolder holder;
        Paint p; // объект-кисть
        DrawThread(SurfaceHolder holder) {
            super();
            this.holder = holder;
            p = new Paint();
        }

        @Override
        public void run() {
            while (isRunning) {

                try {
                    sleep(50);
                } catch (InterruptedException e) {}

                Canvas c = holder.lockCanvas();
                // проверить, существует ли канва
                if (c != null) {
                    c.drawColor(Color.BLACK);
                    p.setColor(Color.YELLOW);
                    c.drawCircle(center_x, center_y, 20, p);
                    holder.unlockCanvasAndPost(c);
                }
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
        // при изменении поверхности перезапустить поток
        thread.isRunning = false;
        if (surfaceHolder != null) {
            thread = new DrawThread(surfaceHolder);
            thread.start();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        // остановить поток, если поверхности больше нет
        thread.isRunning = false;
    }
}
