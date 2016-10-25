package com.example.felipe.prototipoplann;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

public class NavActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ScrollView scrollAbout;
    PopupWindow infoImagen;
    String[] nombresLugares;
    ImageView[] imagenes;
    CoordinatorLayout appBarNav;
    LinearLayout content;
    LinearLayout horario;
    LinearLayout contenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //this.appBarNav = (CoordinatorLayout) findViewById(R.id.appBarNav);

        this.contenido = (LinearLayout) findViewById(R.id.contenido);
        inicializarImagenes();

        //this.content = (LinearLayout) findViewById(R.id.content);
        //appBarNav = getLayoutInflater().inflate(R.layout.content_nav);
        //this.horario = (LinearLayout) findViewById(R.id.horario);

        /**
        this.stub = (ViewStub) findViewById(R.id.stub);
        this.stub.setLayoutResource(R.layout.content_nav);
        this.stub.inflate();*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        this.nombresLugares = new String[6];
        rellenarNombresLugares();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //this.scrollAbout = (ScrollView) findViewById(R.id.scrollAbout);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {
            //this.contenido = (LinearLayout) findViewById(R.id.contenido);
            inicializarImagenes();
            /**ImageView imagen1 = (ImageView) findViewById(R.id.imagen1);
            imagen1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    infoImagen(view);
                }
            });


            this.stub = (ViewStub) findViewById(R.id.stub);
            this.stub.setLayoutResource(R.layout.content_nav);
            this.stub.inflate();*/

        } else if (id == R.id.nav_slideshow) {
            //this.contenido = (LinearLayout) findViewById(R.id.contenido);
            contenido.removeAllViews();
            contenido.addView(LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_horario, contenido, false));
            TextView texto1 = (TextView) findViewById(R.id.texto1);
            texto1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    accionTexto(view);
                }
            });
            TextView texto2 = (TextView) findViewById(R.id.texto2);
            texto2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    accionTexto(view);
                }
            });

            //this.appBarNav = (CoordinatorLayout) findViewById(R.id.appBarNav);
            /**
            this.stub = (ViewStub) findViewById(R.id.stub);
            this.stub.setLayoutResource(R.layout.activity_horario);
            this.stub.inflate();*/

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            String unLink = "http://plann.net16.net/";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(unLink));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage("com.android.chrome");
            startActivity(intent);
        } else if (id == R.id.nav_face) {
            String unLink = "https://www.facebook.com/PLANNOFICIAL/";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(unLink));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage("com.android.chrome");
            startActivity(intent);
        } else if (id == R.id.nav_twit) {
            String unLink = "https://twitter.com/PLANNOficial/";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(unLink));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage("com.android.chrome");
            startActivity(intent);
        } else if (id == R.id.nav_ins) {
            String unLink = "https://www.instagram.com/PLANN_Oficial/";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(unLink));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage("com.android.chrome");
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void llenarImagenes(){
        this.imagenes = new ImageView[6];
        this.imagenes[0] = (ImageView) findViewById(R.id.imagen1);
        this.imagenes[1] = (ImageView) findViewById(R.id.imagen2);
        this.imagenes[2] = (ImageView) findViewById(R.id.imagen3);
        this.imagenes[3] = (ImageView) findViewById(R.id.imagen4);
        this.imagenes[4] = (ImageView) findViewById(R.id.imagen5);
        this.imagenes[5] = (ImageView) findViewById(R.id.imagen6);
    }

    private void clickImagenes(){
        for (int i = 0; i < 6; i++){
            this.imagenes[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    infoImagen(view);
                }
            });
        }
    }

    public void rellenarNombresLugares(){
        this.nombresLugares[0] = "Volcán Villarica";
        this.nombresLugares[1] = "Lago Caburgua";
        this.nombresLugares[2] = "Cascadas Tolhuaca";
        this.nombresLugares[3] = "Lago Panguipulli";
        this.nombresLugares[4] = "Ojos del Caburgua";
        this.nombresLugares[5] = "Parque Nacional Villarrica";
    }

    private void inicializarImagenes(){
        contenido.removeAllViews();
        contenido.addView(LayoutInflater.from(getApplicationContext()).inflate(R.layout.content_nav, contenido, false));
        llenarImagenes();
        clickImagenes();
    }

    public void infoImagen(View img){
        String tag = (String) img.getTag();
        for(int i = 1; i <= 6; i++){
            if (tag.equals("" + i)){
                FragmentManager fm = getSupportFragmentManager();
                DialogoAlerta dialogo = new DialogoAlerta();
                dialogo.setBuilder(this.nombresLugares[i - 1], "Lugar:", "OK");
                dialogo.show(fm, "tagAlerta");
            }
        }
    }

    public void accionTexto(View view){
        Toast.makeText(getApplicationContext(), "Tag: " + view.getTag().toString(), Toast.LENGTH_SHORT).show();
    }
}
