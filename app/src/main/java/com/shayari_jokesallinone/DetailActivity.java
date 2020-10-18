package com.shayari_jokesallinone;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shayari_jokesallinone.RecyclerPackage.DbModelClass;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static android.content.ContentValues.TAG;


public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbarDetail;
    private TextView text_view;
    private ImageView sbtn, cbtn;
    private ImageView nxt_btn,prev_btn;
    private LinearLayout lineartxtView;

    private Switch toggle;
    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";
    private boolean useDarkTheme;


    int nxt_count = 0;
    int onHowManyTapInterstitialAdsShow = 6;
    int currentPosition=0;

    private FrameLayout adContainerView;
    private AdView adView;
    private InterstitialAd mInterstitialAd;

    List<DbModelClass> dataList= new ArrayList<DbModelClass>();
    private String banner_ad_unit_id;
    private String interstitialAd_adUnitId;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Use the chosen theme
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if(useDarkTheme) {
            setTheme(R.style.AppTheme_Dark_NoActionBar);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initializeUi();

        //toolbar setting
        setSupportActionBar(toolbarDetail);

        //get data from shayari activity
        String Cat=ShayariActivity.getActivityInstance().getDataForDetailToolbar();

        assert Cat != null;
        Objects.requireNonNull(getSupportActionBar()).setTitle(Cat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbarDetail.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }


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
                    interstitialAd_adUnitId = dataSnapshot.child("interstitial").getValue().toString();
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



        //getting item from recycler/shayariActivity to show in the txtview
        Intent i = getIntent();
         String myJsonData = i.getStringExtra("jsonData");
         currentPosition=i.getIntExtra("position",0);
         Gson gson = new Gson();
        Type type = new TypeToken<List<DbModelClass>>() {
        }.getType();
        dataList= gson.fromJson(myJsonData,type);
        if(!dataList.isEmpty())
        text_view.setText(dataList.get(currentPosition).getShayari());



        sbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // code of share
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,dataList.get(currentPosition).getShayari()); //rQoute value mean which i want to send or share
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent,"Share by");
                startActivity(sendIntent);


            }
        });
        sbtn.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                Toast.makeText(getApplicationContext(), "Share.. " ,Toast.LENGTH_SHORT).show();

                return true;    // set to true
            }
        });

        cbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // code for copy
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("editText",text_view.getText().toString()); //  txt =From where you want to copy
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(getApplicationContext(),"Copied to clipboard..",Toast.LENGTH_SHORT).show();

            }
        });
        cbtn.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                Toast.makeText(getApplicationContext(), "Copy.. " ,Toast.LENGTH_SHORT).show();

                return true;    // set to true
            }
        });



        nxt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(currentPosition!=dataList.size()-1){
                   currentPosition++;
                   randomImageBgForQoutes();
                   nxt_count = nxt_count+1;
               }
               if (nxt_count == onHowManyTapInterstitialAdsShow){
                   if (interstitialAd_adUnitId != null){
                       showInterstitialAds();
                       nxt_count = 0;
                   }

               }
                if (nxt_count > onHowManyTapInterstitialAdsShow)
                    nxt_count =0;
                text_view.setText(dataList.get(currentPosition).getShayari());
            }
        });

        nxt_btn.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                Toast.makeText(getApplicationContext(), "Next.. " ,Toast.LENGTH_SHORT).show();

                return true;    // set to true
            }
        });

        prev_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPosition>0){
                    currentPosition--;
                    randomImageBgForQoutes();
                    nxt_count++;
                }
                if (nxt_count == onHowManyTapInterstitialAdsShow){
                    if (interstitialAd_adUnitId != null){
                        showInterstitialAds();
                        nxt_count = 0;
                    }
                }
                if (nxt_count > onHowManyTapInterstitialAdsShow)
                    nxt_count =0;
                text_view.setText(dataList.get(currentPosition).getShayari());
            }
        });
        prev_btn.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                Toast.makeText(getApplicationContext(), "Previous.. " ,Toast.LENGTH_SHORT).show();

                return true;    // set to true
            }
        });


        //set random image for quotes background
        randomImageBgForQoutes();

        //for theme change
       toggle = (Switch) findViewById(R.id.toggle);
        toggle.setChecked(useDarkTheme);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean isChecked) {
                toggleTheme(isChecked);
            }
        });
        if(toggle.isChecked()){
            toggle.setText("Light Mode");
           // Toast.makeText(this, "Dark Mode  ", Toast.LENGTH_SHORT).show();
        }
        else if(!(toggle.isChecked())){
            toggle.setText("Dark Mode");
           // Toast.makeText(this, "Light Mode  ", Toast.LENGTH_SHORT).show();
        }

    }

    private void setAds() {
        // Initialize the Mobile Ads SDK.
        // Set the adaptive ad
        MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) { }
        });

        //interstitial ads
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(interstitialAd_adUnitId);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        //adaptive banner ads
        adContainerView = findViewById(R.id.ad_view_container_detail);
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

    private void showInterstitialAds(){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();

            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    // Load the next interstitial.
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                }
            });
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }

    }

    public void initializeUi(){

        text_view = findViewById(R.id.textViewDetail);
        sbtn = findViewById(R.id.Share_btn);
        cbtn = findViewById(R.id.Copy_btn);
        nxt_btn = findViewById(R.id.Next_btn);
        prev_btn = findViewById(R.id.Prev_btn);
        toolbarDetail = findViewById(R.id.toolbarDetail);
        lineartxtView  = findViewById(R.id.linear_txtView);

    }

    public void randomImageBgForQoutes(){
        int[] images = {R.drawable.qoutesbg1,R.drawable.qoutesbg2,R.drawable.qoutesbg3,R.drawable.qoutesbg4,R.drawable.qoutesbg5,R.drawable.qoutesbg6,R.drawable.qoutesbg7,R.drawable.qoutesbg8,R.drawable.qoutesbg9,R.drawable.qoutesbg10};
        int random = new Random().nextInt(images.length);
        text_view.setBackgroundDrawable( getResources().getDrawable(images[random]) );
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
                    String shareMessage= "\nI recommend you to download All Category Best Status application.\n\n";
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

    private void toggleTheme(boolean darkTheme) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(PREF_DARK_THEME, darkTheme);
        editor.apply();

        Intent intent = getIntent();
        finish();

        startActivity(intent);
    }

}
