package com.alithya.talentsboard.service;

import android.widget.TextView;

import com.alithya.talentsboard.api.BackendApi;
import com.alithya.talentsboard.model.FelicitationList;
import com.alithya.talentsboard.model.FelicitationResponse;
import com.alithya.talentsboard.model.PersonEntity;
import com.alithya.talentsboard.model.PersonList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BackendService {

    private TextView textViewResult;
    private Retrofit retrofit;
    private BackendApi backendApi;

    public BackendService(TextView textViewResult) {
        this.textViewResult = textViewResult;

        retrofit = new Retrofit.Builder()
                .baseUrl("http://talentsboardapiapp.eba-7henmwsv.ca-central-1.elasticbeanstalk.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        backendApi = retrofit.create(BackendApi.class);

    }

    public TextView getNumberOfMessages(String uidValue){

        Call<Integer> call = backendApi.getNumberOfLikes(uidValue);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                textViewResult.append("Number of likes: " + response.body() + "\n");
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
        return textViewResult;
    }


    public TextView getPersons(){

        Call<PersonList> call = backendApi.getPersons();

        call.enqueue(new Callback<PersonList>() {
            @Override
            public void onResponse(Call<PersonList> call, Response<PersonList> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                for( PersonEntity p :response.body().getPersonList()) {
                    textViewResult.append(p.getFirstName() + "\n");
                }
            }

            @Override
            public void onFailure(Call<PersonList> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
        return textViewResult;
    }



    public TextView getFelicitations(String personUID){

        Call<FelicitationList> call = backendApi.getFelicitations(personUID);

        call.enqueue(new Callback<FelicitationList>() {
            @Override
            public void onResponse(Call<FelicitationList> call, Response<FelicitationList> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                for( FelicitationResponse f :response.body().getFelicitationEntityList()) {
                    textViewResult.append(f.getMessage() + "\n");
                }

            }

            @Override
            public void onFailure(Call<FelicitationList> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
        return textViewResult;
    }


}
