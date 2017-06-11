package pl.jp.arkanoid;

import android.graphics.Canvas;

/**
 * Created by Jakub on 10/06/2017.
 */

public interface Actor {
    public void draw(Canvas canvas);
    public void collision();
//    public int getTop();
//    public int getBottom();
//    public int getLeft();
//    public int getRight();
}
