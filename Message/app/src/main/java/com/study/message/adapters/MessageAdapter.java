package com.study.message.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.study.message.R;
import com.study.message.models.Message;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MessageAdapter extends ArrayAdapter<Message> {
    private LayoutInflater inflater;
    private int layout;

    public MessageAdapter(@NonNull Context context, int resource, List<Message> messages) {
        super(context, resource, messages);
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    static class ViewHolder {
        TextView text;
        TextView time;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.text = convertView.findViewById(R.id.content_message);
            viewHolder.time = convertView.findViewById(R.id.time_sends);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Message message = getItem(position);

        viewHolder.text.setText(message.getTextMessage());
        viewHolder.time.setText(this.getTime());
        return convertView;
    }

    private String getTime(){
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = time.format(formatter);
        return formattedTime;
    }
}
