package pl.jp.arkanoid;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


/**
 * Main class handling whole program.
 */
public class Arkanoid extends Activity {
    DatabaseHelper databaseHelper;

    @Override
    /**
     * Set the view of the application. Create the logic of the application. Show it.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseHelper = new DatabaseHelper(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        showDatabase();
        View view = new View(this);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                startGame();
                return false;
            }
        });
        setContentView(view);
         }

         public void showDatabase() {
             Cursor res = databaseHelper.getAllData();
             if (res.getCount() == 0) {
                 return;
             }

             StringBuffer buffer = new StringBuffer();
             while (res.moveToNext()) {
                 buffer.append(DatabaseHelper.COL_1 + ": " + res.getString(0) + "\n");
                 buffer.append(DatabaseHelper.COL_2 + ": " + res.getString(1) + "\n");
                 buffer.append(DatabaseHelper.COL_3 + ": " + res.getString(2) + "\n\n");
             }
             showMessage("High Scores", buffer.toString());


         }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    void startGame(){
        setContentView(new GamePanel(this, databaseHelper, this));
    }

}
