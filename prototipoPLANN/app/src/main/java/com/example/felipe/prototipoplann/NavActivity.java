package com.example.felipe.prototipoplann;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class NavActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    String[] nombresLugares;
    LinearLayout contenido;
    ImageView[] imagenes;
    private GoogleMap mapa;
    private String datos;
    private int current;

    View imagenesV;
    View horarioV;
    MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.contenido = (LinearLayout) findViewById(R.id.contenido);

        this.mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapa);
        this.mapFragment.getMapAsync(this);
        cargarMapa(true);

        this.imagenesV = findViewById(R.id.imagenes);
        this.horarioV = findViewById(R.id.horario);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        this.nombresLugares = new String[6];
        rellenarNombresLugares();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
            removeCurrent();
            inicializarImagenes();
        } else if (id == R.id.nav_slideshow) {
            removeCurrent();
            inicializarItinerario();
        } else if (id == R.id.nav_share) {
            removeCurrent();
            cargarMapa(true);
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

    public void cargarMapa(boolean vis){
        if (vis) {
            this.mapFragment.getView().setVisibility(View.VISIBLE);
            this.current = 0;
        } else this.mapFragment.getView().setVisibility(View.GONE);

    }

    private void inicializarImagenes(){
        this.imagenesV.setVisibility(View.VISIBLE);
        llenarImagenes();
        clickImagenes();
        this.current = 1;
    }

    private void inicializarItinerario(){
        this.horarioV.setVisibility(View.VISIBLE);
        TextView texto1 = (TextView) findViewById(R.id.texto10);
        texto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionTexto(view);
            }
        });
        TextView texto2 = (TextView) findViewById(R.id.texto20);
        texto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionTexto(view);
            }
        });
        this.current = 2;
    }

    private void removeCurrent(){
        switch (this.current){
            case 0:
                cargarMapa(false);
            case 1:
                this.imagenesV.setVisibility(View.GONE);
            case 2:
                this.horarioV.setVisibility(View.GONE);
        }

    }

    public void infoImagen(View img){
        String tag = (String) img.getTag();
        for(int i = 1; i <= 6; i++){
            if (tag.equals("" + i)){
                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                DialogoAlerta dialogo = new DialogoAlerta();
                dialogo.setBuilder(this.nombresLugares[i - 1], "Lugar:", "OK");
                dialogo.show(fm, "tagAlerta");
            }
        }
    }

    public void accionTexto(View view){
        Toast.makeText(getApplicationContext(), "Tag: " + view.getTag().toString(), Toast.LENGTH_SHORT).show();
    }

    private void metodoMarker(Marker marker){
        this.datos = marker.getTitle();

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        DialogoAlerta dialogo = new DialogoAlerta();
        dialogo.setBuilder("Más opciones", this.datos, "OK");
        dialogo.show(fm, "tagAlerta");

        contenido.addView(LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_horario, contenido, false));

        TextView texto = (TextView) findViewById(R.id.texto10);
        texto.setText(datos);
        texto.setTag(datos);
        texto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionTexto(view);
            }
        });

        texto = (TextView) findViewById(R.id.texto20);
        texto.setText(datos);
        texto.setTag(datos);
        texto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionTexto(view);
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mapa = googleMap;
        this.mapa.addMarker(new MarkerOptions()
                .position(new LatLng(-38.7290843, -72.6377406))
                .title("Ciudad: Temuco"));
        this.mapa.addMarker(new MarkerOptions()
                .position(new LatLng(-35.156368, -132.785230))
                .title("Mi semestre XD"));

        this.mapa.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                metodoMarker(marker);
                return true;
            }
        });
    }

}
