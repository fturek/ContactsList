package com.filipturek.contactslist.http.retrofit;

import com.filipturek.contactslist.http.model.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ContactsApi {

    @GET("json/contacts.json")
    Call<List<Contact>> listContacts();
}
