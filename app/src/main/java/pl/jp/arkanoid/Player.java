package pl.jp.arkanoid;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Jakub on 10/06/2017.
 */

public class Player implements Actor {

    private Rect rectangle;
    private int color;

    Player(Rect rectangle, int color){
        this.rectangle = rectangle;
        this.color = color;
    }

    @Override
    public void  draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }


    public void update (Point point){
        rectangle.set(point.x - rectangle.width()/2,
                point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2);
    }

    @Override
    public void collision(){
        //it could make a sound
    }

    public Rect getRectangle() {
        return rectangle;
    }
}
