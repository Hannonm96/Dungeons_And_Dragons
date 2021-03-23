package com.example.dungeons_and_dragons;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.dungeons_and_dragons.model.AppViewModel;

public class PreferencesFragment extends PreferenceFragmentCompat {
    EditTextPreference editTextPreference;
    AppViewModel appViewModel;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences_main, rootKey);

        appViewModel = new ViewModelProvider(this)
                .get(AppViewModel.class);
        //editTextPreference.setSummary(appViewModel.getAllUsers().getValue().get(0).getName());

    }

    private static Preference.OnPreferenceChangeListener listener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            return false;
        }
    };

    @Override
    public void onResume() {
        super.onResume();
    }
}
