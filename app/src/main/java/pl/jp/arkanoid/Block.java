package pl.jp.arkanoid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Jakub on 11/06/2017.
 */

/**
 * Block is the rectangle Player will be destroyng, implements Actor, as it is the main thing displayed on the screen.
 */
public class Block extends Player implements Actor {

    int color;

    /**
     * Construct the Block, give it its rectangle which is actually displayed, and the rectangles color.
     * @param rectangle - Rect cllass object being the graphical and logical form of player
     * @param color - color of the rectangle
     */
    Block(Rect rectangle, int color) {
        super(rectangle, color);
    }

    @Override
    /**
     * Empty collision, if we wanted for example to make a sound on collision, it would be easy.
     */
    public void collision() {

    }

    @Override
    /**
     * Drawing the Block is actually handled by Handler.
     */
    public void draw(Canvas canvas) {

    }

    @Override
    /**
     * Update the ractangle (just as in Player).
     */
    public void update(Point point) {
        super.update(point);
    }

    /**
     * Get the color of rectangle.
     * @return
     */
    public int getColor(){
        return color;
    }
}
