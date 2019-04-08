package com.filipturek.contactslist.ui;

import com.filipturek.contactslist.architecture.BasePresenter;
import com.filipturek.contactslist.architecture.BaseView;
import com.filipturek.contactslist.http.model.Contact;

import java.util.List;

public interface ContactsListContract {

    interface View extends BaseView<Presenter> {
        void showContactsList(List<Contact> contactsList);
    }

    interface Presenter extends BasePresenter {
        void loadContactsList();
    }
}
