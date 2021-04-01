package com.lipingwu.lab03;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;

public class Ex02Activity extends AppCompatActivity {

    ImageView reusableImageView;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex02);

        //load image view
        reusableImageView = (ImageView)findViewById(R.id.ImageViewForMoon);

        // Load the appropriate animation
        anim =  AnimationUtils.loadAnimation(this, R.anim.moonmove);

        // Register a listener, so we can disable and re-enable buttons
        anim.setAnimationListener(new MyAnimationListener());

    }
    // Handle Start Button
    public void startAnimation(View view){
        performAnimation(R.anim.moonmove);
    }

    // Handle Stop Button
    public void stopAnimation(View view){
        reusableImageView.clearAnimation();
    }

    private void performAnimation(int animationResourceID)
    {
        reusableImageView.setImageResource(R.drawable.green_rect);
        reusableImageView.setVisibility(View.VISIBLE);

        // Start the animation
        reusableImageView.startAnimation(anim);
    }

    private void toggleButtons(boolean clickableState)
    {
        // Handle Start Button
        final Button startButton = (Button) findViewById(R.id.ButtonStart);
        startButton.setClickable(clickableState);

        // Handle Start Button
        final Button stopButton = (Button) findViewById(R.id.ButtonStop);
        stopButton.setClickable(!clickableState);
    }

    class MyAnimationListener implements Animation.AnimationListener {
        public void onAnimationEnd(Animation animation) {
            // Hide our ImageView
            ImageView reusableImageView = (ImageView)findViewById(R.id.ImageViewForMoon);
            reusableImageView.setVisibility(View.INVISIBLE);

            // Enable all buttons once animation is over
            toggleButtons(true);
        }

        public void onAnimationRepeat(Animation animation) {
            // what to do when animation loops
        }

        public void onAnimationStart(Animation animation) {
            // Disable all buttons while animation is running
            toggleButtons(false);
        }

    }
}