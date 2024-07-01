package com.example.friendapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Viewfrnds extends AppCompatActivity {

    TextView t1;
    String apiUrl="https://friendsapi-re5a.onrender.com/view";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_viewfrnds);
        t1=(TextView)findViewById(R.id.txt);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(
                Request.Method.GET,
                apiUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response)
                    {

//                        Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                            StringBuilder result=new StringBuilder();
                            for (int i=0;i<response.length();i++)
                            {
                                try {
                                    JSONObject friend = response.getJSONObject(i);
                                    String name=friend.getString("name");
                                    String fname=friend.getString("friendName");
                                    String fnickname=friend.getString("friendNickName");
                                    String desc=friend.getString("DescribeYourFriend");

                                    result.append(name).append(" ").append(fname).append(" ").append(fnickname).append(" ").append(desc).append("\n");
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            t1.setText(result.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);

    }
}