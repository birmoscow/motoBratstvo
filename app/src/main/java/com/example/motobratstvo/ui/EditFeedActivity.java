package com.example.motobratstvo.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.motobratstvo.R;
import com.example.motobratstvo.srcActivity.SrcActivity;
import com.example.motobratstvo.checker.StringChecker;

public class EditFeedActivity extends AppCompatActivity {

    private TextView editText, editTitle, textText, textTitle;
    private Button submitButton, backButton;
    private static String TAG = "firebase";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_feed);

        editText = findViewById(R.id.editTextWriteText);
        editTitle = findViewById(R.id.editTextWriteTitle);
        textText = findViewById(R.id.textWriteText);
        textTitle = findViewById(R.id.textWriteTitle);
        submitButton = findViewById(R.id.buttonSubmit);
        backButton = findViewById(R.id.buttonBackEdit);

    }

    @Override
    public void onStart() {
        super.onStart();

        submitButton.setOnClickListener(view -> {
            StringChecker stringChecker = new StringChecker();
            String textBuff, titleBuff;

            titleBuff = editTitle.getText().toString();
            textBuff = editText.getText().toString();

            if(titleBuff != "" && textBuff!= "") {
                    SrcActivity.mDatabase.child("news")
                            .child(Integer.toString(SrcActivity.initData.getLastId() + 1))
                            .child("title").setValue(titleBuff);
                    SrcActivity.mDatabase.child("news")
                            .child(Integer.toString(SrcActivity.initData.getLastId() + 1))
                            .child("text").setValue(textBuff);

                    SrcActivity.initData.lastId++;

                SrcActivity.mDatabase.child("news")
                        .child("lastId").setValue(Integer.toString(SrcActivity.initData.lastId));

                    Toast.makeText(EditFeedActivity.this, "Posted",
                            Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(EditFeedActivity.this, "Error: cant be empty",
                        Toast.LENGTH_SHORT).show();
            }
        });
       backButton.setOnClickListener(view -> {
           super.finish();
       });

    }

}
