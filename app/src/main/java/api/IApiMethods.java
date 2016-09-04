package api;

import api.models.TestResponse;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by vsekr_000 on 04.09.2016.
 * Интерфейс с методами API
 */
public interface IApiMethods {

    @GET("/server_data.txt")
    Observable<TestResponse> getItems();
}
