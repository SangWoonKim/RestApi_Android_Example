package com.study.restapiexample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.study.restapiexample.DTO.Users;

import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RetrofitClient {
    private static final String BASE_URL = "http://10.0.2.2:3000/v1/users/"; //TODO REST API 퍼블릭 IP로 변경

    public static SelectAPI getApiService() {
        return getInstance().create(SelectAPI.class);
    }

    private static Retrofit getInstance() {

        Gson gson = new GsonBuilder().setLenient().create();
            return new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(new OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
}


    interface SelectAPI {
        @GET("index")
        Call<List<Users>> get_All();

        @FormUrlEncoded
        @POST("create")
        Call<Users> post_Insert(@FieldMap HashMap<String, String> parameters);


        @PUT("update/{nickname}/{nicknameParam}")
        Call<Users> put_Update(@Path("nickname") String searchName, @Path("nicknameParam") String updateName);

        @DELETE("delete/{nickname}")
        Call<Users> delete_Delete(@Path("nickname") String deleteName);
    }
}

