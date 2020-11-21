package com.example.myfinalproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    Context context;
    ArrayList<ProductClass> products;


    public ProductAdapter(Context c,ArrayList<ProductClass> p){
        context = c;
        products = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.p_name.setText(products.get(position).getName());
        holder.p_description.setText(products.get(position).getDescription());
        holder.p_price.setText(products.get(position).getPrice().toString());
        Picasso.get().load(products.get(position).getImage()).into(holder.p_image);
        holder.onClick();
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView p_name,p_description,p_price;
        ImageView p_image;
        FloatingActionButton addtocart;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            p_name = (TextView) itemView.findViewById(R.id.txt_product_name);
            p_description = (TextView) itemView.findViewById(R.id.txt_product_description);
            p_price = (TextView) itemView.findViewById(R.id.txt_product_price);
            p_image = (ImageView) itemView.findViewById(R.id.product_image);
            addtocart = (FloatingActionButton) itemView.findViewById(R.id.btn_addtocart);
        }

        public void onClick(){
            addtocart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context.getApplicationContext(), Success.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}



