package com.example.yb.friendsmap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by YB on 16.10.2015.
 */
public class EntryAdapter extends ArrayAdapter<PersonsEntry> {
    private final int personsItemLayoutResource;

    public EntryAdapter(final Context context, final int personsItemLayoutResource) {
        super(context, 0);
        this.personsItemLayoutResource = personsItemLayoutResource;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        final View view = getWorkingView(convertView);
        final ViewHolder viewHolder = getViewHolder(view);
        final PersonsEntry entry = getItem(position);

        viewHolder.personNameView.setText(entry.getPersonName());
        viewHolder.personAvatarView.setImageResource(entry.getPersonAvatar());
        viewHolder.personFriendsView.setText(entry.getPersonFriendsList().toString());

        return view;
    }

    private View getWorkingView(final View convertView) {

        View workingView = null;

        if(null == convertView) {
            final Context context = getContext();
            final LayoutInflater inflater = (LayoutInflater)context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);

            workingView = inflater.inflate(personsItemLayoutResource, null);
        } else {
            workingView = convertView;
        }

        return workingView;
    }

    private ViewHolder getViewHolder(final View workingView) {

        final Object tag = workingView.getTag();
        ViewHolder viewHolder = null;


        if(null == tag || !(tag instanceof ViewHolder)) {
            viewHolder = new ViewHolder();

            viewHolder.personNameView = (TextView) workingView.findViewById(R.id.personName);
            viewHolder.personFriendsView = (TextView) workingView.findViewById(R.id.friendsName);
            viewHolder.personAvatarView = (ImageView) workingView.findViewById(R.id.personImageView);

            workingView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) tag;
        }

        return viewHolder;
    }

    private static class ViewHolder {
        public TextView personNameView;
        public TextView personFriendsView;
        public ImageView personAvatarView;
    }
}
