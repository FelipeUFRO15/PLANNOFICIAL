package com.example.felipe.prototipoplann;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Toast;

/**
 * Created by Felipe on 21-09-2016.
 */
public class LoginActivity extends AppCompatActivity {

    FrameLayout frameAbout;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.frameAbout = (FrameLayout) findViewById(R.id.frameAbout);
        //cerrarAbout(frameAbout);
        frameAbout.setVisibility(View.GONE);

    }

    public void continuar(View view){
        Intent in = new Intent(this, NavActivity.class);
        startActivity(in);
        finish();
    }

    public void abrirAbout(View view){
        Animation animationIn = AnimationUtils.loadAnimation(this, R.anim.slide_in_up);
        //use this to make it longer:  animation.setDuration(1000);
        animationIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                frameAbout.setVisibility(View.VISIBLE);
            }
        });

        frameAbout.startAnimation(animationIn);


        //this.frameAbout.setVisibility(View.VISIBLE);
        /**frameAbout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                }
            }
        });*/
    }

    public void cerrarAbout(View view){
        //Toast.makeText(getApplicationContext(), "apreta3 xdxdXDXDxd", Toast.LENGTH_LONG).show();
        Animation animationOut = AnimationUtils.loadAnimation(this, R.anim.slide_out_down);
        //use this to make it longer:  animation.setDuration(1000);
        animationOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                frameAbout.setVisibility(View.GONE);
            }
        });

        frameAbout.startAnimation(animationOut);
        //frameAbout.setVisibility(View.INVISIBLE);
    }
}
