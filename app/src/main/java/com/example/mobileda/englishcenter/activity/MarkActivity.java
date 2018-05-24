package com.example.mobileda.englishcenter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mobileda.englishcenter.R;

public class MarkActivity extends AppCompatActivity {

    ImageButton btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_subject);
        btnReturn = (ImageButton) findViewById(R.id.btnReturnSubject);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_result_subject);
            }
        });
    }

}
