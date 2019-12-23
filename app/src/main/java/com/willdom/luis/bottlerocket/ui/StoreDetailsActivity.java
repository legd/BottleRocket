package com.willdom.luis.bottlerocket.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.willdom.luis.bottlerocket.R;

/**
 * Detail activity in charge to display store details.
 *
 * @author Luis Guzman
 */
public class StoreDetailsActivity extends AppCompatActivity {


    //===========================================================================
    //                           OVERRIDE METHODS
    //===========================================================================

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
                Uri gmmIntentUri = Uri.parse(geoLocation);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });

    }

    //===========================================================================
    //                           CUSTOM METHOD
    //===========================================================================

    /**
     * Method in charge to format the whole address into one String.
     *
     * @param address first line address
     * @param city city in the address
     * @param zipcode zipcode in the address
     * @param state state in the address
     * @return String with full address formatted
     */
    private String formatAddress(String address, String city, String state, String zipcode) {
        return String.format("%s%s%s%s%s%s%s", address, " \n", city, ", ", state, " ", zipcode);
    }
}
