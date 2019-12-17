package com.willdom.luis.bottlerocket.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.willdom.luis.bottlerocket.BuildConfig;
import com.willdom.luis.bottlerocket.R;
import com.willdom.luis.bottlerocket.api.BottleRocketApiClient;
import com.willdom.luis.bottlerocket.api.interfaces.BottleRocketApi;
import com.willdom.luis.bottlerocket.api.models.StoreResults;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private BottleRocketApi mApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        mApiClient = BottleRocketApiClient.getInstance(BuildConfig.API_URL);
//
//        mApiClient.getStores().enqueue(new Callback<StoreResults>() {
//            @Override
//            public void onResponse(Call<StoreResults> call, Response<StoreResults> response) {
//                Log.i(TAG, "onResponse: List size " + response.body().getStores().size());
//            }
//
//            @Override
//            public void onFailure(Call<StoreResults> call, Throwable error) {
//                Log.e(TAG, "Request failed while communicating with server: ", error);
//            }
//        });

//        Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194");
//        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//        mapIntent.setPackage("com.google.android.apps.maps");
//        if (mapIntent.resolveActivity(getPackageManager()) != null) {
//            startActivity(mapIntent);
//        }

    }
}
