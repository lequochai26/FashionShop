package org.lequochai.fashionshop.services;

import org.lequochai.fashionshop.entities.ItemType;
import org.lequochai.fashionshop.response.RestfulResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ItemTypeService {
    @GET ("/item?method=get")
    Call<RestfulResponse<ItemType>> get(@Query( "id" ) String id);

    @GET ("/item?method=getAll")
    Call<RestfulResponse<List<ItemType>>> getAll();

    @GET ("/item?method=getByKeyword")
    Call<RestfulResponse<List<ItemType>>> getByKeyword(@Query( "keyword" ) String keyword);


}