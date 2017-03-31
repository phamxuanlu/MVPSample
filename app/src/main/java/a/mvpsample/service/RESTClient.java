package a.mvpsample.service;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pham.xuan.lu on 3/31/17.
 */
public class RESTClient {
    private static RESTClient instance;
    private static FuckService service;
    private static final String ENDPOINT_URL = "https://testfirebasecm.firebaseio.com/";

    public static RESTClient getInstance() {
        if (instance == null) {
            instance = new RESTClient();
        }
        return instance;
    }

    private static final Interceptor AUTHENTICATE_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();
            HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("auth",
                    "eyJhbGciOiJSUzI1NiIsImtpZCI6IjIyYjE1NjU1N2M5MTgwYTU5MzlkOGNiYTliOWNiOWMyZjNmYTFiZTEifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdGVzdGZpcmViYXNlY20iLCJwcm92aWRlcl9pZCI6ImFub255bW91cyIsImF1ZCI6InRlc3RmaXJlYmFzZWNtIiwiYXV0aF90aW1lIjoxNDkwODU5MTIzLCJ1c2VyX2lkIjoiamZLUmxxRUQ3Z1hGMUtPa2dybGQzUHJpNzFKMiIsInN1YiI6ImpmS1JscUVEN2dYRjFLT2tncmxkM1ByaTcxSjIiLCJpYXQiOjE0OTA5MjkyMjYsImV4cCI6MTQ5MDkzMjgyNiwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6e30sInNpZ25faW5fcHJvdmlkZXIiOiJhbm9ueW1vdXMifX0.Rkza46Q5VUHKWA5EPzBXaUdaukO0UaGBrAPYzmPA4i6nBBRqh6KW4hhLO1wVvK-VGhAYLAXAmpQwTHL5hvHEKXuCwD3WPOBqaZwIHRU2W7ZDYd6uebXf2xWVyE3c2SVyDUEMCA26JTuLWaFLmPDr1-gVVZgpP8dK7Hoo2ghOPFwEVBUK6gu8ylqKKinjIIjE4V7vyrOhQIkG6KGuTNQllhIWgLz4M2IllxpDd1FJNZRg3SBSrDBHuDdJICzneng6FwkMYNp0W-N4YjW94FsVqvN3rznCuOI8ydpZy9LcvG4_IaYtRcnJyrLkCDodT-Sz1Xx3GoeDi6pQ7P_ccIfHCQ")
                .build();
            Request.Builder requestBuilder = original.newBuilder().url(url);
            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    };

    public FuckService getService() {
        if (service == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            clientBuilder.addInterceptor(AUTHENTICATE_INTERCEPTOR);
            clientBuilder.addInterceptor(interceptor);
            Retrofit retrofit = new Retrofit
                .Builder()
                .client(clientBuilder.build())
                .baseUrl(ENDPOINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
            service = retrofit.create(FuckService.class);
        }
        return service;
    }
}
