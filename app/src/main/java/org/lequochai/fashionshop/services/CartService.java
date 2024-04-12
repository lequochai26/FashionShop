package org.lequochai.fashionshop.services;

import org.lequochai.fashionshop.entities.CartItem;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.bodies.CartDeleteBody;
import org.lequochai.fashionshop.services.bodies.CartPostBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CartService {
    @GET ("/cart")
    Call<RestfulResponse<List<CartItem>>> get();

    @POST ("/cart")
    Call<RestfulResponse<Void>> add(@Body CartPostBody body);

    @DELETE ("/cart")
    Call<RestfulResponse<Void>> remove(@Body CartDeleteBody body);
}
