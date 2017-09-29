package khaliliyoussef.booklisting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import khaliliyoussef.booklisting.R;


public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;
    private String bookTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.search_library);
        editText = (EditText) findViewById(R.id.insert_library);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookTitle = editText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("title", bookTitle);

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
    }
}
