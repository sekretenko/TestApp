package api;


import api.models.TestItem;
import api.models.TestResponse;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Created by vsekr_000 on 04.09.2016.
 * Класс для работы с тестовым апи
 */
public class TestApi {
    public static String BASE_URL = "https://dl.dropboxusercontent.com/s/2kwni1y9iclkmjj/server_data/";

    IApiMethods service;

    public TestApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();

        service = retrofit.create(IApiMethods.class);
    }


    public Observable<TestResponse> getItems() {
        return service.getItems();
    }
}
