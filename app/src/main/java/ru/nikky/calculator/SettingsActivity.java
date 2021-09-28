package ru.nikky.calculator;

import android.os.Bundle;

import com.google.android.material.radiobutton.MaterialRadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


public class SettingsActivity extends AppCompatActivity {

    private MaterialRadioButton lightThemeRadioButton;
    private MaterialRadioButton darkThemeRadioButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initViews();
        setupViews();
    }

    private void initViews(){
        lightThemeRadioButton = findViewById(R.id.light_theme_radio_button);
        darkThemeRadioButton = findViewById(R.id.dark_theme_radio_button);
    }

    private void setupViews() {
        lightThemeRadioButton.setOnClickListener(v -> {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        });
        darkThemeRadioButton.setOnClickListener(v -> {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        });
    }
}
