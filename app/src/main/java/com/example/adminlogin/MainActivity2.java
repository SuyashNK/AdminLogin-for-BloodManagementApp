package com.example.adminlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity2 extends AppCompatActivity {
    EditText addLocation, sampleIDAdmin;
    Button updateAdmin,logout;
    RequestQueue queue;
    public static String url="https://soorveer-api.herokuapp.com/api/donar/admin/";
    String sample;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        addLocation = findViewById(R.id.addLocation);
        sampleIDAdmin = findViewById(R.id.sampleid);
        updateAdmin = findViewById(R.id.updateAdmin);
        logout=findViewById(R.id.logout);
        sample= sampleIDAdmin.getText().toString();

        logout.setOnClickListener(v->{
            Intent intent=new Intent(MainActivity2.this,MainActivity.class);
            startActivity(intent);
        });

        updateAdmin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                queue = Volley.newRequestQueue(getApplicationContext());
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("location",addLocation.getText().toString());


                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url+sampleIDAdmin.getText().toString(), jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(MainActivity2.this, "Location added successfully", Toast.LENGTH_SHORT).show();
                        Log.d("Response : ",response.toString());
                        Log.i("in",  sample);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error : ",error.toString());
                    }
                });
                queue.add(jsonObjectRequest);

            }
        });

    }
    @Override
    public void onBackPressed(){
        return ;
    }
}