package com.alithya.talentsboard;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.alithya.talentsboard.api.BackendApi;
import com.alithya.talentsboard.model.PersonEntity;
import com.alithya.talentsboard.model.PersonList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PersonsListActivity extends AppCompatActivity {

    public void goToOldView(View view) {
        startActivity(new Intent(PersonsListActivity.this, FelicitationActivity.class));
    }

    public void goBack(View view) {
        startActivity(new Intent(PersonsListActivity.this, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons_list);
        new PersonsListActivity.BackendAsync().execute();

    }




    public void setListAdapter(ListAdapter la)
    {
        ListView lv = (ListView) findViewById(R.id.personsList);
        lv.setAdapter(la);
    }


    class BackendAsync extends AsyncTask<Void, Void, PersonList> {
        @Override
        protected PersonList doInBackground(Void... voids) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://talentsboardapiapp.eba-7henmwsv.ca-central-1.elasticbeanstalk.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            BackendApi backendApi = retrofit.create(BackendApi.class);

            final Call<PersonList> call = backendApi.getPersons();

            // Fetch and print a list of the contributors to the library.
            try{
                PersonList personList = call.execute().body();
                for (PersonEntity post: personList.getPersonList()) {
                    //Log.i("INFO", post.title + " (" + post.body+ ")");
                }
                return personList;
            }
            catch(IOException e)
            {

            }
            return null;
        }

        protected void onPostExecute(PersonList personList) {

            Log.i("INFO", "onPostExecute");

            List<String> titles = new ArrayList<String>();
            for (PersonEntity personEntity: personList.getPersonList())
            {
                titles.add(personEntity.getFirstName() + " " + personEntity.getLastName() + "\n " + personEntity.getEmail());
            }
            ArrayAdapter<String> aa = new ArrayAdapter<String>(PersonsListActivity.this, android.R.layout.simple_list_item_1,titles);
            PersonsListActivity.this.setListAdapter(aa);

        }
    }
}
