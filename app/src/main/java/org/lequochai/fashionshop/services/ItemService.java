package org.lequochai.fashionshop.services;

import org.lequochai.fashionshop.entities.Item;
import org.lequochai.fashionshop.response.RestfulResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ItemService {
    @GET ("/item?method=getAll")
    Call<RestfulResponse<List<Item>>> getAll();
}
