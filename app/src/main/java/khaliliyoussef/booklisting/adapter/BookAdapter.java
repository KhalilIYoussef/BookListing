package khaliliyoussef.booklisting.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import khaliliyoussef.booklisting.R;
import khaliliyoussef.booklisting.model.Book;

/**
 * Created by khalil on the 28/09/2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    private final static String AUTHOR = "Author: ";
    private final static String EDITOR = "Editor: ";
    private final static String PAGE = "Page: ";

    public BookAdapter(Activity context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    private static class ViewHolder {
        ImageView thumbnail;
        TextView mTitle;
        TextView mAuthor;
        TextView mEditor;
        TextView mPage;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        View bookList = convertView;
        if (bookList == null) {
            bookList = LayoutInflater.from(getContext()).inflate(R.layout.book_listing_item, parent, false);
            holder = new ViewHolder();
            holder.thumbnail = (ImageView) bookList.findViewById(R.id.thumbnail);
            holder.mTitle = (TextView) bookList.findViewById(R.id.book_title);
            holder.mAuthor = (TextView) bookList.findViewById(R.id.autore);
            holder.mEditor = (TextView) bookList.findViewById(R.id.editore);
            holder.mPage = (TextView) bookList.findViewById(R.id.pagine);
            bookList.setTag(holder);
        } else {
            holder = (ViewHolder) bookList.getTag();
        }
        Book currentBook = getItem(position);

        Picasso.with(getContext()).load(currentBook.getThumbnail()).into(holder.thumbnail);

        holder.mTitle.setText(currentBook.getTitle());

        holder.mAuthor.setText(AUTHOR + currentBook.getAuthor());

        holder.mEditor.setText(EDITOR + currentBook.getEditor());

        holder.mPage.setText(PAGE + currentBook.getPage());

        return bookList;
    }

}
