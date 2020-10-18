package com.shayari_jokesallinone;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer;
    NavigationView navigationView;
    FrameLayout frameLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nvView);
        frameLayout = (FrameLayout) findViewById(R.id.flContent);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.drawer_open,  R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //set default fragment
        loadFragment(new HomeFragment());

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == R.id.nav_hindi_fragment) {
                    loadFragment(new HomeFragment());
                } else if(id == R.id.nav_english_fragment) {
                    loadFragment(new EnglishFragment());
                }
                else if(id == R.id.nav_horror_video) {
                    try{
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://bit.ly/2AGrw1H")));
                    }
                    catch (ActivityNotFoundException e){
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else if(id == R.id.nav_Developer_fragment) {
                loadFragment(new DeveloperFragment());
                }
                else if(id == R.id.nav_Rate) {
                    try{
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName())));
                    }
                    catch (ActivityNotFoundException e){
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
                    }
                }
                else if(id == R.id.nav_PrivacyPolicy) {
                    try{
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://dhirajkrchauhan22.blogspot.com/p/privacy-policy-all-category-status.html")));
                    }
                    catch (ActivityNotFoundException e){
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                else if (id==R.id.nav_more_app){
                    try{
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/developer?id=DhirajChauhan")));
                    }
                    catch (ActivityNotFoundException e){
                    }
                }
                else if (id == R.id.nav_share){
                    try {
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                        String shareMessage= "\nLet me recommend you this application\n\n";
                        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        startActivity(Intent.createChooser(shareIntent, "choose one"));
                    } catch(Exception e) {
                        //e.toString();
                    }
                }

                if ((id != R.id.nav_Rate)&& (id != R.id.nav_PrivacyPolicy) && (id != R.id.nav_more_app) && (id != R.id.nav_horror_video) && (id != R.id.nav_share)){
                    // Highlight the selected item has been done by NavigationView
                    menuItem.setChecked(true);
                    // Set action bar title
                    setTitle(menuItem.getTitle());
                }

                drawer.closeDrawer(GravityCompat.START);

                return true;
            }
        });
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flContent, fragment);
        transaction.commit();

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(" Are you sure you want to exit? ")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNeutralButton("Other App ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try{
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/developer?id=DhirajChauhan")));
                        }
                        catch (ActivityNotFoundException e){
                        }
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }}

