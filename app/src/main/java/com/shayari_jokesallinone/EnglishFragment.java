package com.shayari_jokesallinone;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnglishFragment extends Fragment implements View.OnClickListener{

    private CardView ecd1,ecd2,ecd3,ecd4,ecd5, ecd6,ecd7,ecd8,ecd9,ecd10, ecd11,ecd12,ecd13,ecd14,ecd15, ecd16,ecd17,ecd18,ecd19,ecd20,ecd21,ecd22,ecd23,ecd24,ecd25, ecd26,ecd27,ecd28,ecd29,ecd30, ecd31,ecd32,ecd33,ecd34,ecd35, ecd36,ecd37,ecd38,ecd39,ecd40,ecd41,ecd42,ecd43,ecd44,ecd45, ecd46,ecd47,ecd48,ecd49,ecd50,ecd51,ecd52,ecd53,ecd54;
    View v;
    private FrameLayout adContainerView;
    private AdView adView;
    static EnglishFragment INSTANCE;
    public static String  data;
    private String banner_ad_unit_id;

    //paas data to detail activity without intent
    public static EnglishFragment getActivityInstance()
    {
        return INSTANCE;
    }

    public static String get()
    {
        return data;
    }

    public EnglishFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_english, container, false);

        initializeUi();
        cardClickInitialize();

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

        return v;
    }

    private void setAds() {
        // Initialize the Mobile Ads SDK.
        // Set the adaptive ad
        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) { }
        });
        adContainerView = v.findViewById(R.id.ad_view_container_english);
        // Step 1 - Create an AdView and set the ad unit ID on it.
        adView = new AdView(getContext());
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
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(getContext(), adWidth);
    }

    public void initializeUi(){
        //cardview
        ecd1=  v.findViewById(R.id.ecd1);
        ecd2=  v.findViewById(R.id.ecd2);
        ecd3=  v.findViewById(R.id.ecd3);
        ecd4=  v.findViewById(R.id.ecd4);
        ecd5=  v.findViewById(R.id.ecd5);
        ecd6=  v.findViewById(R.id.ecd6);
        ecd7=  v.findViewById(R.id.ecd7);
        ecd8=  v.findViewById(R.id.ecd8);
        ecd9=  v.findViewById(R.id.ecd9);
        ecd10=  v.findViewById(R.id.ecd10);
        ecd11=  v.findViewById(R.id.ecd11);
        ecd12=  v.findViewById(R.id.ecd12);
        ecd13=  v.findViewById(R.id.ecd13);
        ecd14=  v.findViewById(R.id.ecd14);
        ecd15=  v.findViewById(R.id.ecd15);
        ecd16=  v.findViewById(R.id.ecd16);
        ecd17=  v.findViewById(R.id.ecd17);
        ecd18=  v.findViewById(R.id.ecd18);
        ecd19=  v.findViewById(R.id.ecd19);
        ecd20=  v.findViewById(R.id.ecd20);
        ecd21=  v.findViewById(R.id.ecd21);
        ecd22=  v.findViewById(R.id.ecd22);
        ecd23=  v.findViewById(R.id.ecd23);
        ecd24=  v.findViewById(R.id.ecd24);
        ecd25=  v.findViewById(R.id.ecd25);
        ecd26=  v.findViewById(R.id.ecd26);
        ecd27=  v.findViewById(R.id.ecd27);
        ecd28=  v.findViewById(R.id.ecd28);
        ecd29=  v.findViewById(R.id.ecd29);
        ecd30=  v.findViewById(R.id.ecd30);
        ecd31=  v.findViewById(R.id.ecd31);
        ecd32=  v.findViewById(R.id.ecd32);
        ecd33=  v.findViewById(R.id.ecd33);
        ecd34=  v.findViewById(R.id.ecd34);
        ecd35=  v.findViewById(R.id.ecd35);
        ecd36=  v.findViewById(R.id.ecd36);
        ecd37=  v.findViewById(R.id.ecd37);
        ecd38=  v.findViewById(R.id.ecd38);
        ecd39=  v.findViewById(R.id.ecd39);
        ecd40=  v.findViewById(R.id.ecd40);
        ecd41=  v.findViewById(R.id.ecd41);
        ecd42=  v.findViewById(R.id.ecd42);
        ecd43=  v.findViewById(R.id.ecd43);
        ecd44=  v.findViewById(R.id.ecd44);
        ecd45=  v.findViewById(R.id.ecd45);
        ecd46=  v.findViewById(R.id.ecd46);
        ecd47=  v.findViewById(R.id.ecd47);
        ecd48=  v.findViewById(R.id.ecd48);
        ecd49=  v.findViewById(R.id.ecd49);
        ecd50=  v.findViewById(R.id.ecd50);
        ecd51=  v.findViewById(R.id.ecd51);
        ecd52=  v.findViewById(R.id.ecd52);
        ecd53=  v.findViewById(R.id.ecd53);
        ecd54=  v.findViewById(R.id.ecd54);
        
    }

    public void cardClickInitialize(){

        ecd1.setOnClickListener(this);
        ecd2.setOnClickListener(this);
        ecd3.setOnClickListener(this);
        ecd4.setOnClickListener(this);
        ecd5.setOnClickListener(this);
        ecd6.setOnClickListener(this);
        ecd7.setOnClickListener(this);
        ecd8.setOnClickListener(this);
        ecd9.setOnClickListener(this);
        ecd10.setOnClickListener(this);
        ecd11.setOnClickListener(this);
        ecd12.setOnClickListener(this);
        ecd13.setOnClickListener(this);
        ecd14.setOnClickListener(this);
        ecd15.setOnClickListener(this);
        ecd16.setOnClickListener(this);
        ecd17.setOnClickListener(this);
        ecd18.setOnClickListener(this);
        ecd19.setOnClickListener(this);
        ecd20.setOnClickListener(this);
        ecd21.setOnClickListener(this);
        ecd22.setOnClickListener(this);
        ecd23.setOnClickListener(this);
        ecd24.setOnClickListener(this);
        ecd25.setOnClickListener(this);
        ecd26.setOnClickListener(this);
        ecd27.setOnClickListener(this);
        ecd28.setOnClickListener(this);
        ecd29.setOnClickListener(this);
        ecd30.setOnClickListener(this);
        ecd31.setOnClickListener(this);
        ecd32.setOnClickListener(this);
        ecd33.setOnClickListener(this);
        ecd34.setOnClickListener(this);
        ecd35.setOnClickListener(this);
        ecd36.setOnClickListener(this);
        ecd37.setOnClickListener(this);
        ecd38.setOnClickListener(this);
        ecd39.setOnClickListener(this);
        ecd40.setOnClickListener(this);
        ecd41.setOnClickListener(this);
        ecd42.setOnClickListener(this);
        ecd43.setOnClickListener(this);
        ecd44.setOnClickListener(this);
        ecd45.setOnClickListener(this);
        ecd46.setOnClickListener(this);
        ecd47.setOnClickListener(this);
        ecd48.setOnClickListener(this);
        ecd49.setOnClickListener(this);
        ecd50.setOnClickListener(this);
        ecd51.setOnClickListener(this);
        ecd52.setOnClickListener(this);
        ecd53.setOnClickListener(this);
        ecd54.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent swtch = new Intent(getActivity(),ShayariActivity.class);
        int id = v.getId();
        switch (id) {
            case R.id.ecd1:
                swtch.putExtra("Category",  getString (R. string. ecd1));
                data = "ecd1";
                break;
            case R.id.ecd2:
                swtch.putExtra("Category",  getString (R. string. ecd2));
                data = "ecd2";
                break;
            case R.id.ecd3:
                swtch.putExtra("Category",  getString (R. string. ecd3));
                data = "ecd3";
                break;
            case R.id.ecd4:
                swtch.putExtra("Category",  getString (R. string. ecd4));
                data = "ecd4";
                break;
            case R.id.ecd5:
                swtch.putExtra("Category",  getString (R. string. ecd5));
                data = "ecd5";
                break;
            case R.id.ecd6:
                swtch.putExtra("Category",  getString (R. string. ecd6));
                data = "ecd6";
                break;
            case R.id.ecd7:
                swtch.putExtra("Category",  getString (R. string. ecd7));
                data = "ecd7";
                break;
            case R.id.ecd8:
                swtch.putExtra("Category",  getString (R. string. ecd8));
                data = "ecd8";
                break;
            case R.id.ecd9:
                swtch.putExtra("Category",  getString (R. string. ecd9));
                data = "ecd9";
                break;
            case R.id.ecd10:
                swtch.putExtra("Category",  getString (R. string. ecd10));
                data = "ecd10";
                break;
            case R.id.ecd11:
                swtch.putExtra("Category",  getString (R. string. ecd11));
                data = "ecd11";
                break;
            case R.id.ecd12:
                swtch.putExtra("Category",  getString (R. string. ecd12));
                data = "ecd12";
                break;
            case R.id.ecd13:
                swtch.putExtra("Category",  getString (R. string. ecd13));
                data = "ecd13";
                break;
            case R.id.ecd14:
                swtch.putExtra("Category",  getString (R. string. ecd14));
                data = "ecd14";
                break;
            case R.id.ecd15:
                swtch.putExtra("Category",  getString (R. string. ecd15));
                data = "ecd15";
                break;
            case R.id.ecd16:
                swtch.putExtra("Category",  getString (R. string. ecd16));
                data = "ecd16";
                break;
            case R.id.ecd17:
                swtch.putExtra("Category",  getString (R. string. ecd17));
                data = "ecd17";
                break;
            case R.id.ecd18:
                swtch.putExtra("Category",  getString (R. string. ecd18));
                data = "ecd18";
                break;
            case R.id.ecd19:
                swtch.putExtra("Category",  getString (R. string. ecd19));
                data = "ecd19";
                break;
            case R.id.ecd20:
                swtch.putExtra("Category",  getString (R. string. ecd20));
                data = "ecd20";
                break;
            case R.id.ecd21:
                swtch.putExtra("Category",  getString (R. string. ecd21));
                data = "ecd21";
                break;
            case R.id.ecd22:
                swtch.putExtra("Category",  getString (R. string. ecd22));
                data = "ecd22";
                break;
            case R.id.ecd23:
                swtch.putExtra("Category",  getString (R. string. ecd23));
                data = "ecd23";
                break;
            case R.id.ecd24:
                swtch.putExtra("Category",  getString (R. string. ecd24));
                data = "ecd24";
                break;
            case R.id.ecd25:
                swtch.putExtra("Category",  getString (R. string. ecd25));
                data = "ecd25";
                break;
            case R.id.ecd26:
                swtch.putExtra("Category",  getString (R. string. ecd26));
                data = "ecd26";
                break;
            case R.id.ecd27:
                swtch.putExtra("Category",  getString (R. string. ecd27));
                data = "ecd27";
                break;
            case R.id.ecd28:
                swtch.putExtra("Category",  getString (R. string. ecd28));
                data = "ecd28";
                break;
            case R.id.ecd29:
                swtch.putExtra("Category",  getString (R. string. ecd29));
                data = "ecd29";
                break;
            case R.id.ecd30:
                swtch.putExtra("Category",  getString (R. string. ecd30));
                data = "ecd30";
                break;
            case R.id.ecd31:
                swtch.putExtra("Category",  getString (R. string. ecd31));
                data = "ecd31";
                break;
            case R.id.ecd32:
                swtch.putExtra("Category",  getString (R. string. ecd32));
                data = "ecd32";
                break;
            case R.id.ecd33:
                swtch.putExtra("Category",  getString (R. string. ecd33));
                data = "ecd33";
                break;
            case R.id.ecd34:
                swtch.putExtra("Category",  getString (R. string. ecd34));
                data = "ecd34";
                break;
            case R.id.ecd35:
                swtch.putExtra("Category",  getString (R. string. ecd35));
                data = "ecd35";
                break;
            case R.id.ecd36:
                swtch.putExtra("Category",  getString (R. string. ecd36));
                data = "ecd36";
                break;
            case R.id.ecd37:
                swtch.putExtra("Category",  getString (R. string. ecd37));
                data = "ecd37";
                break;
            case R.id.ecd38:
                swtch.putExtra("Category",  getString (R. string. ecd38));
                data = "ecd38";
                break;
            case R.id.ecd39:
                swtch.putExtra("Category",  getString (R. string. ecd39));
                data = "ecd39";
                break;
            case R.id.ecd40:
                swtch.putExtra("Category",  getString (R. string. ecd40));
                data = "ecd40";
                break;
            case R.id.ecd41:
                swtch.putExtra("Category",  getString (R. string. ecd41));
                data = "ecd41";
                break;
            case R.id.ecd42:
                swtch.putExtra("Category",  getString (R. string. ecd42));
                data = "ecd42";
                break;
            case R.id.ecd43:
                swtch.putExtra("Category",  getString (R. string. ecd43));
                data = "ecd43";
                break;
            case R.id.ecd44:
                swtch.putExtra("Category",  getString (R. string. ecd44));
                data = "ecd44";
                break;
            case R.id.ecd45:
                swtch.putExtra("Category",  getString (R. string. ecd45));
                data = "ecd45";
                break;
            case R.id.ecd46:
                swtch.putExtra("Category",  getString (R. string. ecd46));
                data = "ecd46";
                break;
            case R.id.ecd47:
                swtch.putExtra("Category",  getString (R. string. ecd47));
                data = "ecd47";
                break;
            case R.id.ecd48:
                swtch.putExtra("Category",  getString (R. string. ecd48));
                data = "ecd48";
                break;
            case R.id.ecd49:
                swtch.putExtra("Category",  getString (R. string. ecd49));
                data = "ecd49";
                break;
            case R.id.ecd50:
                swtch.putExtra("Category",  getString (R. string. ecd50));
                data = "ecd50";
                break;
            case R.id.ecd51:
                swtch.putExtra("Category",  getString (R. string. ecd51));
                data = "ecd51";
                break;
            case R.id.ecd52:
                swtch.putExtra("Category",  getString (R. string. ecd52));
                data = "ecd52";
                break;
            case R.id.ecd53:
                swtch.putExtra("Category",  getString (R. string. ecd53));
                data = "ecd53";
                break;
            case R.id.ecd54:
                swtch.putExtra("Category",  getString (R. string. ecd54));
                data = "ecd54";
                break;
        }
        startActivity(swtch);
        
    }
}
