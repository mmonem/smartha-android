package com.mmonem.smartha;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new GsonBuilder().setLenient().create();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.254.2/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                EspApi mApi = retrofit.create(EspApi.class);
                Call<State > call = mApi.load();
                call.enqueue(new Callback<State>() {
                    @Override
                    public void onResponse(Call<State> call, Response<State> response) {
                        Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                        updateState(response.body());
                    }

                    @Override
                    public void onFailure(Call<State> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void updateState(State state) {
        ((TextView)findViewById(R.id.textView)).setText(String.valueOf(state.r1));
        ((TextView)findViewById(R.id.textView2)).setText(String.valueOf(state.r2));
        ((TextView)findViewById(R.id.textView3)).setText(String.valueOf(state.t));
    }
}
