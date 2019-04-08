package com.filipturek.contactslist.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.filipturek.contactslist.R;
import com.filipturek.contactslist.http.model.Contact;

import java.util.ArrayList;

class ContactsListAdapter extends ArrayAdapter<Contact> {

    private LayoutInflater inflater;

    ContactsListAdapter(@NonNull Context context, ArrayList<Contact> contactsList) {
        super(context, -1, contactsList);
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        Contact contact = getItem(position);

        if (row == null) {
            row = inflater.inflate(R.layout.contacst_view, null);
            holder = new ViewHolder(
                    (TextView) row.findViewById(R.id.tv_contact_name),
                    (ImageView) row.findViewById(R.id.iv_contact_avatar)
            );
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        holder.contactAvatar = null;
        holder.contactName.setText(contact.getName());
        return row;
    }

    private class ViewHolder {
        TextView contactName;
        ImageView contactAvatar;

        ViewHolder(TextView contactName, ImageView contactAvatar) {
            this.contactName = contactName;
            this.contactAvatar = contactAvatar;
        }
    }
}
