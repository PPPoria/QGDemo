package com.example.mvvm.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.mvvm.Book;
import com.example.mvvm.BookDao;
import com.example.mvvm.R;
import com.example.mvvm.SQLDataBase;
import com.example.mvvm.User;
import com.example.mvvm.Writer;
import com.example.mvvm.databinding.ActivityMainBinding;
import com.example.mvvm.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final User user = new User("");
    private List<Book> books;
    private BookDao bookDao;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initDataBinding();
        initView();
        initBooksList();
    }

    private void initBooksList() {
        //来点测试数据
        Book book1 = new Book(1, "YY", "yoyo", null, new Writer("bb", 19));
        Book book2 = new Book(2, "PPP", "P", null, new Writer("Houmen", 20));

        bookDao = SQLDataBase.getDataBase(this).bookDao();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new BookAdapter());
        new Thread(new Runnable() {
            @Override
            public void run() {
                bookDao.insert(book1, book2);
                books = bookDao.loadAll();
                rv.getAdapter().notifyDataSetChanged();
            }
        }).start();
    }

    private void initView() {
        rv = findViewById(R.id.data_base_list);
    }

    public void initDataBinding() {
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MutableLiveData<User> mutableUser = viewModel.getUser();
        mutableUser.setValue(user);

        mutableUser.observe(this, o -> binding.setMainViewModel(viewModel));
    }

    //================

    private class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {
        @NonNull
        @Override
        public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_rv, parent, false);
            BookHolder holder = new BookHolder(itemView);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull BookHolder holder, int position) {
            Book book = books.get(position);
            holder.newName.setText(book.newName);
        }

        @Override
        public int getItemCount() {
            if (books == null) return 0;
            return books.size();
        }

        class BookHolder extends RecyclerView.ViewHolder {

            public TextView newName;

            public BookHolder(@NonNull View itemView) {
                super(itemView);
                newName = itemView.findViewById(R.id.book_newName);
            }
        }
    }
}