package pl.jp.arkanoid;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Jakub on 10/06/2017.
 */

/**
 * Class of the rectangle, the player moves with.
 */
public class Player implements Actor {

    private Rect rectangle;
    private int color;

    /**
     * Constructor of player, it gives the player rectangle which actually is displayed and color it will be displayed with.
     * @param rectangle - Rect object the player is logically and graphically
     * @param color - color of the player rectangle
     */
    Player(Rect rectangle, int color){
        this.rectangle = rectangle;
        this.color = color;
    }

    @Override
    /**
     * Redraw the situation.
     */
    public void  draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }


    /**
     * Update player location.
     * @param point point where the player is replaced to
     */
    public void update (Point point){
        rectangle.set(point.x - rectangle.width()/2,
                point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2);
    }

    @Override
    public void collision(){
        //it could make a sound
    }

    /**
     * Get the Rect the player is displayed as.
     * @return
     */
    public Rect getRectangle() {
        return rectangle;
    }
}
