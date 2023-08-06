package com.example.datapegawai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class DetailPegawaiActivity extends AppCompatActivity {

    TextView tvNama, tvProvinsi, tvKabupaten, tvKecamatan, tvKelurahan, tvJalan;
    String nama, provinsi, kabupaten, kecamatan, kelurahan, jalan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pegawai);

        ImageButton arrow_back = findViewById(R.id.arrow_back);
        arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        tvNama = findViewById(R.id.tvNama);
        tvProvinsi = findViewById(R.id.tvProvinsi);
        tvKabupaten = findViewById(R.id.tvKabupaten);
        tvKecamatan = findViewById(R.id.tvKecamatan);
        tvKelurahan = findViewById(R.id.tvKelurahan);
        tvJalan = findViewById(R.id.tvJalan);

        Bundle extras = getIntent().getExtras();
        nama = extras.getString("nama");
        provinsi = extras.getString("provinsi");
        kabupaten = extras.getString("kabupaten");
        kecamatan = extras.getString("kecamatan");
        kelurahan = extras.getString("kelurahan");
        jalan = extras.getString("jalan");

        tvNama.setText(nama);
        tvProvinsi.setText(provinsi);
        tvKabupaten.setText(kabupaten);
        tvKecamatan.setText(kecamatan);
        tvKelurahan.setText(kelurahan);
        tvJalan.setText(jalan);
    }
}