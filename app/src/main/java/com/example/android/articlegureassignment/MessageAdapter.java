package com.example.android.articlegureassignment;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<NewMessage> {
    public MessageAdapter(Context context, int resource, List<NewMessage> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_message, parent, false);
        }

        ImageView photoView = (ImageView) convertView.findViewById(R.id.photoView);
        TextView descriptionView = (TextView) convertView.findViewById(R.id.descriptionView);
        TextView likeCount = (TextView) convertView.findViewById(R.id.likeCount);

        final NewMessage message = getItem(position);

        message.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setLikes(message.getLikes() + 1);
            }
        });

        Glide.with(photoView.getContext()).load(message.getPhotoUrl()).into(photoView);
        descriptionView.setText(message.getDescription());
        likeCount.setText(message.getLikes());

        return convertView;
    }
}
