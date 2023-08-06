package com.example.datapegawai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.datapegawai.model.KabupatenResponse;
import com.example.datapegawai.model.KecamatanResponse;
import com.example.datapegawai.model.KelurahanResponse;
import com.example.datapegawai.model.Pegawai;
import com.example.datapegawai.model.ProvinsiResponse;
import com.example.datapegawai.retrofit.ApiClient;
import com.example.datapegawai.retrofit.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPegawaiActivity extends AppCompatActivity {

    private static final String API_KEY = "b2326d07b82ba082ca985534c8202868503edc6566369d8dc866fbde1cf7047c";
    private EditText inputNama, inputJalan;
    private Spinner inputProvinsi, inputKabupaten, inputKecamatan, inputKelurahan;
    private List<ProvinsiResponse.Provinsi> provinsiResponseList;
    private List<String> provinsiNamesList;
    private ArrayAdapter<String> provinsiAdapter;
    private List<KabupatenResponse.Kabupaten> kabupatenResponseList;
    private List<String> kabupatenNamesList;
    private ArrayAdapter<String> kabupatenAdapter;
    private List<KecamatanResponse.Kecamatan> kecamatanResponseList;
    private List<String> kecamatanNamesList;
    private ArrayAdapter<String> kecamatanAdapter;
    private List<KelurahanResponse.Kelurahan> kelurahanResponseList;
    private List<String> kelurahanNamesList;
    private ArrayAdapter<String> kelurahanAdapter;
    private String selectedKabupatenId, selectedKecamatanId, selectedKelurahanId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pegawai);

        ImageButton arrow_back = findViewById(R.id.arrow_back);
        arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        inputNama = findViewById(R.id.inputNama);
        inputJalan = findViewById(R.id.inputJalan);
        inputNama.setText(getIntent().getStringExtra("nama"));
        inputJalan.setText(getIntent().getStringExtra("jalan"));

        inputProvinsi = findViewById(R.id.inputProvinsi);
        provinsiNamesList = new ArrayList<>();
        provinsiNamesList.add("Pilih Provinsi");
        provinsiAdapter = new ArrayAdapter<>(this, R.layout.item_dropdown, provinsiNamesList);
        inputProvinsi.setAdapter(provinsiAdapter);
        fetchProvinsiData();

        inputKabupaten = findViewById(R.id.inputKabupaten);
        kabupatenNamesList = new ArrayList<>();
        kabupatenNamesList.add("Pilih Kabupaten");
        kabupatenAdapter = new ArrayAdapter<>(this, R.layout.item_dropdown, kabupatenNamesList);
        inputKabupaten.setAdapter(kabupatenAdapter);

        inputKecamatan = findViewById(R.id.inputKecamatan);
        kecamatanNamesList = new ArrayList<>();
        kecamatanNamesList.add("Pilih Kecamatan");
        kecamatanAdapter = new ArrayAdapter<>(this, R.layout.item_dropdown, kecamatanNamesList);
        inputKecamatan.setAdapter(kecamatanAdapter);

        inputKelurahan = findViewById(R.id.inputKelurahan);
        kelurahanNamesList = new ArrayList<>();
        kelurahanNamesList.add("Pilih Kelurahan");
        kelurahanAdapter = new ArrayAdapter<>(this, R.layout.item_dropdown, kelurahanNamesList);
        inputKelurahan.setAdapter(kelurahanAdapter);

        inputProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    ProvinsiResponse.Provinsi selectedProvinsi = provinsiResponseList.get(position - 1);
                    fetchKabupatenData(selectedProvinsi.getId());
                    if (selectedKabupatenId != null) {
                        inputKabupaten.setSelection(0);
                        kabupatenAdapter.notifyDataSetChanged();
                    }
                } else {
                    kabupatenNamesList.clear();
                    kabupatenNamesList.add("Pilih Kabupaten");
                    kabupatenAdapter.notifyDataSetChanged();

                    kecamatanNamesList.clear();
                    kecamatanNamesList.add("Pilih Kecamatan");
                    kecamatanAdapter.notifyDataSetChanged();

                    kelurahanNamesList.clear();
                    kelurahanNamesList.add("Pilih Kelurahan");
                    kelurahanAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        inputKabupaten.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    KabupatenResponse.Kabupaten selectedKabupaten = kabupatenResponseList.get(position - 1);
                    selectedKabupatenId = selectedKabupaten.getId();
                    fetchKecamatanData(selectedKabupatenId);
                    if (selectedKecamatanId != null) {
                        inputKecamatan.setSelection(0);
                        kecamatanAdapter.notifyDataSetChanged();
                    }
                } else {
                    kecamatanNamesList.clear();
                    kecamatanNamesList.add("Pilih Kecamatan");
                    kecamatanAdapter.notifyDataSetChanged();

                    kelurahanNamesList.clear();
                    kelurahanNamesList.add("Pilih Kelurahan");
                    kelurahanAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        inputKecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    KecamatanResponse.Kecamatan selectedKecamatan = kecamatanResponseList.get(position - 1);
                    selectedKecamatanId = selectedKecamatan.getId();
                    fetchKelurahanData(selectedKecamatanId);
                    if (selectedKelurahanId != null) {
                        inputKelurahan.setSelection(0);
                        kelurahanAdapter.notifyDataSetChanged();
                    }
                } else {
                    kelurahanNamesList.clear();
                    kelurahanNamesList.add("Pilih Kelurahan");
                    kelurahanAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        inputKelurahan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    KelurahanResponse.Kelurahan selectedKelurahan = kelurahanResponseList.get(position - 1);
                    selectedKelurahanId = selectedKelurahan.getId();
                } else {
                    kelurahanNamesList.clear();
                    kelurahanNamesList.add("Pilih Kelurahan");
                    kelurahanAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = getIntent().getStringExtra("id");
                String nama = inputNama.getText().toString();
                String provinsi = inputProvinsi.getSelectedItem().toString();
                String kabupaten = inputKabupaten.getSelectedItem().toString();
                String kecamatan = inputKecamatan.getSelectedItem().toString();
                String kelurahan = inputKelurahan.getSelectedItem().toString();
                String jalan = inputJalan.getText().toString();

                updatePegawai(id, nama, provinsi, kabupaten, kecamatan, kelurahan, jalan);
            }
        });
    }

    private int getIndex(Spinner spinner, String value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(value)) {
                return i;
            }
        }
        return 0;
    }

    private void fetchProvinsiData() {
        ApiService apiService = ApiClient.getClientApiWilayah().create(ApiService.class);
        Call<ProvinsiResponse> call = apiService.getProvinsi(API_KEY);
        call.enqueue(new Callback<ProvinsiResponse>() {
            @Override
            public void onResponse(Call<ProvinsiResponse> call, Response<ProvinsiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProvinsiResponse provinsiResponse = response.body();
                    provinsiResponseList = provinsiResponse.getProvinsiList();
                    provinsiNamesList.subList(1, provinsiNamesList.size()).clear();
                    for (ProvinsiResponse.Provinsi provinsi : provinsiResponseList) {
                        provinsiNamesList.add(provinsi.getName());
                    }
                    provinsiAdapter.notifyDataSetChanged();

                    String provinsiName = getIntent().getStringExtra("provinsi");
                    int selectedProvinsiIndex = getIndex(inputProvinsi, provinsiName);
                    inputProvinsi.setSelection(selectedProvinsiIndex);
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to fetch provinsi data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProvinsiResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchKabupatenData(String idProvinsi) {
        ApiService apiService = ApiClient.getClientApiWilayah().create(ApiService.class);
        Call<KabupatenResponse> call = apiService.getKabupaten(API_KEY, idProvinsi);
        call.enqueue(new Callback<KabupatenResponse>() {
            @Override
            public void onResponse(Call<KabupatenResponse> call, Response<KabupatenResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    KabupatenResponse kabupatenResponse = response.body();
                    kabupatenResponseList = kabupatenResponse.getKabupatenList();
                    kabupatenNamesList.clear();
                    kabupatenNamesList.add("Pilih Kabupaten");
                    for (KabupatenResponse.Kabupaten kabupaten : kabupatenResponseList) {
                        kabupatenNamesList.add(kabupaten.getName());
                    }
                    kabupatenAdapter.notifyDataSetChanged();

                    String kabupatenName = getIntent().getStringExtra("kabupaten");
                    int selectedKabupatenIndex = getIndex(inputKabupaten, kabupatenName);
                    inputKabupaten.setSelection(selectedKabupatenIndex);
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to fetch kabupaten data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KabupatenResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchKecamatanData(String idKabupaten) {
        ApiService apiService = ApiClient.getClientApiWilayah().create(ApiService.class);
        Call<KecamatanResponse> call = apiService.getKecamatan(API_KEY, idKabupaten);
        call.enqueue(new Callback<KecamatanResponse>() {
            @Override
            public void onResponse(Call<KecamatanResponse> call, Response<KecamatanResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    KecamatanResponse kecamatanResponse = response.body();
                    kecamatanResponseList = kecamatanResponse.getKecamatanList();
                    kecamatanNamesList.clear();
                    kecamatanNamesList.add("Pilih Kecamatan");
                    for (KecamatanResponse.Kecamatan kecamatan : kecamatanResponseList) {
                        kecamatanNamesList.add(kecamatan.getName());
                    }
                    kecamatanAdapter.notifyDataSetChanged();

                    String kecamatanName = getIntent().getStringExtra("kecamatan");
                    int selectedKecamatanIndex = getIndex(inputKecamatan, kecamatanName);
                    inputKecamatan.setSelection(selectedKecamatanIndex);
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to fetch kecamatan data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KecamatanResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchKelurahanData(String idKecamatan) {
        ApiService apiService = ApiClient.getClientApiWilayah().create(ApiService.class);
        Call<KelurahanResponse> call = apiService.getKelurahan(API_KEY, idKecamatan);
        call.enqueue(new Callback<KelurahanResponse>() {
            @Override
            public void onResponse(Call<KelurahanResponse> call, Response<KelurahanResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    KelurahanResponse kelurahanResponse = response.body();
                    kelurahanResponseList = kelurahanResponse.getKelurahanList();
                    kelurahanNamesList.clear();
                    kelurahanNamesList.add("Pilih Kelurahan");
                    for (KelurahanResponse.Kelurahan kelurahan : kelurahanResponseList) {
                        kelurahanNamesList.add(kelurahan.getName());
                    }
                    kelurahanAdapter.notifyDataSetChanged();

                    String kelurahanName = getIntent().getStringExtra("kelurahan");
                    int selectedKelurahanIndex = getIndex(inputKelurahan, kelurahanName);
                    inputKelurahan.setSelection(selectedKelurahanIndex);
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to fetch kelurahan data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KelurahanResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updatePegawai(String id, String nama, String provinsi, String kabupaten, String kecamatan, String kelurahan, String jalan) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Pegawai pegawai = new Pegawai(id, nama, provinsi, kabupaten, kecamatan, kelurahan, jalan);

        Call<Pegawai> call = apiService.updatePegawai(id, pegawai);
        call.enqueue(new Callback<Pegawai>() {
            @Override
            public void onResponse(Call<Pegawai> call, Response<Pegawai> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(getApplicationContext(), "Success to update pegawai", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(EditPegawaiActivity.this, "Failed to update pegawai", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pegawai> call, Throwable t) {
                Toast.makeText(EditPegawaiActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
