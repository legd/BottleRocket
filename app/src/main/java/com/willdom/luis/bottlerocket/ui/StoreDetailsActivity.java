package com.willdom.luis.bottlerocket.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.willdom.luis.bottlerocket.R;

public class StoreDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_details);


        TextView storeName = findViewById(R.id.store_name);
        storeName.setText(getIntent().getStringExtra("name"));

        TextView storeNumber = findViewById(R.id.store_number);
        storeNumber.setText(getIntent().getStringExtra("number"));

        TextView storeAddress = findViewById(R.id.full_store_address);
        storeAddress.setText(formatAddress(
                getIntent().getStringExtra("address"),
                getIntent().getStringExtra("city"),
                getIntent().getStringExtra("state"),
                getIntent().getStringExtra("zipcode")));

        String latitude = getIntent().getStringExtra("latitude");
        String longitude = getIntent().getStringExtra("longitude");

        TextView storeSeeLocation = findViewById(R.id.see_on_map_label);
        storeSeeLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String geoLocation = "geo:" + latitude + "," + longitude;
//                Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194");
                Uri gmmIntentUri = Uri.parse(geoLocation);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });

    }

    /**
     *
     * @param address
     * @param city
     * @param zipcode
     * @param state
     * @return String with full address formatted
     */
    private String formatAddress(String address, String city, String state, String zipcode) {
        return String.format("%s%s%s%s%s%s%s", address, " \n", city, ", ", state, " ", zipcode);
    }
}
