package com.example.sampleapp;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private Context mcontext;
    private List<ModelClass> mData;

    public ListAdapter(Context mcontext, List<ModelClass> mData) {
        this.mcontext = mcontext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        view=inflater.inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText(mData.get(position).getId());
        holder.name.setText(mData.get(position).getName());

        Glide.with(mcontext).load(mData.get(position).getImg()).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();

/// Here the Tile is being set
                //alertDialog.setTitle("Alert Dialog Example.");

/// Here the message content is being set
                alertDialog.setMessage("Here the description of the image can be displayed");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id,name;
        ImageView  img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.tv_id);
            name=itemView.findViewById(R.id.tv_name);
            img=itemView.findViewById(R.id.iv_image);
        }

    }
}
