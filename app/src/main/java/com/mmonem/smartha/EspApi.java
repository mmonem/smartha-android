package com.mmonem.smartha;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EspApi {
    @GET("get")
    Call<State> load();
}
