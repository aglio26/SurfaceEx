package com.example.madlax.surfaceex;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.view.MotionEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by madlax on 2017/03/24.
 */

public class MySurfaceView extends SurfaceView
    implements SurfaceHolder.Callback { //Surfaceの実装部分 レイヤーの生成、更新、破棄を定義

    private float drawX, drawY; //変数定義
    private float targetX,targetY;

    private void initial(){ // 初期化処理？？
        SurfaceHolder holder = this.getHolder();
        holder.addCallback(this);
        drawX = 50f;
        drawY = 50f;
        targetX = drawX;
        targetY = drawY;
    }

    public void startExecutor(){ //ユーザー定義メソッド
        ScheduledExecutorService service =
                Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new Runnable(){
            @Override
            public void run(){
                Log.d("CHECK", "run-start: "+drawX+","+drawY);
                drawX += (targetX - drawX)/25;
                drawY += (targetY - drawY)/25;
                draw(getHolder());
                Log.d("CHECK","end:"+ drawX + "," + drawY);
            }
        },50,50,TimeUnit.MILLISECONDS);
    }

    public void draw(SurfaceHolder holder){ //描画メソッド
        Canvas c = holder.lockCanvas(); //レイヤーを固定して描画を可能に
        c.drawColor(Color.WHITE);
        Paint p = new Paint();
        p.setColor(Color.RED);
        c.drawCircle(drawX,drawY,50,p); //円の描画
        holder.unlockCanvasAndPost(c);   //レイヤーを解放
    }


    public MySurfaceView(Context context){ //コンストラクタ
        super(context);
        initial();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder){    //レイヤー生成時に呼び出されるメソッド
        draw(surfaceHolder);
        startExecutor();
    }


    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder,int i,int i2,int i3){   //レイヤー変更時に呼び出されるメソッド
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder){  //レイヤー破棄時に読み出されるメソッド
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){ //タッチ実行時に呼び出されるメソッド
        Log.d("CHECK","onTouch-start:"+drawX+","+drawY);
        targetX = e.getX();
        targetY = e.getY();
        return super.onTouchEvent(e);
    }


}