package org.lequochai.fashionshop.services;

import org.lequochai.fashionshop.entities.Order;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.bodies.OrderBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface OrderService {

    @GET("/order?method=get")
    Call<RestfulResponse<Order>> get(@Query("id") String id);

    @GET("/order?method=getOrdered")
    Call<RestfulResponse<List<Order>>> getOrdered();

    @POST("/order?method=create")
    Call<RestfulResponse<Void>> create();

    @PUT("/order?method=cancel")
    Call<RestfulResponse<Void>> cancel(@Query("id") String id);
}
