package com.example.datapegawai.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datapegawai.MainActivity;
import com.example.datapegawai.R;
import com.example.datapegawai.model.Pegawai;

import java.util.ArrayList;
import java.util.List;

public class PegawaiAdapter extends RecyclerView.Adapter<PegawaiAdapter.PegawaiViewHolder> {
    private List<Pegawai> listPegawai;
    private RecyclerViewClickListener listener;

    public PegawaiAdapter(List<Pegawai> listPegawai, RecyclerViewClickListener listener) {
        this.listPegawai = listPegawai;
        this.listener = listener;
    }

    public void setData(List<Pegawai> data) {
        listPegawai = data;
        notifyDataSetChanged();
    }

    public class PegawaiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvNama;
        private ImageButton btnEdit, btnDelete;
        public PegawaiViewHolder(View view){
            super(view);
            tvNama = view.findViewById(R.id.tvNama);
            btnEdit = view.findViewById(R.id.btnEdit);
            btnDelete = view.findViewById(R.id.btnDelete);

            view.setOnClickListener(this);
            btnEdit.setOnClickListener(this);
            btnDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Pegawai pegawai = listPegawai.get(position);
                if (v.getId() == R.id.btnEdit) {
                    listener.onEditClick(pegawai);
                } else if (v.getId() == R.id.btnDelete) {
                    listener.onDeleteClick(pegawai);
                } else {
                    listener.onClick(pegawai);
                }
            }
        }
    }

    @NonNull
    @Override
    public PegawaiAdapter.PegawaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pegawai, parent, false);
        return new PegawaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PegawaiAdapter.PegawaiViewHolder holder, int position) {
        Pegawai pegawai = listPegawai.get(position);
        holder.tvNama.setText(pegawai.getNama());
    }

    @Override
    public int getItemCount() {
        return (listPegawai!=null) ? listPegawai.size() : 0;
    }

    public interface RecyclerViewClickListener{
        void onClick(Pegawai pegawai);
        void onEditClick(Pegawai pegawai);
        void onDeleteClick(Pegawai pegawai);
    }

}
