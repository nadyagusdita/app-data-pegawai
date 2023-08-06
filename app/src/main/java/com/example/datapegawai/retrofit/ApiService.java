package com.example.datapegawai.retrofit;

import com.example.datapegawai.model.KabupatenResponse;
import com.example.datapegawai.model.KecamatanResponse;
import com.example.datapegawai.model.KelurahanResponse;
import com.example.datapegawai.model.Pegawai;
import com.example.datapegawai.model.ProvinsiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("pegawai")
    Call<List<Pegawai>> getListPegawai();
    @FormUrlEncoded
    @POST("pegawai")
    Call<Pegawai> createPegawai(
            @Field("nama") String nama,
            @Field("provinsi") String provinsi,
            @Field("kabupaten") String kabupaten,
            @Field("kecamatan") String kecamatan,
            @Field("kelurahan") String kelurahan,
            @Field("jalan") String jalan
    );
    @PUT("pegawai/{id}")
    Call<Pegawai> updatePegawai(
            @Path("id") String id,
            @Body Pegawai pegawai
    );
    @DELETE("pegawai/{id}")
    Call<Void> deletePegawai(@Path("id") String id);

    @GET("provinsi")
    Call<ProvinsiResponse> getProvinsi(@Query("api_key") String apiKey);

    @GET("kabupaten")
    Call<KabupatenResponse> getKabupaten(
            @Query("api_key") String apiKey,
            @Query("id_provinsi") String idProvinsi
    );

    @GET("kecamatan")
    Call<KecamatanResponse> getKecamatan(
            @Query("api_key") String apiKey,
            @Query("id_kabupaten") String idKabupaten
    );

    @GET("kelurahan")
    Call<KelurahanResponse> getKelurahan(
            @Query("api_key") String apiKey,
            @Query("id_kecamatan") String idKecamatan
    );
}
