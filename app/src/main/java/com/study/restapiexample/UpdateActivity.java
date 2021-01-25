package com.study.restapiexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.study.restapiexample.DTO.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {

    private EditText search_name, update_name;
    private Button update_button;

    private final String TAG = getClass().getSimpleName();

    RetrofitClient.SelectAPI selectAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        selectAPI = RetrofitClient.getApiService();
        search_name = (EditText)findViewById(R.id.search_name);
        update_name = (EditText)findViewById(R.id.update_name);

        update_button = (Button)findViewById(R.id.update_button);
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchValue = search_name.getText().toString();
                String updateValue = update_name.getText().toString();
                Call<Users> update = selectAPI.put_Update(searchValue,updateValue);
                update.enqueue(new Callback<Users>() {
                    @Override
                    public void onResponse(Call<Users> call, Response<Users> response) {
                        if (response.isSuccessful()){
                            search_name.setText("");
                            update_name.setText("");
                            Users users = response.body();
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
