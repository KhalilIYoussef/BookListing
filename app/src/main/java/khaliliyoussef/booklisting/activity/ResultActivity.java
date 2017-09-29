package khaliliyoussef.booklisting.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import khaliliyoussef.booklisting.R;
import khaliliyoussef.booklisting.adapter.BookAdapter;
import khaliliyoussef.booklisting.model.Book;
import khaliliyoussef.booklisting.utils.NetworkUtil;

import static android.content.res.Resources.getSystem;

public class ResultActivity extends AppCompatActivity {

    private ListView listView;
    private ProgressBar progressBar;
    private BookAdapter mAdapter;
    private TextView noInternet;
    private static final String BOOK_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("ResultActivity");
        setContentView(R.layout.list_view);

        String title = getIntent().getStringExtra("title");

        String end = "&maxResults=20";
        Log.e("title: ",title);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        noInternet = (TextView)findViewById(R.id.no_internet);

        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        String newUrl = BOOK_REQUEST_URL + title.toLowerCase() + end;
        Log.e("The new URL",newUrl);
        listView = (ListView) findViewById(R.id.list);

        BookAsyncTask task = new BookAsyncTask();

        if (isConnected) {
            task.execute(newUrl );
        }
        else {
            progressBar.setVisibility(View.GONE);
            noInternet.setText(R.string.no_internet);
        }
        mAdapter = new BookAdapter(this, new ArrayList<Book>());
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Book book = mAdapter.getItem(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(book.getUrl()));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }
    private class BookAsyncTask extends AsyncTask<String, Void, List<Book>> {

        @Override
        protected List<Book> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
           List<Book> result =  NetworkUtil.fetchBookList(urls[0]);
            return result;
        }

        protected void onPostExecute(List<Book> data) {

            mAdapter.clear();
            progressBar.setVisibility(View.GONE);

            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);

            }else{noInternet.setText(R.string.no_data_found);}
        }
    }
}
