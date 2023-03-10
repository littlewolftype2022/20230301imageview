package com.example.a20230301imageview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {



    private final static int MIN_WIDTH = 200;
    private ImageView mIageView;
    private TextView mTextView1;
    private SeekBar mSeekBar1;
    private TextView mTextView2;
    private SeekBar mSeekBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById();
        setListeners();
        setMaxValueForSeekBar1();

    }

    private void findViewById(){

        mIageView = (ImageView) findViewById(R.id.imageview);
        mTextView1 = (TextView) findViewById(R.id.textview1);
        mSeekBar1 = (SeekBar) findViewById(R.id.seekbar1);
        mTextView2 = (TextView) findViewById(R.id.textview2);
        mSeekBar2 = (SeekBar) findViewById(R.id.seekbar2);

    }

    private void setListeners(){

        mSeekBar1.setOnSeekBarChangeListener(this);
        mSeekBar2.setOnSeekBarChangeListener(this);

    }


    private void setMaxValueForSeekBar1(){
        DisplayMetrics dmDispalyMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dmDispalyMetrics);
        mSeekBar1.setMax(dmDispalyMetrics.widthPixels-MIN_WIDTH);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if(seekBar.getId()==R.id.seekbar1){
            int newWidth = progress + MIN_WIDTH;
            int newHight = (int) (newWidth*3/4);
            mIageView.setLayoutParams(new LinearLayout.LayoutParams(newHight,newHight));
            mTextView1.setText(getResources().getString(R.string.image_width) + newWidth
                    + " " + getResources().getString(R.string.image_height) + newHight);
        } else if (seekBar.getId()==R.id.seekbar2) {
            Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.android_l)).getBitmap();
            Matrix matrix = new Matrix();
            matrix.setRotate(progress);
            bitmap = Bitmap.createBitmap(bitmap,0,0,
                    bitmap.getWidth(),bitmap.getHeight(),matrix,true);
            mIageView.setImageBitmap(bitmap);
            mTextView2.setText(getResources().getString(R.string.rotate_degree)+progress);


        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}