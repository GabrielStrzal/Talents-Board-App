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
import com.alithya.talentsboard.model.FelicitationList;
import com.alithya.talentsboard.model.FelicitationResponse;
import com.alithya.talentsboard.model.PersonEntity;
import com.alithya.talentsboard.model.PersonList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FelicitatioinListActivity extends AppCompatActivity {

    public void goToPersonList(View view) {
        startActivity(new Intent(FelicitatioinListActivity.this, PersonsListActivity.class));
    }

    public void goBack(View view) {
        startActivity(new Intent(FelicitatioinListActivity.this, MainActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_felicitatioin_list);

        new BackendAsync().execute();

    }




    public void setListAdapter(ListAdapter la)
    {
        ListView lv = (ListView) findViewById(R.id.personsList);
        lv.setAdapter(la);
    }


    class BackendAsync extends AsyncTask<Void, Void, FelicitationList> {


        @Override
        protected FelicitationList doInBackground(Void... voids) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://talentsboardapiapp.eba-7henmwsv.ca-central-1.elasticbeanstalk.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            BackendApi backendApi = retrofit.create(BackendApi.class);

            final Call<FelicitationList> call = backendApi.getFelicitations("p-001");

            // Fetch and print a list of the contributors to the library.
            try{
                FelicitationList felicitationList = call.execute().body();
                for (FelicitationResponse felicitationResponse: felicitationList.getFelicitationEntityList()) {
                    //Log.i("INFO", post.title + " (" + post.body+ ")");
                }
                return felicitationList;
            }
            catch(IOException e)
            {

            }
            return null;
        }

        protected void onPostExecute(FelicitationList felicitationList) {

            Log.i("INFO", "onPostExecute");

            List<String> titles = new ArrayList<String>();
            for (FelicitationResponse felicitationResponse: felicitationList.getFelicitationEntityList())
            {
                titles.add( felicitationResponse.getToPerson().getFirstName() + " " + felicitationResponse.getToPerson().getLastName() + "\n\n"   +
                            felicitationResponse.getMessage() + "\n\n" +
                            "From: " + felicitationResponse.getFromPerson().getFirstName() + " " + felicitationResponse.getFromPerson().getLastName() );
            }
            ArrayAdapter<String> aa = new ArrayAdapter<String>(FelicitatioinListActivity.this, android.R.layout.simple_list_item_1,titles);
            FelicitatioinListActivity.this.setListAdapter(aa);

        }
    }
}
