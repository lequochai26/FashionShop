package org.lequochai.fashionshop.services;

import org.lequochai.fashionshop.entities.Item;
import org.lequochai.fashionshop.response.RestfulResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ItemService {
    @GET ("/item?method=getAll")
    Call<RestfulResponse<List<Item>>> getAll();

    @GET ("/item?method=getByKeyword")
    Call<RestfulResponse<List<Item>>> getByKeyword(@Query( "keyword" ) String keyword);

    @GET ("/item?method=get")
    Call<RestfulResponse<Item>> get(@Query( "id" ) String id);
}
