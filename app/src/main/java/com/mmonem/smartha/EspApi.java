package com.mmonem.smartha;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface  EspApi {
    @GET("{number}/")

    Call<Void> setRelay(@Path("number") int number);
}
