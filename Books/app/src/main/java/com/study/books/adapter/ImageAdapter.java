package com.study.books.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.study.books.R;
import com.study.books.model.ImageSourseModel;

import java.util.List;

public class ImageAdapter extends ArrayAdapter<ImageSourseModel> {

    private LayoutInflater inflater;
    private int layout;
    private List<ImageSourseModel> images;

    public ImageAdapter(@NonNull Context context, int resource, List<ImageSourseModel> images) {
        super(context, resource, images);
        this.images = images;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.flagView = convertView.findViewById(R.id.img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ImageSourseModel source = images.get(position);
        Glide.with(getContext()).load(source.getImgResource()).into(viewHolder.flagView);

        return convertView;
    }

    static class ViewHolder {
        ImageView flagView;
    }
}
