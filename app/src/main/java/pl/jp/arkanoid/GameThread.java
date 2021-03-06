package pl.jp.arkanoid;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Jakub on 10/06/2017.
 */

/**
 * Klasa rozszerzająca watek, jest odpowiedzialna za pokazywanie elementów na ekranie.
 * @extends Thread
 */
public class GameThread extends Thread {
    public static final int MAX_FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;
    Arkanoid context;

    /**
     * Set the running param.
     * @param running
     */
    public void setRunning(boolean running){
        this.running = running;
    }

    /**
     * Construct the thread, connect it to the surface it refreshes and game panel it shows.
     * @param surfaceHolder
     * @param gamePanel
     */
    public GameThread(SurfaceHolder surfaceHolder, GamePanel gamePanel, Arkanoid context){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
        this.context = context;
    }

    @Override
    /**
     * Run the thread.
     */
    public void run(){
        long startTime;
        long timeMilis;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000/MAX_FPS;

        while(running){
            startTime = System.nanoTime();
            canvas = null;

            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized(surfaceHolder) {
                    this.gamePanel.update();
                    this.gamePanel.draw
                            (canvas);
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }finally {
                if(canvas!=null){
                    try{
                    surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch(Exception e){e.printStackTrace();}
                }
                }
                timeMilis = (System.nanoTime()-startTime)/1000000;
            waitTime = targetTime - timeMilis;
            try{
                if(waitTime>0)
                    this.sleep(waitTime);
            }catch(Exception e){e.printStackTrace();}

            totalTime += System.nanoTime() - startTime;
            frameCount++;
                    if(frameCount ==MAX_FPS){
                        averageFPS = 1000/((totalTime/frameCount)/1000000);
                        frameCount = 0;
                        totalTime = 0;
                        System.out.println(averageFPS);
                    }
        }
    }
}
