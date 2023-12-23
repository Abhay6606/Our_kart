package com.example.our_kart.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.our_kart.Domain.DBGetterSetter;
import com.example.our_kart.Model.DBHandler;
import com.example.our_kart.R;
import com.example.our_kart.databinding.ActivityDetailBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding detailBinding;

    String detailImage, detailTitle, detailRating, detailReview, detailDescription, detailPrice;

    String itemCount = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailBinding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(detailBinding.getRoot());
        getSupportActionBar().hide();

        detailImage = getIntent().getStringExtra("detailImage");
        detailTitle = getIntent().getStringExtra("detailTitle");
        detailPrice = getIntent().getStringExtra("detailPrice");
        detailRating = getIntent().getStringExtra("detailRating");
        detailReview = getIntent().getStringExtra("detailReview");
        detailDescription = getIntent().getStringExtra("detailDescription");

        Picasso.get().load(detailImage).into(detailBinding.detailImage);
        detailBinding.detailTitle.setText(detailTitle);
        detailBinding.detailprice.setText(detailPrice);
        detailBinding.detailRating.setText(detailRating);
        detailBinding.detailReview.setText(detailReview);
        detailBinding.detailDescription.setText(detailDescription);

//
//        detailBinding.addToCartBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(DetailActivity.this,KartActivity.class);
//                intent.putExtra("cartImage",detailImage);
//                intent.putExtra("cartTitle",detailTitle);
//                intent.putExtra("cartPrice",detailPrice);
//                startActivity(intent);
//            }
//        });


        detailBinding.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String contains="false";
                ArrayList<DBGetterSetter> dbGetterSetterArrayList = new ArrayList<>();

                DBHandler dbHandler = new DBHandler(DetailActivity.this);

                dbGetterSetterArrayList = dbHandler.callProducts();
                

                for (int i = 0; i < dbGetterSetterArrayList.size(); i++) {
                    if (detailTitle.equalsIgnoreCase(dbGetterSetterArrayList.get(i).getTitle())) {
                        contains="true";
                        break;
                    }
                }

                if(contains.equalsIgnoreCase("true"))
                {
                    Toast.makeText(DetailActivity.this, "Already in cart", Toast.LENGTH_SHORT).show();
                }else {

                    dbHandler.addNewProduct(detailTitle, detailPrice, detailImage, itemCount);
                    Toast.makeText(DetailActivity.this, "Item has been added to cart", Toast.LENGTH_SHORT).show();

                }


            }
        });


    }
}