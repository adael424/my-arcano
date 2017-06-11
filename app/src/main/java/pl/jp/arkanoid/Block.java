package pl.jp.arkanoid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Jakub on 11/06/2017.
 */

public class Block extends Player implements Actor {

    int color;
    Block(Rect rectangle, int color) {
        super(rectangle, color);
    }

    @Override
    public void collision() {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void update(Point point) {
        super.update(point);
    }
    public int getColor(){
        return color;
    }
}
