package com.app.shopzz.utility;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <h1>Debug.java</h1>
 * <p>
 * The Debug class implements Log functionality which filters
 * Application logs with the TAG provided by the developer.
 * <p>
 * developer can easily filter logs by using this class.
 *
 * @author Abbacus Technologies
 * @version 1.0
 * @since 2017-04-28
 */
public class Debug {
    private static final String TAG = "TAG";
    public static boolean isDebug = true;
    public static boolean isPersistant = false;

    /**
     * This method is used to print log in "Android Monitor".
     * This is a simplest method to print log , message will be defined
     * by the developer.
     * <p>
     * If developer wants to save log on sdcard, then it is possible
     * using the append method.
     * <p>
     * It will print log if isPersistant is true.
     *
     * @param msg : This is the message developer wants to print.
     * @return Nothing
     */
    public static void trace(final String msg) {
        if (isDebug) {
            Log.i(TAG, msg);
            if (isPersistant) {
                appendLog(msg);
            }
        }
    }

    /**
     * This method is used print log in "Android Monitor".
     * This is a simplest method to print log , message will be defined
     * by the developer.
     *
     * @param tag : This is the filter tag,by which user can filter logs.
     * @param msg : This is the message developer wants to print.
     * @return Nothing
     */
    public static void trace(final String tag, final String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    /**
     * This is the method used to save log into sdcard.
     * path will be defined in file object.
     *
     * @param text : Message, which requires to save in .txt file.
     * @return Nothing
     */
    public static void appendLog(final String text) {
        final File logFile = new File(Environment.getExternalStorageDirectory() + "/Shopzz.txt");
        if (!logFile.exists()) try {
            logFile.createNewFile();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        try {
            // BufferedWriter for performance, true to set append to file flag
            final BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(text);
            buf.newLine();
            buf.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}