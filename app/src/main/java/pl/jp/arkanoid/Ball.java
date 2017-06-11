package pl.jp.arkanoid;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.Vector;

/**
 * Created by Jakub on 10/06/2017.
 */

public class Ball {

    private int color;
    float centerX;
    float centerY;
    float radius;
    double forceX;
    double forceY;
    float averageForce;
    float maxForce;


    Ball(float centerX, float centerY, float radius, int color, float startForce, float maxForce){
        super();
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;
        this.averageForce = startForce;
        this.maxForce = maxForce;
        forceX = averageForce/5;
        forceY = averageForce*4/5;

    }

    public void  draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY, radius, paint);

    }

    public void update(){
        centerX += forceX;
        centerY += forceY;
    }


    //the ball actually handles the collision effect by itself, it does what it is supposed to, and tells the other object it should behave accordingly
    //the ball itself is told to do collision by the GamePanel
    //I created it only for rects as I do not have any other shapes, not counting the ball

    public boolean collision(Rect obstacle){
        return obstacle.intersects((int)(centerX - radius), (int)(centerY - radius), (int)(centerX + radius), (int)(centerY + radius));
    }

    public void hit(Point centerOfObstacle){
        if(averageForce<maxForce){
            averageForce+=0.5;
        }
        double odlegloscX = (centerOfObstacle.x - centerX);
        double odlegloscY = (centerOfObstacle.y - centerY);
        double odleglosc = Math.sqrt(Math.pow(odlegloscX, 2) + Math.pow(odlegloscY, 2));
        double forceDirectionX = Math.pow((odlegloscX/odleglosc),2);
        double forceDirectionY = Math.pow((odlegloscY/odleglosc),2);

        if(centerOfObstacle.x>centerX) {
            forceX = -(averageForce * forceDirectionX);
        }
        else{
            forceX = averageForce * forceDirectionX;
        }

        if(centerOfObstacle.y>centerY) {
            forceY = -(averageForce * forceDirectionY);
        }
        else{
            forceY = averageForce * forceDirectionY;
        }
    }

    public void reverseX(){
        if(averageForce < maxForce) {
            averageForce += 0.5;
        }
        forceX=-forceX;
    }

    public void reverseY() {
        if (averageForce < maxForce) {
            averageForce += 0.5;
        }
        forceY = -forceY;
    }
}
