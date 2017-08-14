package pl.jp.arkanoid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.Iterator;

/**
 * Created by Jakub on 10/06/2017.
 */

/**
 * Ball class has iformation on the ball, and how it acts according to colisions.
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


    /**
     * Contructor sets most parameters of the ball, also divides it starting force.
     * @param centerX
     * @param centerY
     * @param radius
     * @param color
     * @param startForce
     * @param maxForce
     */
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

    /**
     * Redraw the ball on the screen.
     * @param canvas
     */
    public void  draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY, radius, paint);

    }

    /**
     * Update the ball's position.
     */
    public void update(){
        centerX += forceX;
        centerY += forceY;
    }

    /**
     * Check if the ball intersects with a rectangle.
     * @param obstacle - Rect object the collision is checked against
     * @return
     */
    public boolean collision(Rect obstacle){
        return obstacle.intersects((int)(centerX - radius), (int)(centerY - radius), (int)(centerX + radius), (int)(centerY + radius));
    }

    /**
     * Happens when the ball hits something.
     * @param centerOfObstacle - center o of obstacle to hit occured with
     */
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

    /**
     * Happens when the ball hits an eastrn or western wall.
     */
    public void reverseX(){
        if(averageForce < maxForce) {
            averageForce += 0.5;
        }
        forceX=-forceX;
    }

    /**
     * Happens when the ball hits north wall.
     */
    public void reverseY() {
        if (averageForce < maxForce) {
            averageForce += 0.5;
        }
        forceY = -forceY;
    }


}
