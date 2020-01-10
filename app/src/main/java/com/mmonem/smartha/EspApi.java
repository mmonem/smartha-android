package com.mmonem.smartha;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EspApi {
    @GET("state")
    Call<State> load();
}
