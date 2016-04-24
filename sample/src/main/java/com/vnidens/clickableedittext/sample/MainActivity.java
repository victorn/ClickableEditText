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
        setContentView(R.layout.activity_main);

        ClickableEditText cet = (ClickableEditText)findViewById(R.id.cet0);
        ClickableEditText cet1 = (ClickableEditText)findViewById(R.id.cet1);
        ClickableTextInputEditText ctiet = (ClickableTextInputEditText)findViewById(R.id.ctiet0);
        ClickableAutoCompleteTextView cactv = (ClickableAutoCompleteTextView)findViewById(R.id.cactv);

        if(cet != null){
            cet.setOnEndButtonClickListener(this);
        }

        if(ctiet != null){
            ctiet.setOnStartButtonClickListener(this);
            ctiet.setEndButtonTintRes(R.color.button_tint_list_green);
        }

        if(cet1 != null){
            cet1.setStartButtonDrawable(ResourcesCompat.getDrawable(getResources(),
                    R.drawable.ic_error_outline_black_24dp,
                    getTheme()));
            cet1.setEndButtonDrawable(R.drawable.ic_help_black_24dp);

            cet1.setStartButtonTintRes(R.color.blue_500);
            cet1.setEndButtonTintList(ResourcesCompat.getColorStateList(getResources(),
                    R.color.button_tint_list_grey,
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

            cactv.setEndButtonDrawable(R.drawable.ic_clear_black_24dp);
            cactv.setOnEndButtonClickListener(this);
        }
    }

    @Override
    public void onCompoundButtonClicked(@NonNull EditText view, @IdRes long which){
        switch(view.getId()){
            case R.id.cet0:
                if(which == R.id.cet_button_end){
                    Toast.makeText(MainActivity.this,
                            R.string.end_button_clicked,
                            Toast.LENGTH_SHORT)
                            .show();

                    ClickableEditText cet = (ClickableEditText)view;

                    cet.setEndButtonDrawable(ResourcesCompat.getDrawable(getResources(),
                            iconVisible ? R.drawable.ic_visibility_black_24dp : R.drawable.ic_visibility_off_black_24dp,
                            getTheme()));

                    iconVisible = !iconVisible;
                }
                break;

            case R.id.ctiet0:
                if(which == R.id.cet_button_start){
                    Toast.makeText(MainActivity.this,
                            R.string.start_button_clicked,
                            Toast.LENGTH_SHORT)
                            .show();
                }
                break;

            case R.id.cet1:
                if(which == com.vnidens.clickableedittext.sample.R.id.cet_button_start){
                    Toast.makeText(MainActivity.this,
                            R.string.start_button_clicked,
                            Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(MainActivity.this,
                            R.string.end_button_clicked,
                            Toast.LENGTH_SHORT)
                            .show();
                }

                break;

            case R.id.cactv:
                if(which == com.vnidens.clickableedittext.sample.R.id.cet_button_end){
                    view.setText("");
                }
                break;
            default:
                break;
        }
    }
}
