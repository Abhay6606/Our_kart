package com.example.our_kart.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.our_kart.Domain.ApiDataGetterSetter;
import com.example.our_kart.Domain.DBGetterSetter;
import com.example.our_kart.Model.DBHandler;
import com.example.our_kart.Model.Network;
import com.example.our_kart.R;
import com.example.our_kart.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    ArrayList<ApiDataGetterSetter> apiDataArrayListMain = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        getSupportActionBar().hide();

        Network network = new Network(mainBinding);
        network.apiCall(MainActivity.this);

        mainBinding.mainCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KartActivity.class);
                startActivity(intent);
            }
        });
//        DBHandler db = new DBHandler(this);
//        Log.d("fetch: ", "Reading all contacts..");
//    //    ArrayList<DBGetterSetter> getterSetterArrayList = db.callProducts();
//
//        for (DBGetterSetter dbb : getterSetterArrayList) {
//            String log = dbb.getTitle();
//            Log.d("tyytt", log);
//        }

    }

}