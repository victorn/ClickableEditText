package com.vnidens.clickableedittext.sample;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.vnidens.clickableedittext.ClickableAutoCompleteTextView;
import com.vnidens.clickableedittext.ClickableEditText;
import com.vnidens.clickableedittext.ClickableTextInputEditText;
import com.vnidens.clickableedittext.OnCompoundButtonClickListener;

public class MainActivity extends AppCompatActivity implements OnCompoundButtonClickListener{

    private boolean iconVisible = false;

    private static final String[] AUTOCOMPLETE_VALUES = new String[]{"Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
            "Sunday"};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(com.vnidens.android.clickableedittext.sample.R.layout.activity_main);

        ClickableEditText cet = (ClickableEditText)findViewById(com.vnidens.android.clickableedittext.sample.R.id.cet0);
        ClickableEditText cet1 = (ClickableEditText)findViewById(com.vnidens.android.clickableedittext.sample.R.id.cet1);
        ClickableTextInputEditText ctiet = (ClickableTextInputEditText)findViewById(com.vnidens.android.clickableedittext.sample.R.id.ctiet0);
        ClickableAutoCompleteTextView cactv = (ClickableAutoCompleteTextView)findViewById(com.vnidens.android.clickableedittext.sample.R.id.cactv);

        if(cet != null){
            cet.setOnEndButtonClickListener(this);
        }

        if(ctiet != null){
            ctiet.setOnStartButtonClickListener(this);
            ctiet.setEndButtonTintRes(com.vnidens.android.clickableedittext.sample.R.color.green_700);
        }

        if(cet1 != null){
            cet1.setStartButtonDrawable(ResourcesCompat.getDrawable(getResources(),
                    com.vnidens.android.clickableedittext.sample.R.drawable.ic_error_outline_black_24dp,
                    getTheme()));
            cet1.setEndButtonDrawable(com.vnidens.android.clickableedittext.sample.R.drawable.ic_help_black_24dp);

            cet1.setStartButtonTintRes(com.vnidens.android.clickableedittext.sample.R.color.blue_500);
            cet1.setEndButtonTintList(ResourcesCompat.getColorStateList(getResources(),
                    com.vnidens.android.clickableedittext.sample.R.color.button_tint_list_grey,
                    getTheme()));

            cet1.setOnStartButtonClickListener(this);
            cet1.setOnEndButtonClickListener(this);
        }

        if(cactv != null){
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_dropdown_item_1line,
                    AUTOCOMPLETE_VALUES);
            cactv.setAdapter(adapter);

            cactv.setStartButtonTintList(null);

            cactv.setEndButtonDrawable(com.vnidens.android.clickableedittext.sample.R.drawable.ic_clear_black_24dp);
            cactv.setOnEndButtonClickListener(this);
        }
    }

    @Override
    public void onCompoundButtonClicked(@NonNull EditText view, @IdRes long which){
        switch(view.getId()){
            case com.vnidens.android.clickableedittext.sample.R.id.cet0:
                if(which == com.vnidens.android.clickableedittext.sample.R.id.cet_button_end){
                    Toast.makeText(MainActivity.this,
                            "ClickableEditText1 :: End button clicked",
                            Toast.LENGTH_SHORT)
                            .show();

                    ClickableEditText cet = (ClickableEditText)view;

                    cet.setEndButtonDrawable(ResourcesCompat.getDrawable(getResources(),
                            iconVisible ? com.vnidens.android.clickableedittext.sample.R.drawable.ic_visibility_black_24dp : com.vnidens.android.clickableedittext.sample.R.drawable.ic_visibility_off_black_24dp,
                            getTheme()));

                    iconVisible = !iconVisible;
                }
                break;

            case com.vnidens.android.clickableedittext.sample.R.id.ctiet0:
                if(which == com.vnidens.android.clickableedittext.sample.R.id.cet_button_start){
                    Toast.makeText(MainActivity.this,
                            "ClickableTextInputEditText1 :: Start button clicked",
                            Toast.LENGTH_SHORT)
                            .show();
                }
                break;

            case com.vnidens.android.clickableedittext.sample.R.id.cet1:
                if(which == com.vnidens.android.clickableedittext.sample.R.id.cet_button_start){
                    Toast.makeText(MainActivity.this,
                            "ClickableEditText2 :: Start button clicked",
                            Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(MainActivity.this,
                            "ClickableEditText2 :: End button clicked",
                            Toast.LENGTH_SHORT)
                            .show();
                }

                break;

            case com.vnidens.android.clickableedittext.sample.R.id.cactv:
                if(which == com.vnidens.android.clickableedittext.sample.R.id.cet_button_end){
                    view.setText("");
                }
                break;
            default:
                break;
        }
    }
}
