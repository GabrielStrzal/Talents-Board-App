package com.alithya.talentsboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alithya.talentsboard.service.BackendService;


public class FelicitationActivity extends AppCompatActivity {

    private TextView textViewResult;
    String uidValue = "uid-1";

    private BackendService backendService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_felicitation);

        configureBackButton();

        textViewResult = findViewById(R.id.text_view_result);

        backendService = new BackendService(textViewResult);


        backendService.getNumberOfMessages(uidValue);
        backendService.getPersons();
        backendService.getFelicitations("p-001");

    }

    private void configureBackButton() {
        Button felicitationButton = (Button) findViewById(R.id.backButton);
        felicitationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FelicitationActivity.this, MainActivity.class));
            }
        });
    }
}
