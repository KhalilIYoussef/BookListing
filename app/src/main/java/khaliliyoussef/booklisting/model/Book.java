package khaliliyoussef.booklisting.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by khalil on the 28/09/2017.
 */

public class Book implements Parcelable {

    private String mThumbnail;
    private String mTitle;
    private StringBuilder mAuthor;
    private String mEditor;
    private String mPage;
    private String mUrl;

    public Book(String thumbnail, String title, StringBuilder author, String editor, String page, String url) {

        mThumbnail = thumbnail;
        mTitle = title;
        mAuthor = author;
        mEditor = editor;
        mPage = page;
        mUrl = url;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getThumbnail() {
        return mThumbnail;
    }

    public String getTitle() {
        return mTitle;
    }

    public StringBuilder getAuthor() {
        return mAuthor;
    }

    public String getEditor() {
        return mEditor;
    }

    public String getPage() {
        return mPage;
    }

    public String getUrl() {
        return mUrl;
    }

    protected Book(Parcel in) {
        mEditor = in.readString();
        mPage = in.readString();
        mThumbnail = in.readString();
        mUrl = in.readString();
        mTitle = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mThumbnail);
        dest.writeString(mTitle);
        dest.writeString(mEditor);
        dest.writeString(mPage);
        dest.writeString(mUrl);
    }
}
