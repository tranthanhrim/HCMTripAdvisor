package com.example.tranthanhrim1995.hcmtripadvisor;

import com.example.tranthanhrim1995.hcmtripadvisor.Model.DetailThing;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.Thing;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.User;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.response.EndPointResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by tranthanhrim1995 on 1/11/2017.
 */

public interface WebServiceInterface {
    @GET("thingstodo")
    Call<List<Thing>> listThingsToDo();

    @GET("details_id?detailSearch={id}")
    Call<DetailThing> detailThing(@Path("id") String id);

    @POST("user")
    Call<EndPointResponse> createUser(@Body User user);
}
