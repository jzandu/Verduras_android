package com.pes.verduras_android;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private List data;
    private Context context;

    public void addAlimentos(List<String> alimentoList) {
        data.addAll(alimentoList);
        notifyDataSetChanged();
    }
    //Hay que crear esta clase para q no de error
    //Asign the text TextView to the text1 in the layout
    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        private TextView nombreAlimento;


        public ViewHolder(View v) {
            super(v);
            nombreAlimento = v.findViewById(R.id.nombreAlimento);

            linearLayout = v.findViewById(R.id.linea_resultado);
        }
    }

    //Constructor
    public RecyclerAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.alimentos_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        Alimento nombreAlimento = data.get(position);
        holder.nombreAlimento.setText(unitario);
        }

    @Override
    public int getItemCount() {
        return data.size();
    }
}


