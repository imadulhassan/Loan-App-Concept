package com.finaxemoney.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.finaxemoney.R;
import com.finaxemoney.adapters.RecomedAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    ImageView drawer_image;
    RecyclerView recyclerView;
    RecomedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Getting Link from Firebase

        drawer_image= findViewById(R.id.drawe_image);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,  R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        drawer_image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }else{
                    drawer.openDrawer(GravityCompat.START);
                }

            }
        });
        adapter= new RecomedAdapter(getApplicationContext());

        recyclerView= findViewById(R.id.rv_recomeded);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        switch (id){


            case  R.id.nav_rate:
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName())));
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

                break;
            case  R.id.nav_term:
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
                startActivity(new Intent(getApplicationContext(),TermActivity.class));
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

                break;
            case  R.id.policy:
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }

                startActivity(new Intent(getApplicationContext(),Privacy.class));
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

                break;


            case  R.id.nav_share:
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }

                Intent sendInten = new Intent();
                sendInten.setAction(Intent.ACTION_SEND);
                sendInten.putExtra(Intent.EXTRA_TEXT, " https://play.google.com/store/apps/details?id=" + getPackageName());
                sendInten.setType("text/plain");
                startActivity(sendInten);
                break;
            case  R.id.nav_transa:
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
                startActivity(new Intent(getApplicationContext(),TransactionActivity.class));
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

                break;
            case  R.id.nav_logout:
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Splash.class));
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();

                break;


        }

        return false;
    }



}
