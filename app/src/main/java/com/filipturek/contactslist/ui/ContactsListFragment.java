package com.filipturek.contactslist.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.filipturek.contactslist.R;
import com.filipturek.contactslist.http.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactsListFragment extends Fragment implements ContactsListContract.View {

    public static final String TAG = " CONTACTS_LIST_FRAGMENT";

    private ContactsListContract.Presenter mContactsListPresenter;
    private ContactsListAdapter contactsListAdapter;

    public static ContactsListFragment newInstance() {
        return new ContactsListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contactsListAdapter = new ContactsListAdapter(getActivity(), new ArrayList<Contact>());
        new ContactsListPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contacts_list, container, false);

        ListView listView = rootView.findViewById(R.id.contacts_list_view);
        listView.setAdapter(contactsListAdapter);
        return rootView;
    }

    @Override
    public void showContactsList(List<Contact> contactsList) {
        for (Contact contact : contactsList) {
            Log.d("FT",
                    contact.getName() + " "
                    + contact.getPhone() + " "
                    + contact.getImage()
            );
        }

        if( !contactsList.isEmpty()){
            contactsListAdapter.clear();
            contactsListAdapter.addAll(contactsList);
            contactsListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mContactsListPresenter.start();
    }

    @Override
    public void setPresenter(ContactsListContract.Presenter presenter) {
        mContactsListPresenter = presenter;
    }
}
