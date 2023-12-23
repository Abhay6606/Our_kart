package com.example.our_kart.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.our_kart.Adapter.CartAdapter;
import com.example.our_kart.Domain.DBGetterSetter;
import com.example.our_kart.Model.DBHandler;
import com.example.our_kart.R;
import com.example.our_kart.databinding.ActivityKartBinding;
import com.example.our_kart.databinding.CartItemviewBinding;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class KartActivity extends AppCompatActivity {

    ActivityKartBinding kartBinding;
    CartAdapter cartAdapter;
    ArrayList<DBGetterSetter> dbGetterSetterArrayList = new ArrayList<>();
    ArrayList<DBGetterSetter> dbGetterSetterArrayListt = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kartBinding = ActivityKartBinding.inflate(getLayoutInflater());
        setContentView(kartBinding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        DBHandler dbHandler = new DBHandler(KartActivity.this);

        dbGetterSetterArrayList = dbHandler.callProducts();


        kartBinding.cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
         cartAdapter = new CartAdapter(KartActivity.this, dbGetterSetterArrayList,kartBinding);
        kartBinding.cartRecyclerView.setAdapter(cartAdapter);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        int idd = item.getItemId();
        if (idd == android.R.id.home) {
            getOnBackPressedDispatcher().onBackPressed();
        } else if (idd == R.id.search11) {
            Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show();
        } else if (idd == R.id.notify22) {
            Toast.makeText(this, "Notification Clicked", Toast.LENGTH_SHORT).show();
        }

        return true;
    }


//
//    public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
//
//        ArrayList<DBGetterSetter> dbGetterSetterArrayList;
//        LayoutInflater inflater;
//        CartItemviewBinding cartItemviewBinding;
//        DBGetterSetter cartGetterSetter;
//        Context context;
//
//        public CartAdapter(Context context, ArrayList<DBGetterSetter> dbGetterSetterArrayList) {
//            this.dbGetterSetterArrayList = dbGetterSetterArrayList;
//            this.context = context;
//            inflater = LayoutInflater.from(context);
//
//        }
//
//        @NonNull
//        @Override
//        public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//            cartItemviewBinding = CartItemviewBinding.inflate(inflater);
//            return new ViewHolder(cartItemviewBinding.getRoot());
//
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
//            cartGetterSetter = dbGetterSetterArrayList.get(position);
//
//            cartItemviewBinding.cartPrice.setText(cartGetterSetter.getPrice());
//            cartItemviewBinding.productCount.setText(cartGetterSetter.getItemCount() + "");  // important thing to set the text  (that is String by defoult )into any type of datatype just concat with double quotes"".
//            cartItemviewBinding.cartPpricr.setText(cartGetterSetter.getPrice());
//            cartItemviewBinding.carttitle.setText(cartGetterSetter.getTitle().substring(0, 15));
//            Picasso.get().load(cartGetterSetter.getImage()).into(cartItemviewBinding.cartImage);
//
//            cartItemviewBinding.plusBtn.setTag(position);
//            cartItemviewBinding.minusbtn.setTag(position);
//
//            cartItemviewBinding.plusBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    DBHandler dbHandler = new DBHandler(context);
//
//                    dbGetterSetterArrayList = dbHandler.callProducts();
//
//                    int pos = (int) v.getTag();
//                    cartGetterSetter = dbGetterSetterArrayList.get(pos);
//                    int currentcount= cartGetterSetter.getItemCount();
//                    String productcount = String.valueOf(currentcount+1);
//                    dbHandler.updateData(cartGetterSetter.getSnNo(), cartGetterSetter.getTitle(), cartGetterSetter.getPrice(), cartGetterSetter.getImage(), productcount);
//                    Toast.makeText(context, "clicked row is " + pos, Toast.LENGTH_SHORT).show();
//
//
//                    dbGetterSetterArrayList = dbHandler.callProducts();
//
//                    cartAdapter = new CartAdapter(KartActivity.this, dbGetterSetterArrayList);
//                    kartBinding.cartRecyclerView.setAdapter(cartAdapter);
//
//
//                }
//            });
//
//
////
//        cartItemviewBinding.minusbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                DBHandler dbHandler = new DBHandler(context);
//
//                dbGetterSetterArrayList = dbHandler.callProducts();
//
//                int pos = (int) v.getTag();
//                cartGetterSetter = dbGetterSetterArrayList.get(pos);
//                int currentcount= cartGetterSetter.getItemCount();
//                String productcount = String.valueOf(currentcount-1);
//                if(productcount.equalsIgnoreCase("0"))
//                {
//                    dbHandler.deleteData(cartGetterSetter.getSnNo());
//                }else {
//                    dbHandler.updateData(cartGetterSetter.getSnNo(), cartGetterSetter.getTitle(), cartGetterSetter.getPrice(), cartGetterSetter.getImage(), productcount);
//                } Toast.makeText(context, "clicked row is " + pos, Toast.LENGTH_SHORT).show();
//
//
//                dbGetterSetterArrayList = dbHandler.callProducts();
//
//                cartAdapter = new CartAdapter(KartActivity.this, dbGetterSetterArrayList);
//                kartBinding.cartRecyclerView.setAdapter(cartAdapter);
//
//
//            }
//        });
//
//
//        }
//
//        @Override
//        public int getItemCount() {
//            return dbGetterSetterArrayList.size();
//        }
//
//        public class ViewHolder extends RecyclerView.ViewHolder {
//            public ViewHolder(@NonNull View itemView) {
//                super(itemView);
//            }
//        }
//    }

}