package com.willdom.luis.bottlerocket.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.willdom.luis.bottlerocket.R;
import com.willdom.luis.bottlerocket.api.models.ApiStoreModel;
import com.willdom.luis.bottlerocket.ui.StoreDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class of the custom adapter for the store list.
 *
 * @author Luis Guzman
 */
public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {

    private static final String TAG = StoreAdapter.class.getName();
    private Context mContext;
    private List<ApiStoreModel> mStores;

    //===========================================================================
    //                           CONSTRUCTOR
    //===========================================================================

    public StoreAdapter(Context context) {

        this.mStores = new ArrayList<>();
        this.mContext = context;
    }

    //===========================================================================
    //                           OVERRIDE METHODS
    //===========================================================================

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_list_item,
                parent, false);

        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {

        Glide.with(mContext)
                .asBitmap()
                .load(mStores.get(position).getStoreLogoUrl())
                .into(holder.mLogo);

        holder.mPhoneNumber.setText(mStores.get(position).getPhone());
        holder.mAddress.setText(mStores.get(position).getAddress());

        holder.mParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent storeDetailIntent = new Intent(mContext, StoreDetailsActivity.class);
                storeDetailIntent.putExtra("name", mStores.get(position).getName());
                storeDetailIntent.putExtra("number", mStores.get(position).getPhone());
                storeDetailIntent.putExtra("address", mStores.get(position).getAddress());
                storeDetailIntent.putExtra("city", mStores.get(position).getCity());
                storeDetailIntent.putExtra("zipcode", mStores.get(position).getZipcode());
                storeDetailIntent.putExtra("state", mStores.get(position).getState());
                storeDetailIntent.putExtra("latitude", mStores.get(position).getLatitude());
                storeDetailIntent.putExtra("longitude", mStores.get(position).getLongitude());
                mContext.startActivity(storeDetailIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mStores.size();
    }

    //===========================================================================
    //                           CUSTOM METHODS
    //===========================================================================

    /**
     * Method in charge to update the list of store of the adapter.
     *
     * @param storeList New list of stores
     */
    public void updateList(List<ApiStoreModel> storeList) {

        if(storeList != null) {
            this.mStores.clear();
            this.mStores = storeList;
            this.notifyDataSetChanged();
        }
    }

    //===========================================================================
    //                           PRIVATE CLASSES
    //===========================================================================

    /**
     * Class for the implementation of the ViewHolder design pattern used to represent the stores
     * rows.
     */
    public static class StoreViewHolder extends RecyclerView.ViewHolder {

        private ImageView mLogo;
        private TextView mPhoneNumber;
        private TextView mAddress;
        private RelativeLayout mParentLayout;

        StoreViewHolder(@NonNull View itemView) {
            super(itemView);

            mLogo = itemView.findViewById(R.id.store_logo);
            mPhoneNumber =  itemView.findViewById(R.id.store_phone_number);
            mAddress = itemView.findViewById(R.id.store_address);
            mParentLayout = itemView.findViewById(R.id.list_item_layout);
        }
    }
}
