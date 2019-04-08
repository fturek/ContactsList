package com.filipturek.contactslist.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.filipturek.contactslist.R;
import com.filipturek.contactslist.http.model.Contact;
import com.filipturek.contactslist.http.retrofit.ContactsApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ContactsListFragment contactsFragment = (ContactsListFragment)
                getSupportFragmentManager().findFragmentByTag(ContactsListFragment.TAG);

        if (contactsFragment == null) {
            contactsFragment = ContactsListFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, contactsFragment, ContactsListFragment.TAG)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
