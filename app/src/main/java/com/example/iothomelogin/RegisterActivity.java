package com.example.iothomelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_id, et_pass, et_email, et_number;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );

        //아이디값 찾아주기
        et_id = findViewById( R.id.et_id );
        et_pass = findViewById( R.id.et_pass );
        et_email = findViewById( R.id.et_email );
        et_number = findViewById( R.id.et_number);


        //회원가입 버튼 클릭 시 수행
        btn_register = findViewById( R.id.btn_join);
        btn_register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userid = et_id.getText().toString();
                String password = et_pass.getText().toString();
                String email = et_email.getText().toString();
                String phone=  et_number.getText().toString() ;


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );

                            //회원가입 성공시
                            if(success) {

                                Toast.makeText( getApplicationContext(), "성공", Toast.LENGTH_SHORT ).show();
                                Intent intent = new Intent( RegisterActivity.this, LoginActivity.class );
                                startActivity( intent );

                                //회원가입 실패시
                            } else {
                                Toast.makeText( getApplicationContext(), "실패", Toast.LENGTH_SHORT ).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                //서버로 Volley를 이용해서 요청
                RegisterRequest registerRequest = new RegisterRequest( userid, password, email, phone, responseListener);
                RequestQueue queue = Volley.newRequestQueue( RegisterActivity.this );
                queue.add( registerRequest );
            }
        });
    }
}