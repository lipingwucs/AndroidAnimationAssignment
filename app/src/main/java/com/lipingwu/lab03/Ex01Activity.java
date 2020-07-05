package com.lipingwu.lab03;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Ex01Activity extends AppCompatActivity {

    AnimationDrawable mframeAnimation = null;
    private String title;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex01);

        /*
        title = getIntent().getStringExtra("title");
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(title);*/

        // Handle Start Button
        final Button onButton = (Button) findViewById(R.id.ButtonStart);
        onButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startAnimation();
            }
        });

        // Handle Stop Button
        final Button offButton = (Button) findViewById(R.id.ButtonStop);
        offButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stopAnimation();
            }
        });
    }

    //

    private void startAnimation()
    {

        ImageView img = (ImageView)findViewById(R.id.ImageViewFireWorks);

        BitmapDrawable frame1 = (BitmapDrawable)getResources().getDrawable(R.drawable.fireworks1);
        BitmapDrawable frame2 = (BitmapDrawable)getResources().getDrawable(R.drawable.fireworks2);
        BitmapDrawable frame3 = (BitmapDrawable)getResources().getDrawable(R.drawable.fireworks3);
        BitmapDrawable frame4 = (BitmapDrawable)getResources().getDrawable(R.drawable.fireworks4);
        BitmapDrawable frame5 = (BitmapDrawable)getResources().getDrawable(R.drawable.fireworks5);
        BitmapDrawable frame6 = (BitmapDrawable)getResources().getDrawable(R.drawable.fireworks6);
        BitmapDrawable frame7 = (BitmapDrawable)getResources().getDrawable(R.drawable.canada7);

        // Get the background, which has been compiled to an AnimationDrawable object.
        int reasonableDuration =400;
        mframeAnimation = new AnimationDrawable();
        mframeAnimation.setOneShot(false);	// loop continuously
        mframeAnimation.addFrame(frame1, reasonableDuration);
        mframeAnimation.addFrame(frame2, reasonableDuration);
        mframeAnimation.addFrame(frame3, reasonableDuration);
        mframeAnimation.addFrame(frame4, reasonableDuration);
        mframeAnimation.addFrame(frame5, reasonableDuration);
        mframeAnimation.addFrame(frame6, reasonableDuration);
        mframeAnimation.addFrame(frame7, reasonableDuration);


        img.setBackground(mframeAnimation);

        mframeAnimation.setVisible(true,true);
        mframeAnimation.start();
    }
    private void stopAnimation()
    {
        mframeAnimation.stop();
        mframeAnimation.setVisible(false,false);
    }

}