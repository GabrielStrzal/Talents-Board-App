package com.alithya.talentsboard.api;

import com.alithya.talentsboard.model.FelicitationList;
import com.alithya.talentsboard.model.PersonEntity;
import com.alithya.talentsboard.model.PersonList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BackendApi {

    @GET("like/{uid}")
    Call<Integer> getNumberOfLikes(@Path("uid") String uid);


    @GET("persons/{uid}")
    Call<PersonEntity> getPerson(@Path("uid") String uid);

    @GET("persons/")
    Call<PersonList> getPersons();

    @GET("felicitations/{personUID}")
    Call<FelicitationList> getFelicitations(@Path("personUID") String personUID);
}
