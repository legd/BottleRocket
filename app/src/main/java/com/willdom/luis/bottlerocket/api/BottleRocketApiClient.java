package com.willdom.luis.bottlerocket.api;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.willdom.luis.bottlerocket.api.interfaces.BottleRocketApi;
import com.willdom.luis.bottlerocket.BuildConfig;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA;
import static okhttp3.CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA256;
import static okhttp3.CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256;
import static okhttp3.CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA;
import static okhttp3.CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA256;
import static okhttp3.CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384;
import static okhttp3.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256;
import static okhttp3.CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA;
import static okhttp3.CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256;
import static okhttp3.CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256;
import static okhttp3.CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA;
import static okhttp3.CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384;
import static okhttp3.CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384;

/**
 * Class for wrapping the initialization of the Retrofit and BottleRocketApi objects, using a
 * singleton.
 *
 * @author Luis Guzman
 */
public class BottleRocketApiClient {

    private static final String TAG = BottleRocketApiClient.class.getName();
    private static BottleRocketApi sApiClient;

    /**
     * Private constructor for the Singleton pattern.
     */
    private BottleRocketApiClient() {

    }

    /**
     * Method for the Singleton design pattern implementation.
     *
     * @param baseURL Url to be use for the API calls
     * @return BottleRocketApi instance
     */
    public static BottleRocketApi getInstance(String baseURL) {

        if(sApiClient == null) {

            Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL,
                    Modifier.TRANSIENT, Modifier.STATIC)
                    .serializeNulls()
                    .create();

            OkHttpClient.Builder httpClientBuilder = getHttpClientBuilder();

            // Enable logging for debug
            if(BuildConfig.DEBUG) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
                httpClientBuilder.addInterceptor(loggingInterceptor);
            }

            sApiClient = new Retrofit.Builder().baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .callFactory(httpClientBuilder.build())
                    .build()
                    .create(BottleRocketApi.class);
        }

        return sApiClient;
    }

    /**
     *  Returns the OkHttpClient builder which is used by the Retrofit API to send HTTP requests and
     *  read their responses.
     *
     * @return OkHttpClient.Builder object to be used by the Retrofit API
     */
    static OkHttpClient.Builder getHttpClientBuilder() {
        // For now I'm using a plain connection with no security, this is no a good practice
        return new OkHttpClient.Builder().connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS,
                ConnectionSpec.CLEARTEXT));

//        return new OkHttpClient.Builder().connectionSpecs(Collections.singletonList(
//                getConnectionSpec()));
    }

    /**
     * Returns a ConnectionSpec object that specifies configuration for the socket connection that
     * HTTP traffic travels through. For https: URLs, this includes the TLS version and cipher
     * suites to use when negotiating a secure connection.
     *
     * @return ConnectionSpec object to be used by the OkHttpClientBuilder
     */
    @NonNull
    private static ConnectionSpec getConnectionSpec() {

        return new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .cipherSuites(TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                        TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                        TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
                        TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384,
                        TLS_DHE_RSA_WITH_AES_256_GCM_SHA384,
                        TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384,
                        TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
                        TLS_DHE_RSA_WITH_AES_256_CBC_SHA256,
                        TLS_DHE_RSA_WITH_AES_256_CBC_SHA,
                        TLS_DHE_RSA_WITH_AES_128_CBC_SHA256,
                        TLS_DHE_RSA_WITH_AES_128_CBC_SHA,
                        TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256,
                        TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA)
                .build();
    }
}
