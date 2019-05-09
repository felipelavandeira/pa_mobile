package com.pa.schoolnetmobile;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("https://servicodados.ibge.gov.br/api/v2/censos/nomes/ranking/")
    Call<Object> getNomes();
}
