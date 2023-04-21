package com.study.books.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BooksModel {
    String title;
    String[] authors;
    String cover;
    public BooksModel() {}

    public BooksModel(String title, String[] authors, String cover) {
        this.title = title;
        this.authors = authors;
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public static BooksModel fromJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        BooksModel book = new BooksModel();
        book.title = jsonObject.optString("title", "");
        book.cover = jsonObject.optString("cover_id", "");
        JSONArray authors = jsonObject.optJSONArray("authors");
        if (authors != null) {
            book.authors = new String[authors.length()];
            for (int i = 0; i < authors.length(); i++) {
                JSONObject author = authors.getJSONObject(i);
                book.authors[i] = author.optString("name", "");
            }
        }
        return book;
    }
    public static ArrayList<BooksModel> fromJsonList(JSONObject jsonObject) throws JSONException {
        ArrayList<BooksModel> books = new ArrayList<>();
        JSONArray works = jsonObject.optJSONArray("works");
        if (works != null) {
            for (int i = 0; i < 10; i++) {
                JSONObject work = works.getJSONObject(i);
                String title = work.optString("title", "");
                String cover ="https://covers.openlibrary.org/b/id/"+ work.optString("cover_id", "")+"-M.jpg";
                JSONArray authorsArray = work.optJSONArray("authors");
                if (authorsArray != null) {
                    String[] authors = new String[authorsArray.length()];
                    for (int j = 0; j < authorsArray.length(); j++) {
                        JSONObject author = authorsArray.getJSONObject(j);
                        authors[j] = author.optString("name", "");
                    }
                    BooksModel book = new BooksModel(title, authors, cover);
                    books.add(book);
                }
            }
        }
        return books;
    }
}
