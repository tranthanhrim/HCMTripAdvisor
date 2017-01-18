package com.example.tranthanhrim1995.hcmtripadvisor;

import com.example.tranthanhrim1995.hcmtripadvisor.Model.Comment;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.DetailImage;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.DetailThing;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.Thing;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.ThingDistance;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.User;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.response.EndPointResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by tranthanhrim1995 on 1/11/2017.
 */

public interface WebServiceInterface {
    @GET("thingstodo")
    Call<List<Thing>> listThingsToDo();

    @GET("thingstodo_type?typeSearch={typeOfThing}")
    Call<List<Thing>> getlistThingsWithType(@Path("typeOfThing") String typeOfThing);

    @GET("thingstodo_destination")
    Call<List<Thing>> getListDestination();

    @GET("details_id")
    Call<List<DetailThing>> detailThing(@Query("detailSearch") String detailSearch);

    @GET("images")
    Call<List<DetailImage>> detailImage(@Query("imageSearch") String imageSearch);

    @POST("users")
    Call<EndPointResponse> createUser(@Body User user);

    @POST("comments")
    Call<EndPointResponse> postComment(@Body HashMap map);
//    Call<EndPointResponse> postComment(@Path("_idUser") String idUser, @Path("_idThing") String idThing, @Path("_content") String content);

    @POST("ratings")
    Call<EndPointResponse> postRate(@Body HashMap map);

    @GET("comments")
    Call<List<Comment>> getComments(@Query("_idThing") String _idThing);

    @GET("nearme")
    Call<List<ThingDistance>> getListIdThingNearMe(@Query("lon") double lon, @Query("lat") double lat);

    @GET("topthingstodo")
    Call<List<Thing>> getListTopThingsToDo();

    @GET("ratescounting")
    Call<Map> getRateCounting(@Query("_idThing") String _idThing);

    @GET("personrating")
    Call<Map> getPersonalRating(@Query("_idUser") String _idUser, @Query("_idThing") String _idThing);
}
