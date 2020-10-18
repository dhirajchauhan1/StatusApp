package com.shayari_jokesallinone;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeveloperFragment extends Fragment {
    View view;
    private TextView developerDesc,textViewInsta,textViewLinkedin;

    public DeveloperFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_developer, container, false);

        developerDesc = view.findViewById(R.id.developer_Description);
        textViewInsta = view.findViewById(R.id.txtInsta);
        textViewLinkedin = view.findViewById(R.id.txtLinkedin);


        String txt = " Hi, my name is Dhiraj Chauhan and I am the man behind this app.\n" +
                "\n" +
                "if you have any suggestions or feedback feel free to message me. I will reply to everyone. :)\n" +
                "\n" +
                "Do drop me a Hi.. , it really means a lot to me.Looking forward to hear from you.\n " +
                "\n"+
                "Thank you...";

        SpannableString spannableString = new SpannableString(txt);

        // ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.RED);// for color change

        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        StyleSpan italicSpan = new StyleSpan(Typeface.ITALIC);
        StyleSpan boldItalicSpan = new StyleSpan(Typeface.BOLD_ITALIC);
        UnderlineSpan underlineSpan = new UnderlineSpan();
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();

        spannableString.setSpan(boldItalicSpan,16,30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        developerDesc.setText(spannableString);

        //link for insta
          textViewInsta.setMovementMethod(LinkMovementMethod.getInstance());

        //link for linkedin
         textViewLinkedin.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }
}
