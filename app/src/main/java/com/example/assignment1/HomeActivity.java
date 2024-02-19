package com.example.assignment1;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    List<model> dataList;
    List<model> spinnedData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        spinner= findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        String apiurl = "https://dummyapi.online/api/pokemon";

        RequestQueue requestQueue = Volley.newRequestQueue(HomeActivity.this);
        StringRequest request = new StringRequest(Request.Method.GET, apiurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response);
                dataList = parseJson(response);
                setupRecyclerView(dataList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", "Error occurred: " + error.getMessage());
            }
        });

        requestQueue.add(request);
    }

    private List<model> parseJson(String response) {
        List<model> dataList = new ArrayList<model>();
        try {
            JSONArray jsonArray = new JSONArray(response);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("pokemon");
                String type = jsonObject.getString("type");
                String hitpoints = jsonObject.getString("hitpoints");
                String location = jsonObject.getString("location");
                String imageUrl = jsonObject.getString("image_url");
                // Create a Model object and add it to the list
                model model = new model(name,imageUrl,type,hitpoints,location);
                dataList.add(model);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    private void setupRecyclerView(List<model> dataList) {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerAdapter RecyclerAdapter = new recyclerAdapter(dataList, HomeActivity.this);
        recyclerView.setAdapter(RecyclerAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String typeText=parent.getItemAtPosition(position).toString();
        Toast.makeText(this, typeText, Toast.LENGTH_SHORT).show();
        if(dataList!=null)
            spinnedData = dataList.stream()
                    .filter(data -> data.getType().equals(typeText))
                    .collect(Collectors.toList());
        setupRecyclerView(spinnedData);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        setupRecyclerView(dataList);
    }
}
