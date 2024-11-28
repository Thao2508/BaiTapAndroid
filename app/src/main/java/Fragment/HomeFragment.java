package Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baitapcuoi_ntt.BookAdapter;
import com.example.baitapcuoi_ntt.R;

import java.util.ArrayList;
import java.util.List;

import Model.Book;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private List<Book> bookList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        bookList = new ArrayList<>();
        bookList.add(new Book(R.drawable.icon8_book50, "Sách hay nên đọc", "x100", "200.000đ"));
        bookList.add(new Book(R.drawable.icon8_book50, "Sách học lập trình", "x100", "150.000đ"));
        bookList.add(new Book(R.drawable.icon8_book50, "Sách Ngữ Văn 12", "x100", "150.000đ"));
        bookList.add(new Book(R.drawable.icon8_book50, "100 mẹo hay mỗi ngày", "x100", "150.000đ"));
        bookList.add(new Book(R.drawable.icon8_book50, "Sách học lập trình", "x100", "150.000đ"));
        bookList.add(new Book(R.drawable.icon8_book50, "Sách học lập trình", "x100", "150.000đ"));
        bookAdapter = new BookAdapter(bookList);
        recyclerView.setAdapter(bookAdapter);

        return view;
    }
}
