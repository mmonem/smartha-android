package com.mmonem.smartha;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface  EspApi {
    @GET("state")
    Call<State> load();

    @GET("relay/{number}/{state}")
    Call<Void> setRelay(@Path("number") int number, @Path("state") boolean state);
}
