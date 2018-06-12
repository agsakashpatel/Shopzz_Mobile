package com.app.shopzz.helper;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.app.shopzz.BaseApplication;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class PrefHelper {
    public static String KEY_USER = "user";


    public static void setString(final String key, final String value) {
        final Editor editor = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance()).edit();
        editor.putString(key, value);
        apply(editor);
    }

    public static String getString(final String key, final String defaultValue) {
        final SharedPreferences _preference = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance());
        return _preference.getString(key, defaultValue);
    }

    public static void setInt(final String key, final int value) {
        final Editor editor = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance()).edit();
        editor.putInt(key, value);
        apply(editor);
    }

    public static int getInt(final String key, final int defaultValue) {
        final SharedPreferences _preference = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance());
        return _preference.getInt(key, defaultValue);
    }

    public static boolean getBoolean(final String key, final boolean defaultValue) {
        final SharedPreferences _preference = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance());
        return _preference.getBoolean(key, defaultValue);
    }

    public static void setBoolean(final String key, final boolean value) {
        final Editor editor = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance()).edit();
        editor.putBoolean(key, value);
        apply(editor);
    }

    public static void setLong(final String key, final long value) {
        final Editor editor = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance()).edit();
        editor.putLong(key, value);
        apply(editor);
    }

    public static void setDouble(final String key, final double value) {
        final Editor editor = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance()).edit();
        editor.putLong(key, Double.doubleToRawLongBits(value));
        apply(editor);
    }

    public static long getDouble(final String key, final double value) {
        final SharedPreferences _preference = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance());
        return _preference.getLong(key, Double.doubleToRawLongBits(value));
    }

    public static void deletePreference(final String key) {
        final Editor editor = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance()).edit();
        editor.remove(key);
        apply(editor);
    }

    public static void deleteAllPreferences() {
        final Editor editor = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance()).edit();
        editor.clear();
        apply(editor);
    }

    // Faster pref saving for high performance
    private static final Method sApplyMethod = findApplyMethod();

    private static Method findApplyMethod() {
        try {
            final Class<Editor> cls = Editor.class;
            return cls.getMethod("apply");
        } catch (final NoSuchMethodException unused) {
            // fall through
        }
        return null;
    }

    public static void apply(final Editor editor) {
        if (sApplyMethod != null) {
            try {
                sApplyMethod.invoke(editor);
                return;
            } catch (final InvocationTargetException unused) {
                // fall through
            } catch (final IllegalAccessException unused) {
                // fall through
            }
        }
        editor.commit();
    }
}
