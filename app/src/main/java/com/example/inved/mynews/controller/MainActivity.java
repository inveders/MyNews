package com.example.inved.mynews.controller;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.inved.mynews.R;

public class MainActivity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureToolbar();
        this.configureViewPagerAndTabs();


    }


    /**Creation of the toolbar and the menu*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void configureToolbar(){
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Sets the Toolbar
        setSupportActionBar(toolbar);
    }

    /**Action on the icons of Menu Item*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //3 - Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.menu_activity_main_params:
                Toast.makeText(this, "Il n'y a rien à paramétrer ici, passez votre chemin...", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_activity_main_search:
                //Toast.makeText(this, "Recherche indisponible, demandez plutôt l'avis de Google, c'est mieux et plus rapide.", Toast.LENGTH_LONG).show();
                //To pass from MainActivity to HistoryActivity
                startActivity(new Intent(this, SearchActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**Configuration of the ViewPager*/
    private void configureViewPagerAndTabs(){
        //Get ViewPager from layout
        ViewPager pager = findViewById(R.id.activity_main_viewpager);
        //Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));

        // 1 - Get TabLayout from layout
        TabLayout tabs= findViewById(R.id.activity_main_tabs);
        // 2 - Glue TabLayout and ViewPager together
        tabs.setupWithViewPager(pager);

    }

}

