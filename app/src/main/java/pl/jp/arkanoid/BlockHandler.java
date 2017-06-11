package pl.jp.arkanoid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakub on 11/06/2017.
 */

public class BlockHandler {

    ArrayList<Block> blocks;

    BlockHandler(){
        blocks = new ArrayList<>();
    }


    public void populate(int gap, int blockWidth, int widthOfWindow, int rows){
        for(int i = 1; i <= rows; i++){
            if(i%2==0){
                populateLeft(gap, blockWidth, widthOfWindow, i);
            }
            else
            {
                populateRight(gap, blockWidth, widthOfWindow, i);
            }
        }
    }

    public void populateLeft(int gap, int blockWidth, int widthOfWindow, int row){
        Block block;
        for(int i=1; (i*blockWidth+i*gap) < widthOfWindow ; i++) {
            block = new Block(new Rect(gap*i+((i-1)*blockWidth), row*100, gap*i+(i*blockWidth), row*100 + 50), Color.GRAY);
            blocks.add(block);
        }
    }

    public void populateRight(int gap, int blockWidth, int widthOfWindow, int row){
        Block block;
        for(int i=1; (i*blockWidth+i*gap) < widthOfWindow ; i++) {
            block = new Block(new Rect(widthOfWindow - (i*gap + i*blockWidth), row*100, widthOfWindow - (i*gap + (i-1)*blockWidth), row*100 +  50), Color.GRAY);
            blocks.add(block);
        }
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        for (Block item : blocks) {
            canvas.drawRect(item.getRectangle(), paint);
        }
    }
}
