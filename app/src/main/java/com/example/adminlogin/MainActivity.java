package com.example.adminlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText adminID, adminPass;
    Button LoginAdmin;
    RequestQueue queue;
    int length=0;
    int index=0;
    public static String url="https://soorveer-api.herokuapp.com/api/admin/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adminID = findViewById(R.id.adminID);
        adminPass = findViewById(R.id.adminPass);
        LoginAdmin = findViewById(R.id.loginAdmin);


        LoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
    }


    public void getData()
    {
        ProgressDialog progress;
        progress= ProgressDialog.show(this,"Logging In","",true);
        String id=adminID.getText().toString();
        String pass=adminPass.getText().toString();
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url+id+"/"+pass, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    progress.dismiss();
                    if(jsonObject.getBoolean("success")==true){
                        Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Admin credential are wrong please try again", Toast.LENGTH_SHORT).show();
                    }

                    Log.i("data :",response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error",error.toString());
            }


        }
        );
        queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
    @Override
    public void onBackPressed()
    {
        return ;
    }


}