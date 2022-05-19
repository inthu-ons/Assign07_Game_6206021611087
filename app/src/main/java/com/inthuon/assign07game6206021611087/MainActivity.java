package com.inthuon.assign07game6206021611087;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int res_iv[] = {R.id.imageView1,R.id.imageView2, R.id.imageView3,
            R.id.imageView4, R.id.imageView5, R.id.imageView6,
            R.id.imageView7, R.id.imageView8, R.id.imageView9};
    int res_image[] = {R.drawable.apple,R.drawable.apricot,
            R.drawable.banana, R.drawable.cherry,
            R.drawable.mango, R.drawable.pear,
            R.drawable.strawberry, R.drawable.watermalon };
    int fg = R.drawable.ic_home_m;
    ImageView iv[] = new ImageView[res_iv.length];
    Button startButton;
    int iNum = -1, oldNum = -1, iImage = -1;
    CountDownTimer timer1;
    Random rnd = new Random();
    int Max = res_image.length;
    int count = 0;
    private TextView showText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showText = (TextView) findViewById(R.id.showText);
        for(int n = 0 ; n < iv.length ; n ++){
            iv[n] = (ImageView) findViewById(res_iv[n]);
            iv[n].setImageResource( fg);
            int N = n ;
            iv[n].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (N == iNum)
                    {
                        count++;
                        showText.setText(Integer.toString(count));
                    }
                }
            });
        }
        timer1 = new CountDownTimer(20000, 1000) {
            public void onTick(long millisUntilFinished) {
                if (oldNum != -1)
                    iv[oldNum].setImageResource(fg);
                iNum = rnd.nextInt( Max );
                iImage = rnd.nextInt( Max );
                iv[iNum].setImageResource( res_image[iImage] );
                oldNum = iNum;
            }
            public void onFinish() {
                iv[iNum].setImageResource(fg);
                startButton.setEnabled(true);
            }
        };
        startButton = (Button) findViewById( R.id.buttonStart);
        startButton.setOnClickListener( new View.OnClickListener() {
            public void onClick(View view) {
                timer1.start();
                count = 0;
                showText.setText(Integer.toString(count));
                startButton.setEnabled(false);

            }
        });
    }
}