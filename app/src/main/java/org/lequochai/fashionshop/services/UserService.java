package org.lequochai.fashionshop.services;

import org.lequochai.fashionshop.entities.User;
import org.lequochai.fashionshop.response.RestfulResponse;
import org.lequochai.fashionshop.services.bodies.UserBody;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface UserService {
    @GET("/user?method=getLoggedIn")
    Call<RestfulResponse<User>> getLoggedIn();

    @POST("/user?method=logout")
    Call<RestfulResponse<Void>> logout();

    @POST("/user?method=login")
    Call<RestfulResponse<Void>> login(@Body UserBody userBody);

    @POST("/user?method=register")
    Call<RestfulResponse<Void>> register(@Body UserBody userBody);

    @PUT("/user?method=update")
    Call<RestfulResponse<Void>> update(@Body UserBody userBody);

    @PUT("/user?method=updatePersonalInfo")
    Call<RestfulResponse<Void>> updatePersonalInfo(@Body UserBody userBody);

    @PUT("/user?method=changePassword")
    Call<RestfulResponse<Void>> changePassword(@Body UserBody userBody);
}
