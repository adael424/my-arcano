package pl.jp.arkanoid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by Jakub on 11/06/2017.
 */

public class BlockHandler {

    ArrayList<Block> blocks;

    /**
     * Construct the class with starting empty arrayList.
     */
    BlockHandler(){
        blocks = new ArrayList<>();
    }


    /**
     * Populate the arrayList with Blocks in given number of rows, gaps between each block, on the given screen.
     * @param gap - distance between each block
     * @param blockWidth - width of each block
     * @param widthOfWindow - width of window the blocks will be created to
     * @param rows - number of rows to be created
     */
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

    /**
     * Populate a line of blocks from the left.
     * @param gap - distance between each block
     * @param blockWidth - width of each block
     * @param widthOfWindow - width of window the row of blocks will be created to
     * @param row - which row (from north to south) will be created
     */
    public void populateLeft(int gap, int blockWidth, int widthOfWindow, int row){
        Block block;
        for(int i=1; (i*blockWidth+i*gap) < widthOfWindow ; i++) {
            block = new Block(new Rect(gap*i+((i-1)*blockWidth), row*100, gap*i+(i*blockWidth), row*100 + 50), Color.GRAY);
            blocks.add(block);
        }
    }

    /**
     * Populate a line of blocks from thr right.
     * @param gap - distance between each block
     * @param blockWidth - width of each block
     * @param widthOfWindow - width of window the row of blocks will be created to
     * @param row - which row (from north to south) will be created
     */
    public void populateRight(int gap, int blockWidth, int widthOfWindow, int row){
        Block block;
        for(int i=1; (i*blockWidth+i*gap) < widthOfWindow ; i++) {
            block = new Block(new Rect(widthOfWindow - (i*gap + i*blockWidth), row*100, widthOfWindow - (i*gap + (i-1)*blockWidth), row*100 +  50), Color.GRAY);
            blocks.add(block);
        }
    }

    /**
     * Redraw every Block.
     * @param canvas - canvas the blocks wil be drawn to
     */
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        for (Block item : blocks) {
            canvas.drawRect(item.getRectangle(), paint);
        }
    }
}
