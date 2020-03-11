package com.my.zhomprass_java.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.my.zhomprass_java.R;

public class ContactUs extends AppCompatActivity {

    private TextView phn1,phn2,webText,emailText;
    private ImageView doofaz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        phn1 = findViewById(R.id.phn1);
        webText = findViewById(R.id.webText);
        doofaz = findViewById(R.id.doofaz);
        emailText = findViewById(R.id.emailText);

        phn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = phn1.getText().toString();

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phone));
                startActivity(intent);
            }
        });

        webText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("http://www.zhomprass.com/"));
                startActivity(browserIntent);
            }
        });
        doofaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("https://doofazit.com//"));
                startActivity(browserIntent);
            }
        });

        emailText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                String[] recipients={"zhomprassltd@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT,"");
                intent.putExtra(Intent.EXTRA_TEXT,"");
                intent.putExtra(Intent.EXTRA_CC,"zhomprassltd@gmail.com");
                intent.setType("text/html");
                intent.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intent, ""));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    
}
