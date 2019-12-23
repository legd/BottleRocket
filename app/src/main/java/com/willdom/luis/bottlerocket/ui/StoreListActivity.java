package com.willdom.luis.bottlerocket.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.willdom.luis.bottlerocket.R;
import com.willdom.luis.bottlerocket.api.models.ApiStoreModel;
import com.willdom.luis.bottlerocket.database.RealmController;
import com.willdom.luis.bottlerocket.repositories.StoreRepositoryState;
import com.willdom.luis.bottlerocket.ui.adapters.StoreAdapter;
import com.willdom.luis.bottlerocket.viewmodel.StoresViewModel;

import java.util.List;

/**
 * Main activity of the app in charge to display the list of stores.
 *
 * @author Luis Guzman
 */
public class StoreListActivity extends AppCompatActivity {

    private static final String TAG = StoreListActivity.class.getName();
    private ProgressBar mProgressBar;
    private RecyclerView mStoreList;
    private StoreAdapter mStoreAdapter;
    private StoresViewModel mStoresViewModel;

    //===========================================================================
    //                           OVERRIDE METHOD
    //===========================================================================

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
                mStoreAdapter.updateList(stores);
            }
        });

        mStoresViewModel.isLoading().observe(this, new Observer<StoreRepositoryState>() {
            @Override
            public void onChanged(StoreRepositoryState state) {
                if(state.isIsLoading()) {
                    mProgressBar.setVisibility(View.VISIBLE);
                } else {
                    mProgressBar.setVisibility(View.INVISIBLE);

                    if(state.getResult() == StoreRepositoryState.Result.SUCCESS && state
                            .getMessage() != null) {

                        Toast.makeText(getApplicationContext(), state.getMessage(), Toast
                                .LENGTH_SHORT).show();
                    }

                    if(state.getResult() == StoreRepositoryState.Result.ERROR) {
                        showAlert(state.getMessage(), 1);
                    }
                }
            }
        });

        initRecyclerView();
    }

    //===========================================================================
    //                           CUSTOM METHOD
    //===========================================================================

    /**
     * Method in charge to initialize the recycler view of the screen.
     */
    private void initRecyclerView() {

        Log.i(TAG, "initRecyclerView");
        mStoreAdapter = new StoreAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mStoreList.setLayoutManager(layoutManager);
        mStoreList.setAdapter(mStoreAdapter);
    }

    /**
     * Shows an alert dialog with a message for the user.
     *
     * @param message text to be shown
     * @param messageType the type of message to be displayed
     */
    public void showAlert(String message, int messageType) {

        AlertDialog.Builder alertBuilder;
        alertBuilder = new AlertDialog.Builder(this, R.style.CustomDialog);

        if(messageType == 0) {
            alertBuilder.setIcon(android.R.drawable.ic_dialog_info);
        } else {
            alertBuilder.setIcon(android.R.drawable.ic_dialog_alert);
        }

        alertBuilder.setTitle("Alert")
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, (DialogInterface dialog, int which) ->
                        dialog.dismiss()
                )
                .show();
    }
}
