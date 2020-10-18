package com.shayari_jokesallinone;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.shayari_jokesallinone.DatabasePackage.ShayariDatabaseClass;
import com.shayari_jokesallinone.RecyclerPackage.DbAdapter;
import com.shayari_jokesallinone.RecyclerPackage.DbModelClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.content.ContentValues.TAG;

public class ShayariActivity extends AppCompatActivity implements ShayariOnClickListener {

    ShayariDatabaseClass objShayariDatabaseClass;
    List<DbModelClass> objDbModelClassArrayList;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    String cat;
    private FrameLayout adContainerView;
    private AdView adView;

    static ShayariActivity INSTANCE;
    public static String dataForDatabase,toolbarDataForDetail;
    private String banner_ad_unit_id;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shayari);

        recyclerView = findViewById(R.id.recycler_shayari);
        toolbar = findViewById(R.id.toolbar_shayari);

        cat = getIntent().getStringExtra("Category");

        toolbarDataForDetail = cat;

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(cat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

       switch (cat){
           case "2 Line Shayari":
               dataForDatabase = "twoline";
               break;
           case "Attitude Shayari":
               dataForDatabase = "attitude";
               break;
           case "Beauty Shayari":
               dataForDatabase = "beauty";
               break;
           case "Bewafa Shayari":
               dataForDatabase = "bewafa";
               break;
           case "Birthday Shayari":
               dataForDatabase = "birthday";
               break;
           case "Dard Shayari":
               dataForDatabase = "dard";
               break;
           case "Desh Bhakti Shayari":
               dataForDatabase = "desh";
               break;
           case "Dil Shayari":
               dataForDatabase = "dil";
               break;
           case "Dosti Shayari":
               dataForDatabase = "dosti";
               break;
           case "Festival Shayari":
               dataForDatabase = "festival";
               break;
           case "Funny Shayari":
               dataForDatabase = "funny";
               break;
           case "Good Morning Shayari":
               dataForDatabase = "goodmorning";
               break;
           case "Good Night Shayari":
               dataForDatabase = "goodnight";
               break;
           case "Heart Touching Shayari":
               dataForDatabase = "hearttouching";
               break;
           case "Hindi Poems Poetry":
               dataForDatabase = "hindipoems";
               break;
           case "Hindutva Shayari":
               dataForDatabase = "hindutva";
               break;
           case "Life Shayari":
               dataForDatabase = "life";
               break;
           case "Love Shayari":
               dataForDatabase = "love";
               break;
           case "Miss You Shayari":
               dataForDatabase = "missyou";
               break;
           case "Motivational Shayari":
               dataForDatabase = "motivational";
               break;
           case "Punjabi Shayari":
               dataForDatabase = "punjabi";
               break;
           case "Rain-Barish Shayari":
               dataForDatabase = "rainbarish";
               break;
           case "Romantic Shayari":
               dataForDatabase = "romantic";
               break;
           case "Sad Shayari":
               dataForDatabase = "sad";
               break;
           case "Sharabi Shayari":
               dataForDatabase = "sharabi";
               break;
           case "Sorry Shayari":
               dataForDatabase = "sorry";
               break;
           case "True Shayari":
               dataForDatabase = "true";
               break;
           case "Yaad Shayari":
               dataForDatabase = "yaad";
               break;
           case "Alone Status":
               dataForDatabase = "engalone";
               break;
           case "Angry Status":
               dataForDatabase = "engangry";
               break;
           case "Anniversary Status":
               dataForDatabase = "enganniversary";
               break;
           case "Attitude Status":
               dataForDatabase = "engattitude";
               break;
           case "Army Status":
               dataForDatabase = "engarmy";
               break;
           case "Birthday Status":
               dataForDatabase = "engbirthday";
               break;
           case "BreakUp Status":
               dataForDatabase = "engbreakup";
               break;
           case "Brother Status":
               dataForDatabase = "engbrother";
               break;
           case "Busy Status":
               dataForDatabase = "engbusy";
               break;
           case "Comedy Status":
               dataForDatabase = "engcomedy";
               break;
           case "Cool Status":
               dataForDatabase = "engcool";
               break;
           case "Couple Status":
               dataForDatabase = "engcouple";
               break;
           case "Cry Status":
               dataForDatabase = "engcry";
               break;
           case "DP Status":
               dataForDatabase = "engdp";
               break;
           case "Diwali Status":
               dataForDatabase = "engdiwali";
               break;
           case "Ego Status":
               dataForDatabase = "engego";
               break;
           case "Engagement Status":
               dataForDatabase = "engengagement";
               break;
           case "Emotional Status":
               dataForDatabase = "engemotional";
               break;
           case "Exam Status":
               dataForDatabase = "engexam";
               break;
           case "Friendship Status":
               dataForDatabase = "engfriendship";
               break;
           case "Facebook Status":
               dataForDatabase = "engfacebook";
               break;
           case "Flirty Status":
               dataForDatabase = "engflirty";
               break;
           case "Family Status":
               dataForDatabase = "engfamily";
               break;
           case "Good Morning Status":
               dataForDatabase = "enggoodmorning";
               break;
           case "Good Night Status":
               dataForDatabase = "enggoodnight";
               break;
           case "Gym Status":
               dataForDatabase = "enggym";
               break;
           case "God Status":
               dataForDatabase = "enggod";
               break;
           case "Girl Status":
               dataForDatabase = "enggirl";
               break;
           case "Happiness Status":
               dataForDatabase = "enghappiness";
               break;
           case "Heart Break Status":
               dataForDatabase = "engheartbreak";
               break;
           case "Heart Touching Status":
               dataForDatabase = "enghearttouching";
               break;
           case "Hate Status":
               dataForDatabase = "enghate";
               break;
           case "Inspirational Status":
               dataForDatabase = "enginspirational";
               break;
           case "Jealous Status":
               dataForDatabase = "engjealous";
               break;
           case "Life Status":
               dataForDatabase = "englife";
               break;
           case "Love Status":
               dataForDatabase = "englove";
               break;
           case "Lonely Status":
               dataForDatabase = "englonely";
               break;
           case "Marriage Status":
               dataForDatabase = "engmarriage";
               break;
           case "Missing Status":
               dataForDatabase = "engmissing";
               break;
           case "Motivational Status":
               dataForDatabase = "engmotivational";
               break;
           case "Nature Status":
               dataForDatabase = "engnature";
               break;
           case "Personality Status":
               dataForDatabase = "engpersonality";
               break;
           case "Problem Status":
               dataForDatabase = "engproblem";
               break;
           case "Party Status":
               dataForDatabase = "engparty";
               break;
           case "Rain Status":
               dataForDatabase = "engrain";
               break;
           case "Relationship Status":
               dataForDatabase = "engrelationship";
               break;
           case "Romantic Status":
               dataForDatabase = "engromantic";
               break;
           case "Rude Status":
               dataForDatabase = "engrude";
               break;
           case "Sarcastic Status":
               dataForDatabase = "engsarcastic";
               break;
           case "Sad Status":
               dataForDatabase = "engsad";
               break;
           case "Single Status":
               dataForDatabase = "engsingle";
               break;
           case "Wedding Status":
               dataForDatabase = "engwedding";
               break;
           case "Whatsapp Status":
               dataForDatabase = "engwhatsapp";
               break;
           case "Weekend Status":
               dataForDatabase = "engweekend";
               break;
           case "Hindi Jokes":
               dataForDatabase = "jkhindi";
               break;
           case "Funny Jokes":
               dataForDatabase = "engweekend";
               break;
           case "Santa-Banta Jokes":
               dataForDatabase = "engweekend";
               break;
           case "Pati-Patni Jokes":
               dataForDatabase = "engweekend";
               break;
           case "Teacher-Students Jokes":
               dataForDatabase = "engweekend";
               break;
           case "Friends Jokes":
               dataForDatabase = "engweekend";
               break;
           case "Aunty Jokes":
               dataForDatabase = "engweekend";
               break;
           case "Aalia Jokes":
               dataForDatabase = "engweekend";
               break;
           case "Rajnikant Jokes":
               dataForDatabase = "engweekend";
               break;
       }


        objDbModelClassArrayList =  new ArrayList<>();
        objShayariDatabaseClass = new ShayariDatabaseClass(this);
        showData();

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                try {
                    banner_ad_unit_id = dataSnapshot.child("banner").getValue().toString();
                    setAds();
                }
                catch (Exception e){
                    Log.d("banner", "null");
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    private void setAds() {
        // Initialize the Mobile Ads SDK.
        // Set the adaptive ad
        MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) { }
        });
        adContainerView = findViewById(R.id.ad_view_container_shayari);
        // Step 1 - Create an AdView and set the ad unit ID on it.
        adView = new AdView(this);
        adView.setAdUnitId(banner_ad_unit_id);
        adContainerView.addView(adView);
        loadBanner();

    }

    private void loadBanner() {
        // Create an ad request. Check your logcat output for the hashed device ID
        // to get test ads on a physical device, e.g.,
        // "Use AdRequest.Builder.addTestDevice("ABCDE0123") to get test ads on this
        // device."
        AdRequest adRequest =
                new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                        .build();

        AdSize adSize = getAdSize();
        // Step 4 - Set the adaptive ad size on the ad view.
        adView.setAdSize(adSize);

        // Step 5 - Start loading the ad in the background.
        adView.loadAd(adRequest);
    }

    private AdSize getAdSize() {
        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(getApplicationContext(), adWidth);
    }


    //paas data to detail activity without intent
    public static ShayariActivity getActivityInstance()
    {
        return INSTANCE;
    }

    public static String get()
    {
        return dataForDatabase;
    }

    public static String getDataForDetailToolbar()
    {
        return toolbarDataForDetail;
    }

    public void showData() {

        try {
            objDbModelClassArrayList = objShayariDatabaseClass.getAllData();
            DbAdapter objDbAdapter = new DbAdapter(objDbModelClassArrayList,this);

            recyclerView.hasFixedSize();
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(objDbAdapter);
        }
        catch (Exception e){

            Toast.makeText(this, "Show data "+ e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClicked(int position, List<DbModelClass> data) {
        Gson gson = new Gson();
        String myJsonData = gson.toJson(data);
        Log.e("CLICK",myJsonData);
        Intent intent= new Intent(this, DetailActivity.class);
        intent.putExtra("jsonData",myJsonData);
        intent.putExtra("position",position);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_right_corner_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.nav_Rate:
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName())));
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
                }
                return true;

            case R.id.nav_more_app:
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/developer?id=DhirajChauhan")));
                }
                catch (ActivityNotFoundException e){
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                return true;

            case R.id.nav_share:
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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

