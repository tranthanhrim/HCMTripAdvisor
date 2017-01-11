package com.example.tranthanhrim1995.hcmtripadvisor;

import com.example.tranthanhrim1995.hcmtripadvisor.Model.Thing;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tranthanhrim1995 on 1/11/2017.
 */

public interface WebServiceInterface {
    @GET("thingstodo")
    Call<List<Thing>> listThingsToDo();
}
