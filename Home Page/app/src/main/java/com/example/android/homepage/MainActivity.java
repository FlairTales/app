package com.example.android.homepage;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.homepage.R;
import com.google.firebase.auth.FirebaseAuth;

import me.anwarshahriar.calligrapher.Calligrapher;

public class MainActivity extends AppCompatActivity {

    //menu hamburger.
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private FirebaseAuth mAuth;
    TextView body;
    Typeface descpfont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        //menu - hamburger.
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Button signOut = (Button) findViewById(R.id.signOut);
       // signOut.setOnClickListener(new View.OnClickListener() {
       //     @Override
        //    public void onClick(View v) {
       //         mAuth.signOut();
       //         Intent intentEmail = new Intent(MainActivity.this, SignInActivity.class);
       //         startActivity(intentEmail);
       //     }
       // });


    }

    // check_2
    //menu - navigation drawer.
    Intent intent;
    Toast toast;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
            switch (item.getItemId()) {
                case R.id.home:
                    // User chose the "Home" item, show the app settings UI...
                    intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    toast = Toast.makeText(this, "HomePage", Toast.LENGTH_SHORT);
                    toast.show();
                    return true;

                case R.id.explore:
                    // User chose the "Explore" action, mark the current item
                    // as a favorite...
                    intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    toast = Toast.makeText(this, "Explore", Toast.LENGTH_SHORT);
                    toast.show();
                    return true;

                case R.id.settings:
                    // User chose the "Settings" item, show the app settings UI...
                    intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    toast = Toast.makeText(this, "Settings", Toast.LENGTH_SHORT);
                    toast.show();
                    return true;

                case R.id.trending:
                    // User chose the "Trending" item, show the app settings UI...
                    intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    toast = Toast.makeText(this, "Trending", Toast.LENGTH_SHORT);
                    toast.show();
                    return true;

                case R.id.connect:
                    // User chose the "Connect" item, show the app settings UI...
                    intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    toast = Toast.makeText(this, "Connect", Toast.LENGTH_SHORT);
                    toast.show();
                    return true;

                case R.id.logout:
                    // User chose the "Logout" item, show the app settings UI...
                    mAuth.signOut();
                    Intent intentEmail = new Intent(MainActivity.this, SignInActivity.class);
                    startActivity(intentEmail);
                    toast = Toast.makeText(this, "Logged Out !", Toast.LENGTH_SHORT);
                    toast.show();
                    return true;


                default:
                    // If we got here, the user's action was not recognized.
                    // Invoke the superclass to handle it.
                    return super.onOptionsItemSelected(item);
            }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        // Configure the search info and add any event listeners
        return super.onCreateOptionsMenu(menu);
    }
}
