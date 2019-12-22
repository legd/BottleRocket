package com.willdom.luis.bottlerocket.database;

import android.content.Context;
import android.util.Log;

import com.willdom.luis.bottlerocket.BuildConfig;

import java.nio.charset.StandardCharsets;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Class for wrapping the Realm initialization and configuration for the default Realm instance.
 *
 * @author Luis Guzman
 */
public class RealmController {

    private static final String TAG = RealmController.class.getName();
    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    /**
     * Method in charge to initialize the default Realm instance.
     *
     * @param context Application context
     * @return Default Realm instance
     */
    public static Realm initializeConfiguration(Context context) {

        // This is a basic encryption example for production another approach is need it
        byte[] key = BuildConfig.ENCRIPTION_KEY.getBytes(StandardCharsets.UTF_8);

        // Code to print the data base password
        if(BuildConfig.DEBUG) {
            char[] hexChars = new char[key.length * 2];
            for ( int j = 0; j < key.length; j++ ) {
                int v = key[j] & 0xFF;
                hexChars[j * 2] = hexArray[v >>> 4];
                hexChars[j * 2 + 1] = hexArray[v & 0x0F];
            }

            String keyString = new String(hexChars);
            Log.i(TAG, "database key: " + keyString);
        }

        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("bottleRocket.realm")
                .encryptionKey(key)
                .schemaVersion(1)
                .build();

        return Realm.getInstance(config);
    }
}
