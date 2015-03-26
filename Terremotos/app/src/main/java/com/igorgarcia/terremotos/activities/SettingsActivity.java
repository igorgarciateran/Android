package com.igorgarcia.terremotos.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.igorgarcia.terremotos.R;

import java.util.List;

/**
 * Created by cursomovil on 26/03/15.
 */
public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.userpreferences);

    }
}
