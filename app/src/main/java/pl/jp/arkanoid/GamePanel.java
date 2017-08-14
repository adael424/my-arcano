package pl.jp.arkanoid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.provider.ContactsContract;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

/**
 * Created by Jakub on 10/06/2017.
 */

/**
 * Class handling changes in game logic. Extends SufaceView to be able to display things and implements SurfaceHolder.Callback to be added to the GameThread.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {


    private BallThread ballThread;
    private GameThread thread;

    private Player player;
    private Point playerPosition;
    private BlockHandler blockHandler;
    private int rows;

    private Integer lifes;
    private Integer score;

    private Ball ball;

    /**
     * Set the ball currently displayed and handled.
     * @param ball - ball to be set to the panel
     */
    public void setBall(Ball ball){
        this.ball = ball;
    }

    /**
     * Constructor allowing Panel to connect to screen.
     * @param context - screen the game panel is conected to
     */
    public GamePanel(Context context, DatabaseHelper database, Arkanoid arkanoid){
        super(context);

        getHolder().addCallback(this);
        player = new Player(new Rect(000,000,300,70), Color.GRAY);
        playerPosition = new Point(getWidth()/2,getHeight() - (getHeight()/12) );
        blockHandler = new BlockHandler();
        thread = new GameThread(getHolder(), this, arkanoid);
        ballThread = new BallThread(this, blockHandler, playerPosition, player, database, arkanoid);

        setFocusable(true);
    }

    /**
     * Get the number of lifes left.
     * @return
     */
    public Integer getLifes(){
        return lifes;
    }

    /**
     * Decrement lifes.
     */
    public void decrementLifes(){
        lifes--;
    }

    public void incrementScore(){
        score++;
    }
    public void incrementRows(){
        rows++;
    }

    public int getScore(){ return score; }

    /**
     * Get current number of rows.
     * @return
     */
    public int getRows(){
        return rows;
    }


    @Override
    /**
     * Override function from interface.
     */
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
        //empty
    }

    @Override
    /**
     * Override function from interface. Called when the surface is created.
     */
    public void surfaceCreated(SurfaceHolder holder){
        //thread = new GameThread(getHolder(), this);

        thread.setRunning(true);
        ballThread.setRunning(true);


        score = 0;
        rows = 4;
        lifes = 3;

        blockHandler.populate(20, 100, getWidth(), rows);

        player.update(playerPosition);
        thread.start();
        ballThread.start();
    }

    @Override
    /**
     * When the surface is destroyed end gameThread.
     */
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
    /**
     * Handling the touch, allowing player to move.
     */
    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:

            case MotionEvent.ACTION_MOVE:
                playerPosition.set((int)event.getX(), getHeight() - (getHeight()/9));
        }

        return true;
        //return super.onTouchEvent(event);
    }

    /**
     * Update player position according to touch moves.
     */
    public void update(){
        player.update(playerPosition);
    }

    @Override
    /**
     * Redraw the whole game
     */
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

    void setThreadRunning(boolean running){
        thread.setRunning(running);

    }
}
