package org.lequochai.fashionshop.services;

import org.lequochai.fashionshop.entities.CartItem;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.bodies.CartDeleteBody;
import org.lequochai.fashionshop.services.bodies.CartPostBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface CartService {
    @GET ("/cart")
    Call<RestfulResponse<List<CartItem>>> get();

    @POST ("/cart")
    Call<RestfulResponse<Void>> add(@Body CartPostBody body);

    @PUT("/cart")
    Call<RestfulResponse<Void>> remove(@Body CartDeleteBody body);
}
