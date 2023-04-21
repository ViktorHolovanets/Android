package com.study.books.adapter;

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

import com.bumptech.glide.Glide;
import com.study.books.R;
import com.study.books.model.BooksModel;

import java.util.List;

public class BooksAdapter extends ArrayAdapter<BooksModel> {
    private LayoutInflater inflater;
    private int layout;

    public BooksAdapter(@NonNull Context context, int resource, List<BooksModel> books) {
        super(context, resource, books);
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    static class ViewHolder {
        ImageView cover;
        TextView title;
        ListView authors;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.cover = convertView.findViewById(R.id.cover_book);
            viewHolder.title = convertView.findViewById(R.id.title_book);
            viewHolder.authors = convertView.findViewById(R.id.authors_book);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        BooksModel book = getItem(position);

        Glide.with(getContext()).load(book.getCover()).into(viewHolder.cover);
        viewHolder.title.setText(book.getTitle());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, book.getAuthors());
        viewHolder.authors.setAdapter(adapter);

        return convertView;
    }
}
