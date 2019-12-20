package com.willdom.luis.bottlerocket.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.willdom.luis.bottlerocket.R;
import com.willdom.luis.bottlerocket.api.interfaces.BottleRocketApi;
import com.willdom.luis.bottlerocket.models.Store;
import com.willdom.luis.bottlerocket.viewmodel.StoresViewModel;

import java.util.List;

public class StoreListActivity extends AppCompatActivity {

    private static final String TAG = StoreListActivity.class.getName();
    private BottleRocketApi mApiClient;

    private StoresViewModel mStoresViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_list);

        mStoresViewModel = ViewModelProviders.of(this).get(StoresViewModel.class);
        mStoresViewModel.init();
        mStoresViewModel.getStores().observe(this, new Observer<List<Store>>() {
            @Override
            public void onChanged(List<Store> stores) {
                 // mAdapter.notifyDataSetChanged();
            }
        });

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

    private void initRecyclerView() {
        // mAdapter =  new RecyclerAdapter(this, mStoresViewModel.getStores().getValue());
    }
}
