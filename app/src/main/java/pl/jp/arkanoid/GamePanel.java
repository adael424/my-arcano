package pl.jp.arkanoid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import java.util.Iterator;

/**
 * Created by Jakub on 10/06/2017.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    public static final int MAX_ROWS = 9;
    private GameThread thread;

    private Player player;
    private Point playerPosition;
    private BlockHandler blockHandler;
    private int rows;

    private Integer lifes;
    private Integer score;

    private Ball ball;

    public GamePanel(Context context){
        super(context);

        getHolder().addCallback(this);

        thread = new GameThread(getHolder(), this);

        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
        //empty
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread = new GameThread(getHolder(), this);

        thread.setRunning(true);
        player = new Player(new Rect(000,000,300,70), Color.GRAY);
        playerPosition = new Point(getWidth()/2,getHeight() -250 );

        score = 0;
        rows = 4;
        lifes = 3;
        blockHandler = new BlockHandler();
        blockHandler.populate(20, 100, getWidth(), rows);

        ball = new Ball(getWidth()/3, 1000, 35, Color.BLACK, 20, 70);
        player.update(playerPosition);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        //boolean retry = true;
        while(true){
            try{
                thread.setRunning(false);
                thread.join();
            }catch(Exception e){
                e.printStackTrace();
            }
            //retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:

            case MotionEvent.ACTION_MOVE:
                playerPosition.set((int)event.getX(), getHeight()-250);
        }

        return true;
        //return super.onTouchEvent(event);
    }

    public void update(){
        player.update(playerPosition);

        if(ball.collision(player.getRectangle()))
        {
            ball.hit(playerPosition);
        }

        if((ball.centerX - ball.radius)<0||(ball.centerX + ball.radius)>getWidth()){
            ball.reverseX();
        }

        if((ball.centerY - ball.radius)<0){
            ball.reverseY();
        }

        if((ball.centerY + ball.radius)>getHeight()){
            if(lifes>0){
                lifes--;
                ball = new Ball(getWidth()/3, 1000, 35, Color.BLACK, 20, 70);
            }
        }


        for (Iterator<Block> iterator = blockHandler.blocks.iterator(); iterator.hasNext();) {
            Block block = iterator.next();
            if(ball.collision(block.getRectangle())){
                ball.hit(new Point(block.getRectangle().centerX(),block.getRectangle().centerY()));
                score++;
                iterator.remove();
                if(blockHandler.blocks.isEmpty()){
                    if(rows<MAX_ROWS){
                        rows++;
                    }
                    blockHandler.populate(20, 100, getWidth(), rows);
                }
            }
        }

        ball.update();

    }

    @Override
    public void draw(Canvas canvas){

        Paint paint = new Paint();


        super.draw(canvas);

        canvas.drawColor(Color.WHITE);
        paint.setColor(Color.BLACK);
        paint.setTextSize(80);
        canvas.drawText(lifes.toString(), getWidth()-100, getHeight()-100, paint);
        canvas.drawText(score.toString(), 100, getHeight()-100, paint);
        player.draw(canvas);
        ball.draw(canvas);
        blockHandler.draw(canvas);
    }
}
