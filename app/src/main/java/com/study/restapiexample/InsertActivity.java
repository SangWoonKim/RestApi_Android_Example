package com.study.restapiexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.study.restapiexample.DTO.Users;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertActivity extends AppCompatActivity {
    private EditText nickname,password;
    private Button insertButton;
    RetrofitClient.SelectAPI selectAPI;

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        selectAPI = RetrofitClient.getApiService();

        nickname = (EditText)findViewById(R.id.Nickname);
        password = (EditText)findViewById(R.id.Password);

        insertButton = (Button)findViewById(R.id.InsertButton);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> insertData = new HashMap<>();
                insertData.put("nickname", nickname.getText().toString());
                insertData.put("password",password.getText().toString());
                Call<Users> insert = selectAPI.post_Insert(insertData);
                insert.enqueue(new Callback<Users>() {
                    @Override
                    public void onResponse(Call<Users> call, Response<Users> response) {
                        if(response.isSuccessful()){
                            Users users = response.body();
                            nickname.setText("");
                            password.setText("");
                            if (users!= null){
                                Log.d("users.getNickname()", users.getNickname() + "");
                            }
                        }else{
                            Log.d(TAG,"상태코드" + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<Users> call, Throwable t) {
                        Log.d(TAG,"에러메세지:" +t.getMessage());
                    }
                });
            }
        });

    }




}
