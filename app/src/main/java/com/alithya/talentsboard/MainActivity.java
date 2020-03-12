package com.alithya.talentsboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    public void buttonClickedFelicitation(View view){

        EditText emailEditText = (EditText) findViewById(R.id.editText2);
        Toast.makeText(this, "Logged in as " + emailEditText.getText(), Toast.LENGTH_LONG).show();
        startActivity(new Intent(MainActivity.this, FelicitatioinListActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Info", "Test");

    }


}
