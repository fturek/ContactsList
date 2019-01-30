package com.filipturek.contactslist.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.filipturek.contactslist.R;
import com.filipturek.contactslist.http.model.Contact;
import com.filipturek.contactslist.http.retrofit.ContactsApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.androidhive.info/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ContactsApi contactsApi = retrofit.create(ContactsApi.class);

        Call<List<Contact>> call = contactsApi.listContacts();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                if (response.isSuccessful()) {
                    List<Contact> contactList = response.body();
                    for (Contact contact : contactList) {
                        Log.e("FT", contact.getName() + " " + contact.getPhone() + " " + contact.getImage());
                    }
                } else {
                    Log.e("", "! esponse.isSuccessful()");
                }
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Log.e("", "onFailure");
            }
        });
    }
}
