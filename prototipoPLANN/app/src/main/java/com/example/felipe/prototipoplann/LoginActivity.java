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
        frameAbout.setVisibility(View.GONE);

    }

    public void continuar(View view){
        Intent in = new Intent(this, NavActivity.class);
        startActivity(in);
        finish();
    }

    public void abrirAbout(View view){
        Animation animationIn = AnimationUtils.loadAnimation(this, R.anim.slide_in_up);
        //animation.setDuration(int);
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
    }

    public void cerrarAbout(View view){
        Animation animationOut = AnimationUtils.loadAnimation(this, R.anim.slide_out_down);
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
    }

    @Override
    public void onBackPressed(){
        View view = new View(LoginActivity.this);
        cerrarAbout(view);
    }
}
