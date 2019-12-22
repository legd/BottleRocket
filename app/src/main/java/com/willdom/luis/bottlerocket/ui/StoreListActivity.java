package com.willdom.luis.bottlerocket.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.willdom.luis.bottlerocket.R;
import com.willdom.luis.bottlerocket.api.models.ApiStoreModel;
import com.willdom.luis.bottlerocket.database.RealmController;
import com.willdom.luis.bottlerocket.ui.adapters.StoreAdapter;
import com.willdom.luis.bottlerocket.viewmodel.StoresViewModel;

import java.util.List;

/**
 *
 * @author Luis Guzman
 */
public class StoreListActivity extends AppCompatActivity implements StoreAdapter.OnStoreListClickItemListener {

    private static final String TAG = StoreListActivity.class.getName();
    private ProgressBar mProgressBar;
    private RecyclerView mStoreList;
    private StoreAdapter mStoreAdapter;
    private StoresViewModel mStoresViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_list);
        mStoreList = findViewById(R.id.store_list);
        mProgressBar = findViewById(R.id.progress_bar);

        RealmController.initializeConfiguration(getApplicationContext());

        // Set the view model for the recycler view
        mStoresViewModel = ViewModelProviders.of(this).get(StoresViewModel.class);
        mStoresViewModel.init();
        mStoresViewModel.getStores().observe(this, new Observer<List<ApiStoreModel>>() {
            @Override
            public void onChanged(List<ApiStoreModel> stores) {
//                mStoreAdapter.notifyDataSetChanged();
                mStoreAdapter.updateList(stores);
            }
        });

        mStoresViewModel.isLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean) {
                    mProgressBar.setVisibility(View.VISIBLE);
                } else {
                    mProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        });

        initRecyclerView();


//        Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194");
//        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//        mapIntent.setPackage("com.google.android.apps.maps");
//        if (mapIntent.resolveActivity(getPackageManager()) != null) {
//            startActivity(mapIntent);
//        }

    }

    /**
     * Method in charge to initialize the recycler view of the screen.
     */
    private void initRecyclerView() {

        Log.i(TAG, "initRecyclerView");
//        mStoreAdapter = new StoreAdapter(this, mStoresViewModel.getStores().getValue(),
//                this);

        mStoreAdapter = new StoreAdapter(this,this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mStoreList.setLayoutManager(layoutManager);
        mStoreList.setAdapter(mStoreAdapter);
    }

    @Override
    public void onItemClicked(int position) {

    }
}
