package com.example.baitapcuoi_ntt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Model.Book;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> bookList;

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name, quantity, price;

        public BookViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.image);
            name = view.findViewById(R.id.product_name);
            quantity = view.findViewById(R.id.product_quantity);
            price = view.findViewById(R.id.product_price);
        }
    }

    public BookAdapter(List<Book> books) {
        this.bookList = books;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.image.setImageResource(book.getImage());
        holder.name.setText(book.getName());
        holder.quantity.setText(book.getQuantity());
        holder.price.setText(book.getPrice());
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
}

