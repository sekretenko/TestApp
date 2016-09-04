package api.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by vsekr_000 on 04.09.2016.
 * Тестовый response
 */
public class TestResponse implements Parcelable {
    public String header;

    //Список айтемов
    public List<TestItem> body;

    protected TestResponse(Parcel in) {
        header = in.readString();
    }

    public static final Creator<TestResponse> CREATOR = new Creator<TestResponse>() {
        @Override
        public TestResponse createFromParcel(Parcel in) {
            return new TestResponse(in);
        }

        @Override
        public TestResponse[] newArray(int size) {
            return new TestResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(header);
    }
}
