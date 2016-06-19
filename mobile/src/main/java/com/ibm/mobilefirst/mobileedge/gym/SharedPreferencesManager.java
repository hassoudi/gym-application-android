package com.ibm.mobilefirst.mobileedge.gym;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by cirilla on 30/05/2016.
 */
public class SharedPreferencesManager {

    protected SharedPreferences sharedPreferences;
    protected SharedPreferences.Editor editor;

    public SharedPreferencesManager(Context context, String name, int mode) {
        this.sharedPreferences = context.getSharedPreferences(name, mode);
        this.editor = sharedPreferences.edit();
    }

    /**
     * Holds single string preference value
     */
    public class StringPreference {

        String prefName;
        String value;

        StringPreference(String prefName) {
            this(prefName, null);
        }

        StringPreference(String prefName, String defaultValue) {
            this.prefName = prefName;
            this.value = sharedPreferences.getString(prefName, defaultValue);
        }

        public String get() {
            return value;
        }

        public void set(String value) {
            this.value = value;
            commit();
        }

        public void clear() {
            this.value = null;
            commit();
        }

        private void commit() {
            editor.putString(prefName, value);
            editor.commit();
        }
    }
}
