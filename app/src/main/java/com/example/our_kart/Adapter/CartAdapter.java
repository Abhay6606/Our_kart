package com.example.our_kart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.our_kart.Activities.KartActivity;
import com.example.our_kart.Domain.DBGetterSetter;
import com.example.our_kart.Model.DBHandler;
import com.example.our_kart.databinding.ActivityKartBinding;
import com.example.our_kart.databinding.CartItemviewBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    ArrayList<DBGetterSetter> dbGetterSetterArrayList;
    LayoutInflater inflater;
    CartAdapter cartAdapter;
    ActivityKartBinding kartBinding;
    CartItemviewBinding cartItemviewBinding;
    DBGetterSetter cartGetterSetter;
    Context context;

    
    public CartAdapter(Context context, ArrayList<DBGetterSetter> dbGetterSetterArrayList, ActivityKartBinding kartBinding) {
        this.dbGetterSetterArrayList = dbGetterSetterArrayList;
        this.context = context;
        this.kartBinding = kartBinding;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        cartItemviewBinding = CartItemviewBinding.inflate(inflater);
        return new ViewHolder(cartItemviewBinding.getRoot());

    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        cartGetterSetter = dbGetterSetterArrayList.get(position);

        cartItemviewBinding.cartPrice.setText(cartGetterSetter.getPrice());
        cartItemviewBinding.productCount.setText(cartGetterSetter.getItemCount() + "");  // important thing to set the text  (that is String by defoult )into any type of datatype just concat with double quotes"".
        cartItemviewBinding.carttitle.setText(cartGetterSetter.getTitle().substring(0, 15));
        Picasso.get().load(cartGetterSetter.getImage()).into(cartItemviewBinding.cartImage);

        double updtePrice = Double.parseDouble(cartGetterSetter.getPrice()) * cartGetterSetter.getItemCount();
        cartItemviewBinding.cartPpricr.setText(Double.toString(updtePrice));


        cartItemviewBinding.plusBtn.setTag(position);
        cartItemviewBinding.minusbtn.setTag(position);

        cartItemviewBinding.plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHandler dbHandler = new DBHandler(context);

                dbGetterSetterArrayList = dbHandler.callProducts();

                int pos = (int) v.getTag();
                cartGetterSetter = dbGetterSetterArrayList.get(pos);
                int currentcount = cartGetterSetter.getItemCount();
                String productcount = String.valueOf(currentcount + 1);
                dbHandler.updateData(cartGetterSetter.getSnNo(), cartGetterSetter.getTitle(), cartGetterSetter.getPrice(), cartGetterSetter.getImage(), productcount);
                // Toast.makeText(context, "clicked row is " + pos, Toast.LENGTH_SHORT).show();

                dbGetterSetterArrayList = dbHandler.callProducts();
                cartAdapter = new CartAdapter(context, dbGetterSetterArrayList, kartBinding);
                kartBinding.cartRecyclerView.setAdapter(cartAdapter);

                systemm();

            }
        });



//
        cartItemviewBinding.minusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHandler dbHandler = new DBHandler(context);

                dbGetterSetterArrayList = dbHandler.callProducts();

                int pos = (int) v.getTag();
                cartGetterSetter = dbGetterSetterArrayList.get(pos);
                int currentcount = cartGetterSetter.getItemCount();
                String productcount = String.valueOf(currentcount - 1);
                if (productcount.equalsIgnoreCase("0")) {
                    dbHandler.deleteData(cartGetterSetter.getSnNo());
                } else {
                    dbHandler.updateData(cartGetterSetter.getSnNo(), cartGetterSetter.getTitle(), cartGetterSetter.getPrice(), cartGetterSetter.getImage(), productcount);
                }
                //Toast.makeText(context, "clicked row is " + pos, Toast.LENGTH_SHORT).show();


                dbGetterSetterArrayList = dbHandler.callProducts();

                cartAdapter = new CartAdapter(context, dbGetterSetterArrayList, kartBinding);
                kartBinding.cartRecyclerView.setAdapter(cartAdapter);

                systemm();
            }
        });
        //==========================================================================================

        systemm();


    }

    public void systemm(){

        double sum=0;
        Double totalPricee =null;
        int delivery=150;
        for (int i=0;i<dbGetterSetterArrayList.size();i++){
            sum=sum+dbGetterSetterArrayList.get(i).getTotalPrice();
            totalPricee=sum;
        }
        kartBinding.subTotal.setText(String.valueOf(totalPricee));
        kartBinding.delivery.setText(String.valueOf(delivery));
        kartBinding.totalTax.setText(String.valueOf("50"));
        kartBinding.total.setText(String.valueOf(totalPricee+delivery+"50"));
    }


    @Override
    public int getItemCount() {
        return dbGetterSetterArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
