package be.msk.calcelection;
/**
 * cette classe gère le compte à rebours ainsi l'affichage
 */

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;


public class CountDown extends AppCompatActivity {

    private CountDownTimer countDownTimer;
    private TextView time;
    ProgressBar mProgressBar;
    CountDownTimer mCountDownTimer;
    int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        time = (TextView) findViewById(R.id.temp);
        mProgressBar=findViewById(R.id.progressbar);
        mProgressBar.setProgress(i);
        start();

    }

    private void start(){
        countDownTimer = new CountDownTimer(4 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                time.setText(""+ l /1000);
                Log.v("Log_tag", "Tick of Progress"+ i+ l);
                i++;
                mProgressBar.setProgress((int)i*100/(4000/1000));

            }

            @Override
            public void onFinish() {
                //Do what you want
                i++;
                mProgressBar.setProgress(100);
                Intent i = new Intent(getApplicationContext(),Etape4.class);
                startActivity(i);
                finish();
            }
        };
        countDownTimer.start();
    }
    @Override
    public void onBackPressed() {
                        countDownTimer.cancel();
                        Intent intent = new Intent(getApplicationContext(), Etape3.class);
                        startActivity(intent);
                        finish();
    }


}