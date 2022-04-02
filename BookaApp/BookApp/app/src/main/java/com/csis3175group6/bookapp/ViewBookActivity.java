package com.csis3175group6.bookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.csis3175group6.bookapp.dataaccess.DatabaseOpenHelper;
import com.csis3175group6.bookapp.entities.Book;

public class ViewBookActivity extends AppCompatActivity implements BookAdapter.IShareButtonClickListener{
    Book[] books;
    BookAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.book_recyclerview);
        DatabaseOpenHelper db = new DatabaseOpenHelper(this);
        books = db.getBooksByOwnerId(App.getInstance().User.Id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new BookAdapter(this, books, BookAdapter.Mode.SHARE);
        adapter.setShareButtonClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onShareButtonClickListener(View view, int position) {
        Toast.makeText(this, "Clicked on book: " + books[position].Title, Toast.LENGTH_SHORT).show();
    }
}