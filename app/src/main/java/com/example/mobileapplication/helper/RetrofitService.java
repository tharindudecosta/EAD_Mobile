package com.example.mobileapplication.helper;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.net.ssl.*;
import java.security.cert.CertificateException;

/*
*https://www.youtube.com/watch?v=lz5lPAdA3fQ&t=258s&pp=ygUZcmV0cm9maXQgYW5kcm9pZCB0dXRvcmlhbA%3D%3D
*https://www.youtube.com/playlist?list=PLoYPuzsyna1k-GZfhW8D2UHPKzEQ6bdlW
* */

public class RetrofitService {
    private Retrofit retrofit;

    private static String TUNNEL_URL = "https://27e9-2402-4000-b250-2d3d-8d89-c161-9950-d442.ngrok-free.app/";
    private static String LOCAL_URL = "http://10.0.2.2:1000/";

    public RetrofitService() {
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(TUNNEL_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient getUnsafeOkHttpClient() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session) -> true);

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
