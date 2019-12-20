package com.willdom.luis.bottlerocket.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.willdom.luis.bottlerocket.R;
import com.willdom.luis.bottlerocket.models.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis on 2019-12-20
 */
public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {

    private static final String TAG = StoreAdapter.class.getName();
    private Context mContext;
    private OnClickListItemListener mOnClickListItemListener;
    private List<Store> mStores;

    public StoreAdapter(Context context, OnClickListItemListener onClickListItemListener) {
        this.mStores = new ArrayList<>();
        this.mContext = context;
        this.mOnClickListItemListener = onClickListItemListener;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_list_item,
                parent, false);

        return new StoreViewHolder(view, mOnClickListItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {

        Glide.with(mContext)
                .asBitmap()
                .load(mStores.get(position).getStoreLogoUrl())
                .into(holder.mLogo);

        holder.mPhoneNumber.setText(mStores.get(position).getPhone());
        holder.mAddress.setText(mStores.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return this.mStores.size();
    }


    //===========================================================================
    //                           PRIVATE CLASSES
    //===========================================================================

    /**
     * Class for the implementation of the ViewHolder design pattern used to represent the stores
     * rows.
     */
    public static class StoreViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        private ImageView mLogo;
        private TextView mPhoneNumber;
        private TextView mAddress;
        private OnClickListItemListener mOnStoreClickListener;

        StoreViewHolder(@NonNull View itemView, OnClickListItemListener storeListener) {
            super(itemView);

            mLogo = itemView.findViewById(R.id.store_logo);
            mPhoneNumber =  itemView.findViewById(R.id.store_phone_number);
            mAddress = itemView.findViewById(R.id.store_address);
            mOnStoreClickListener = storeListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnStoreClickListener.onItemClicked(getAdapterPosition());
        }
    }

    //===========================================================================
    //                           INTERFACE
    //===========================================================================

    /**
     * Interface containing the method responsible for the OnClick event in a row.
     */
    public interface OnClickListItemListener {
        void onItemClicked(int position);
    }
}
