package com.company.mz.utility;

import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

@UtilityClass
public class RetrofitUtil {
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new PrettyJsonLogger());
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    public Retrofit getRetrofit(){
        loggingInterceptor.setLevel(BODY);
        httpClient.addInterceptor(loggingInterceptor);
        return new Retrofit.Builder()
                .baseUrl(ConfigUtil.getBaseUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }
}
