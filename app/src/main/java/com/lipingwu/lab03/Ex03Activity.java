package com.lipingwu.lab03;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class Ex03Activity extends AppCompatActivity {
    Spinner spLineThickness;
    int linethickness = 20;
    Integer[] lthicknesses ={20, 30, 40, 50,60};
    int drawingColor = Color.RED;

    private ImageView reusableImageView;
    private TextView textView;
    //
    private int startx;
    private int starty ;
    private int endx;
    private int endy;
    //
    private Paint paint;
    private Bitmap bitmap;
    private Canvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex03);

        //get/set textView
        textView = findViewById(R.id.position);
        textView.setText("(line: "+linethickness+", x:" + endx + ", y:" + endy +")");

        //spinner for line thickness
        spLineThickness = findViewById(R.id.spLineThickness);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, lthicknesses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLineThickness.setAdapter(adapter);

        //spinner event handler
        spLineThickness.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                linethickness = Integer.parseInt (item);
                textView.setText("(line: "+linethickness+", x:" + endx + ", y:" + endy +")");
                paint.setStrokeWidth(linethickness);
                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        linethickness =Integer.parseInt (spLineThickness.getSelectedItem().toString());

        //create the paint for our drawings
        paint = new Paint();
        paint.setColor(drawingColor);
        paint.setStrokeWidth(linethickness);

        //creating a bitmap as content view for the image
        bitmap = Bitmap.createBitmap((int) getWindowManager()
                .getDefaultDisplay().getWidth(), (int) getWindowManager()
                .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
        //tell canvas about the content view
        canvas = new Canvas(bitmap);
        //set the background for your drawings
        canvas.drawColor(Color.GRAY); //background
        //setup the image view
        reusableImageView = (ImageView)findViewById(R.id.ImageViewForDrawing);
        //tell image view for the bitmap format/content
        reusableImageView.setImageBitmap(bitmap);
        reusableImageView.setVisibility(View.VISIBLE);
        startDrawing();

    } //end of create

    //method to get drawingColor
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbtnRed:
                if(checked)
                    drawingColor = Color.RED;
                break;
            case R.id.rbtnGreen:
                if(checked)
                    drawingColor = Color.GREEN;
                break;
            case R.id.rbtnCyan:
                if(checked)
                    drawingColor = Color.CYAN;
                break;
        }
        paint.setColor(drawingColor);
    } //end of get drawing color

    public void clearCanvas(View v)
    {
        startDrawing();
    }
    public void startDrawing()
    {
        //set the background for your drawings
        canvas.drawColor(Color.LTGRAY); //background
        startx = 50;
        starty = 50;
        endx = startx;
        endy = starty;
        canvas.drawPoint(startx, starty, paint);
    }

    //method drawLine
    public void drawLine(Canvas canvas)
    {
        textView.setText("(line: "+linethickness+", x:" + endx + ", y:" + endy +")");
        canvas.drawLine(startx, starty, endx, endy, paint);
        startx=endx;
        starty=endy;
        reusableImageView.setFocusable(true);
        reusableImageView.requestFocus();
    }

    //click UP/LEFT/RIGHT/DOWN ARROW TO Draw
    public void upClick(View view){
        endy=endy - 50;
        drawLine( canvas);
    }
    public void leftClick(View view){
        endx=endx - 50;
        drawLine(canvas);
    }
    public void rightClick(View view){
        endx=endx + 50;
        drawLine( canvas);
    }
    public void downClick(View view){
        endy=endy + 50;
        drawLine( canvas);
    }
    //end of arrow control

    //Activate the DPAD on emulator:
    //change the settings in config.ini file in .android folder
    //hw.dPad=yes
    //hw.mainKeys=yes
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        switch(keyCode)
        {
            case KeyEvent.KEYCODE_DPAD_DOWN:
                endy=endy+5;
                drawLine( canvas);
                return true;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                endx=endx+5;
                drawLine( canvas);
                return true;
            case KeyEvent.KEYCODE_DPAD_UP:
                endy=endy-5;
                drawLine( canvas);
                return true;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                endx=endx-5;
                drawLine( canvas);
                return true;
        }
        return false;
    } //end of key control
    public void returnToMain(View view){
        finish();
    }
}