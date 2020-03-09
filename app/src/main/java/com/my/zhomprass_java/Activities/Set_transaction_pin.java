package com.my.zhomprass_java.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.my.zhomprass_java.R;

public class Set_transaction_pin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_transaction_pin);

        checkConnection();
    }

    public void checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {

            } else {

            }

        } else {
            Snackbar.make(findViewById(R.id.layoutSetTransactionPinActivity), R.string.offline_message, Snackbar.LENGTH_LONG).show();
        }
    }
}
