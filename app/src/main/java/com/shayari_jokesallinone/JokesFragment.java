package com.shayari_jokesallinone;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class JokesFragment extends Fragment implements View.OnClickListener {

    private LinearLayout jcd1,jcd2,jcd3,jcd4,jcd5,jcd6,jcd7,jcd8,jcd9;

    View view;

    public JokesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_jokes, container, false);

        initializeUi();
        cardClickInitialize();
        return view;
    }

    private void cardClickInitialize() {
        jcd1.setOnClickListener(this);
        jcd2.setOnClickListener(this);
        jcd3.setOnClickListener(this);
        jcd4.setOnClickListener(this);
        jcd5.setOnClickListener(this);
        jcd6.setOnClickListener(this);
        jcd7.setOnClickListener(this);
        jcd8.setOnClickListener(this);
        jcd9.setOnClickListener(this);
    }

    private void initializeUi() {
        jcd1 = view.findViewById(R.id.jcd1);
        jcd2 = view.findViewById(R.id.jcd2);
        jcd3 = view.findViewById(R.id.jcd3);
        jcd4 = view.findViewById(R.id.jcd4);
        jcd5 = view.findViewById(R.id.jcd5);
        jcd6 = view.findViewById(R.id.jcd6);
        jcd7 = view.findViewById(R.id.jcd7);
        jcd8 = view.findViewById(R.id.jcd8);
        jcd9 = view.findViewById(R.id.jcd9);

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getActivity(),ShayariActivity.class);
        int id = v.getId();
        switch (id){
            case R.id.jcd1:
                intent.putExtra("Category",  getString (R. string. jcd1));
                break;
            case R.id.jcd2:
                intent.putExtra("Category",  getString (R. string. jcd2));
                break;
            case R.id.jcd3:
                intent.putExtra("Category",  getString (R. string. jcd3));
                break;
            case R.id.jcd4:
                intent.putExtra("Category",  getString (R. string. jcd4));
                break;
            case R.id.jcd5:
                intent.putExtra("Category",  getString (R. string. jcd5));
                break;
            case R.id.jcd6:
                intent.putExtra("Category",  getString (R. string. jcd6));
                break;
            case R.id.jcd7:
                intent.putExtra("Category",  getString (R. string. jcd7));
                break;
            case R.id.jcd8:
                intent.putExtra("Category",  getString (R. string. jcd8));
                break;
            case R.id.jcd9:
                intent.putExtra("Category",  getString (R. string. jcd9));
                break;
        }
        startActivity(intent);
    }
}
