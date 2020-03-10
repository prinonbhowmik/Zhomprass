package com.my.zhomprass_java.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.my.zhomprass_java.R;

public class ContactUs extends AppCompatActivity {
   // private TextView contactUsTv, nameTv, doofazItTv, mobileTv, mobileNoTv, emailTv, doofazGmailTv, addressTv, mahammadputTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);


//        contactUsTv = findViewById(R.id.contactId);
//        nameTv = findViewById(R.id.nameTv);
//        doofazItTv = findViewById(R.id.doofazItTv);
//        mobileTv = findViewById(R.id.mobileTv);
//        mobileNoTv = findViewById(R.id.mobileNoTv);
//        emailTv = findViewById(R.id.emailsTv);
//        doofazGmailTv = findViewById(R.id.doofazitEmailTv);
//        addressTv = findViewById(R.id.addressTv);
//        mahammadputTv = findViewById(R.id.mahammadpurDhakaTv);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    
}
