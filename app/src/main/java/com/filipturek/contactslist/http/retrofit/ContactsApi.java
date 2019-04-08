package com.filipturek.contactslist.http.retrofit;

import com.filipturek.contactslist.URLS;
import com.filipturek.contactslist.http.model.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ContactsApi {

    @GET(URLS.CONTACTS_JSON)
    Call<List<Contact>> listContacts();
}
