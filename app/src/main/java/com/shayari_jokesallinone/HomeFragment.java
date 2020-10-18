package com.shayari_jokesallinone;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    private CardView card1 , card2 , card3 , card4 , card5 ,card6,card7,card8,card9,card10,card11,card12,card13,card14,card15,card16,card17,card18,card19,card20,card21,card22,card23,card24,card25,card26,card27,card28;

    View v;
    private FrameLayout adContainerView;
    private AdView adView;
    String banner_ad_unit_id;

    static HomeFragment INSTANCE;
    public static String  data;
    //paas data to detail activity without intent
    public static HomeFragment getActivityInstance()
    {
        return INSTANCE;
    }
    public static String get()
    {
        return data;
    }


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);

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
        adContainerView = v.findViewById(R.id.ad_view_container_hindi);
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
        card1=  v.findViewById(R.id.card1);
        card2=  v.findViewById(R.id.card2);
        card3=  v.findViewById(R.id.card3);
        card4=  v.findViewById(R.id.card4);
        card5=  v.findViewById(R.id.card5);
        card6=  v.findViewById(R.id.card6);
        card7=  v.findViewById(R.id.card7);
        card8=  v.findViewById(R.id.card8);
        card9=  v.findViewById(R.id.card9);
        card10= v.findViewById(R.id.card10);
        card11= v.findViewById(R.id.card11);
        card12= v.findViewById(R.id.card12);
        card13= v.findViewById(R.id.card13);
        card14= v.findViewById(R.id.card14);
        card15= v.findViewById(R.id.card15);
        card16= v.findViewById(R.id.card16);
        card17= v.findViewById(R.id.card17);
        card18= v.findViewById(R.id.card18);
        card19= v.findViewById(R.id.card19);
        card20= v.findViewById(R.id.card20);
        card21= v.findViewById(R.id.card21);
        card22= v.findViewById(R.id.card22);
        card23= v.findViewById(R.id.card23);
        card24= v.findViewById(R.id.card24);
        card25= v.findViewById(R.id.card25);
        card26= v.findViewById(R.id.card26);
        card27= v.findViewById(R.id.card27);
        card28= v.findViewById(R.id.card28);
    }

    public void cardClickInitialize(){

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);
        card7.setOnClickListener(this);
        card8.setOnClickListener(this);
        card9.setOnClickListener(this);
        card10.setOnClickListener(this);
        card11.setOnClickListener(this);
        card12.setOnClickListener(this);
        card13.setOnClickListener(this);
        card14.setOnClickListener(this);
        card15.setOnClickListener(this);
        card16.setOnClickListener(this);
        card17.setOnClickListener(this);
        card18.setOnClickListener(this);
        card19.setOnClickListener(this);
        card20.setOnClickListener(this);
        card21.setOnClickListener(this);
        card22.setOnClickListener(this);
        card23.setOnClickListener(this);
        card24.setOnClickListener(this);
        card25.setOnClickListener(this);
        card26.setOnClickListener(this);
        card27.setOnClickListener(this);
        card28.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent swtch = new Intent(getActivity(),ShayariActivity.class);
        int id = v.getId();
        switch (id) {
            case R.id.card1:
                swtch.putExtra("Category",  getString (R. string. card1));
                data = "card1";
                break;
            case R.id.card2:
                swtch.putExtra("Category",  getString (R. string. card2));
                data = "card2";
                break;
            case R.id.card3:
                swtch.putExtra("Category",  getString (R. string. card3));
                data = "card3";
                break;
            case R.id.card4:
                swtch.putExtra("Category",  getString (R. string. card4));
                data = "card4";
                break;
            case R.id.card5:
                swtch.putExtra("Category",  getString (R. string. card5));
                data = "card5";
                break;
            case R.id.card6:
                swtch.putExtra("Category",  getString (R. string. card6));
                data = "card6";
                break;
            case R.id.card7:
                swtch.putExtra("Category",  getString (R. string. card7));
                data = "card7";
                break;
            case R.id.card8:
                swtch.putExtra("Category",  getString (R. string. card8));
                data = "card8";
                break;
            case R.id.card9:
                swtch.putExtra("Category",  getString (R. string. card9));
                data = "card9";
                break;
            case R.id.card10:
                swtch.putExtra("Category",  getString (R. string. card10));
                data = "card10";
                break;
            case R.id.card11:
                swtch.putExtra("Category",  getString (R. string. card11));
                data = "card11";
                break;
            case R.id.card12:
                swtch.putExtra("Category",  getString (R. string. card12));
                data = "card12";
                break;
            case R.id.card13:
                swtch.putExtra("Category",  getString (R. string. card13));
                data = "card13";
                break;
            case R.id.card14:
                swtch.putExtra("Category",  getString (R. string. card14));
                data = "card14";
                break;
            case R.id.card15:
                swtch.putExtra("Category",  getString (R. string. card15));
                data = "card15";
                break;
            case R.id.card16:
                swtch.putExtra("Category",  getString (R. string. card16));
                data = "card16";
                break;
            case R.id.card17:
                swtch.putExtra("Category",  getString (R. string. card17));
                data = "card17";
                break;
            case R.id.card18:
                swtch.putExtra("Category",  getString (R. string. card18));
                data = "card18";
                break;
            case R.id.card19:
                swtch.putExtra("Category",  getString (R. string. card19));
                data = "card19";
                break;
            case R.id.card20:
                swtch.putExtra("Category",  getString (R. string. card20));
                data = "card20";
                break;
            case R.id.card21:
                swtch.putExtra("Category",  getString (R. string. card21));
                data = "card21";
                break;
            case R.id.card22:
                swtch.putExtra("Category",  getString (R. string. card22));
                data = "card22";
                break;
            case R.id.card23:
                swtch.putExtra("Category",  getString (R. string. card23));
                data = "card23";
                break;
            case R.id.card24:
                swtch.putExtra("Category",  getString (R. string. card24));
                data = "card24";
                break;
            case R.id.card25:
                swtch.putExtra("Category",  getString (R. string. card25));
                data = "card25";
                break;
            case R.id.card26:
                swtch.putExtra("Category",  getString (R. string. card26));
                data = "card26";
                break;
            case R.id.card27:
                swtch.putExtra("Category",  getString (R. string. card27));
                data = "card27";
                break;
            case R.id.card28:
                swtch.putExtra("Category",  getString (R. string. card28));
                data = "card28";
                break;
        }
        startActivity(swtch);
        //animation for sliding activity
       // getActivity().overridePendingTransition(R.anim.open_animation,R.anim.exit_animation);
    }
}
