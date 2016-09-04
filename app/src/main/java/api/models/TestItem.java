package api.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vsekr_000 on 04.09.2016.
 * Тестовый айтем
 */
public class TestItem implements Parcelable {

    public int id;

    public String name;

    public float version;

    public String image;

    protected TestItem(Parcel in) {
        id = in.readInt();
        name = in.readString();
        version = in.readFloat();
        image = in.readString();
    }

    public static final Creator<TestItem> CREATOR = new Creator<TestItem>() {
        @Override
        public TestItem createFromParcel(Parcel in) {
            return new TestItem(in);
        }

        @Override
        public TestItem[] newArray(int size) {
            return new TestItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeFloat(version);
        dest.writeString(image);
    }
}
