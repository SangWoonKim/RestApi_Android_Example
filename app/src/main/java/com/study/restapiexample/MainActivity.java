package com.study.restapiexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.study.restapiexample.DTO.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView responseTextView;
    RetrofitClient.SelectAPI selectAPI;

    private Button insertButton,updateButton,deleteButton;

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseTextView = (TextView)findViewById(R.id.responsetextview);
        selectAPI = RetrofitClient.getApiService();
        insertButton = (Button)findViewById(R.id.InsertActivity);
        updateButton = (Button)findViewById(R.id.UpdateActivity);
        deleteButton = (Button)findViewById(R.id.DeleteActivity);

        insertButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);


        Call<List<Users>> getAll = selectAPI.get_All();
        getAll.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if (response.isSuccessful()){

                    List<Users> users = response.body();
                    String result="";
                    for (Users user: users){
                       result += user.toString()+"\n";
                    }
                    responseTextView.setText(result);
                }else{
                    Log.d(TAG,"상태코드" + response.code());
                    Log.d(TAG,"에러메세지" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Log.d(TAG,"에러메세지:" +t.getMessage());
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.InsertActivity:
                Intent insertIntent = new Intent(getApplicationContext(),InsertActivity.class);
                startActivity(insertIntent);
                break;

            case R.id.UpdateActivity:
                Intent updateIntent = new Intent(getApplicationContext(),UpdateActivity.class);
                startActivity(updateIntent);
                break;

            case R.id.DeleteActivity:
                Intent deleteIntent = new Intent(getApplicationContext(),DeleteActivity.class);
                startActivity(deleteIntent);
                break;
        }
    }
}
