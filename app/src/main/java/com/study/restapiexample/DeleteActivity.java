package com.study.restapiexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.study.restapiexample.DTO.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteActivity extends AppCompatActivity {

    private Button delete_Button;
    private EditText delete_Name;

    private final String TAG = getClass().getSimpleName();

    RetrofitClient.SelectAPI selectAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        selectAPI=RetrofitClient.getApiService();

        delete_Name = (EditText) findViewById(R.id.delete_name);
        delete_Button=(Button)findViewById(R.id.delete_button);

        delete_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String delete_Value = delete_Name.getText().toString();
                Call<Users> delete = selectAPI.delete_Delete(delete_Value);
                delete.enqueue(new Callback<Users>() {
                    @Override
                    public void onResponse(Call<Users> call, Response<Users> response) {
                        if (response.isSuccessful()){
                            delete_Name.setText("");
                            Users user = response.body();
                            if (user!=null){
                                Log.d("delete", delete_Value);
                            }else{
                                Log.d(TAG,"상태코드" + response.code());
                            }

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
