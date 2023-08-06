package com.example.datapegawai;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.datapegawai.adapter.PegawaiAdapter;
import com.example.datapegawai.model.Pegawai;
import com.example.datapegawai.retrofit.ApiClient;
import com.example.datapegawai.retrofit.ApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PegawaiAdapter pegawaiAdapter;
    private List<Pegawai> pegawaiList;
    private PegawaiAdapter.RecyclerViewClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listener = new PegawaiAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(Pegawai pegawai) {
                navigateToDetail(pegawai);
            }
            @Override
            public void onEditClick(Pegawai pegawai) {
                navigateToEditForm(pegawai);
            }
            @Override
            public void onDeleteClick(Pegawai pegawai) {
                showDeleteConfirmationDialog(pegawai);
            }
        };

        pegawaiAdapter = new PegawaiAdapter(pegawaiList, listener);
        recyclerView.setAdapter(pegawaiAdapter);

        fetchData();

        FloatingActionButton fab = findViewById(R.id.btnAddData);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreatePegawaiActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showDeleteConfirmationDialog(final Pegawai pegawai) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Konfirmasi Hapus");
        builder.setMessage("Apakah Anda yakin ingin menghapus data pegawai?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deletePegawai(pegawai.getId());
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void deletePegawai(String id) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<Void> call = apiService.deletePegawai(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Success to delete pegawai", Toast.LENGTH_SHORT).show();
                    fetchData();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to delete pegawai", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchData() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<Pegawai>> call = apiService.getListPegawai();
        call.enqueue(new Callback<List<Pegawai>>() {
            @Override
            public void onResponse(Call<List<Pegawai>> call, Response<List<Pegawai>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    pegawaiList = response.body();
                    pegawaiAdapter.setData(pegawaiList);
                } else {
                    Toast.makeText(MainActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pegawai>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToEditForm(Pegawai pegawai) {
         Intent intent = new Intent(this, EditPegawaiActivity.class);
         intent.putExtra("id", pegawai.getId());
         intent.putExtra("nama", pegawai.getNama());
         intent.putExtra("provinsi", pegawai.getProvinsi());
         intent.putExtra("kabupaten", pegawai.getKabupaten());
         intent.putExtra("kecamatan", pegawai.getKecamatan());
         intent.putExtra("kelurahan", pegawai.getKelurahan());
         intent.putExtra("jalan", pegawai.getJalan());
         startActivity(intent);
    }

    private void navigateToDetail(Pegawai pegawai) {
        Intent intent = new Intent(this, DetailPegawaiActivity.class);
        intent.putExtra("nama", pegawai.getNama());
        intent.putExtra("provinsi", pegawai.getProvinsi());
        intent.putExtra("kabupaten", pegawai.getKabupaten());
        intent.putExtra("kecamatan", pegawai.getKecamatan());
        intent.putExtra("kelurahan", pegawai.getKelurahan());
        intent.putExtra("jalan", pegawai.getJalan());
        startActivity(intent);
    }
}
