package com.example.felipe.prototipoplann;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imagen;
    TextView textoBienvenida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textoBienvenida = (TextView) findViewById(R.id.textoBienvenida);
        AssetManager am = getAssets();
        Typeface tf = Typeface.createFromAsset(am, "fonts/montserrat.ttf");
        textoBienvenida.setTypeface(tf);

        imagen = (ImageView) findViewById(R.id.imagePlanncirc);
    }

    public void iniciar(View view){
        Intent in = new Intent(this, LoginActivity.class);
        startActivity(in);
        finish();
    }
}
