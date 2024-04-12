package org.lequochai.fashionshop.services;


import org.lequochai.fashionshop.entities.Brand;
import org.lequochai.fashionshop.response.RestfulResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BrandService {
    @GET ("/brand?method=get")
    Call<RestfulResponse<Brand>> get(@Query("id") String id);

    @GET("/brand?method=getAll")
    Call<RestfulResponse<List<Brand>>> getAll();

    @GET("/brand?method=getByKeyword")
    Call<RestfulResponse<List<Brand>>> getByKeyword(@Query("keyword") String keyword);

}


