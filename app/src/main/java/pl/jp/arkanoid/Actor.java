package pl.jp.arkanoid;

import android.graphics.Canvas;

/**
 * Created by Jakub on 10/06/2017.
 */

/**
 * Interface of a basic rectangle in the game.
 */
public interface Actor {
    /**
     * Redraw the rectangle.
     * @param canvas - canvas the actor is gong to be drawn on
     */
    public void draw(Canvas canvas);

    /**
     * Handle the collision of the object.
     */
    public void collision();
}
