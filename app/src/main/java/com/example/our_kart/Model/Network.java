package com.example.our_kart.Model;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.recyclerview.widget.GridLayoutManager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.our_kart.Adapter.MainRecyclerAdapter;
import com.example.our_kart.Domain.ApiDataGetterSetter;
import com.example.our_kart.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Network {

    ArrayList<ApiDataGetterSetter> apiDataArrayList = new ArrayList<>();

    ActivityMainBinding mainBinding;

    public Network(ActivityMainBinding mainBinding) {
        this.mainBinding = mainBinding;
    }


    public void apiCall(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = " https://fakestoreapi.com/products";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        ApiDataGetterSetter pack = new ApiDataGetterSetter();

                        JSONObject object = new JSONObject(String.valueOf(response.getJSONObject(i)));
                        JSONObject object1 = object.getJSONObject("rating");

                        pack.setImage(object.getString("image"));
                        pack.setTitle(object.getString("title"));
                        pack.setPrice(object.getString("price"));
                        pack.setRating(object1.getString("rate"));
                        pack.setReview(object1.getString("count"));
                        pack.setDescription(object.getString("description"));

                        apiDataArrayList.add(pack);

                    }
                    //==========================================================================================
                    //RecycleView work

                    mainBinding.mainRecylerView.setLayoutManager(new GridLayoutManager(context, 2));
                    MainRecyclerAdapter adapter = new MainRecyclerAdapter(context, apiDataArrayList);
                    mainBinding.mainRecylerView.setAdapter(adapter);


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        queue.add(jsonArrayRequest);

    }


}
