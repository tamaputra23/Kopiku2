package com.example.user.kopiku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView nearbyMenu;
    private ImageView exploreMenu;
    private ImageView searchMenu, aboutMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nearbyMenu= (ImageView) findViewById(R.id.imageView2);
        nearbyMenu.setOnClickListener(this);
        exploreMenu= (ImageView) findViewById(R.id.imageView5);
        exploreMenu.setOnClickListener(this);
        searchMenu= (ImageView) findViewById(R.id.imageView3);
        searchMenu.setOnClickListener(this);
        aboutMenu = (ImageView) findViewById(R.id.imageView6);
        aboutMenu.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView2:
                Intent moveIntent = new Intent(MainActivity.this, NearbyMenu.class);
                startActivity(moveIntent);
                break;
            case R.id.imageView5:
                Intent exploreIntent = new Intent(MainActivity.this, ExploreActivity.class);
                startActivity(exploreIntent);
                break;
            case R.id.imageView3:
                Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(searchIntent);
                break;
            case R.id.imageView6:
                Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
                break;
        }
    }
}
