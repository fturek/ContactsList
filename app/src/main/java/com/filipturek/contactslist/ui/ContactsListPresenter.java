package com.filipturek.contactslist.ui;

import android.util.Log;

import com.filipturek.contactslist.URLS;
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

public class ContactsListPresenter implements ContactsListContract.Presenter {

    private ContactsListContract.View mContactsListView;

    ContactsListPresenter(ContactsListContract.View mContactsListView) {
        this.mContactsListView = mContactsListView;
        mContactsListView.setPresenter(this);
    }

    @Override
    public void start() {
        loadContactsList();
    }

    @Override
    public void loadContactsList() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLS.CONTACTS)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Callback<List<Contact>> callback = new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                if (response.isSuccessful()) {
                    List<Contact> contactList = response.body();

                    mContactsListView.showContactsList(contactList);
                } else {
                    Log.e("", "Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Log.e("", "onFailure");
            }
        };

        ContactsApi contactsApi = retrofit.create(ContactsApi.class);
        Call<List<Contact>> call = contactsApi.listContacts();
        call.enqueue(callback);
    }
}
