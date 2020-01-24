package com.mmonem.smartha;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
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

    private Switch relay1Switch;
    private Switch relay2Switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relay1Switch = findViewById(R.id.relay1);

        relay1Switch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setRelay(1, b);
            }
        });

        relay2Switch = findViewById(R.id.relay2);

        relay2Switch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setRelay(2, b);
            }
        });


    }

    private void setRelay(int number, final boolean state) {
        String onOff = String.valueOf(state);
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.254.2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        EspApi mApi = retrofit.create(EspApi.class);
        Call<Void> call = mApi.setRelay(number, state);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                relay1Switch.setChecked(!state);
            }
        });
    }
}
