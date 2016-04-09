package me.chuvashev.databindingutils;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.CompoundButton;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 09/04/16
 * Time: 21:29
 */
public class ObservableBoolean extends BaseObservable implements Serializable, Parcelable {

    private boolean mValue;

    public ObservableBoolean() {
    }

    public ObservableBoolean(boolean value) {
        this.mValue = value;
    }

    protected ObservableBoolean(Parcel in) {
        mValue = in.readByte() != 0;
    }

    public boolean get() {
        return mValue;
    }

    public void set(boolean value) {
        if (mValue != value) {
            mValue = value;
            notifyChange();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (mValue ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ObservableBoolean> CREATOR = new Creator<ObservableBoolean>() {
        @Override
        public ObservableBoolean createFromParcel(Parcel in) {
            return new ObservableBoolean(in);
        }

        @Override
        public ObservableBoolean[] newArray(int size) {
            return new ObservableBoolean[size];
        }
    };

    @BindingConversion
    public static boolean convert(ObservableBoolean bool) {
        if (bool == null) {
            return false;
        }
        return bool.get();
    }

    @BindingAdapter("bind:observeChecked")
    public static void bind(CompoundButton button, final ObservableBoolean value) {
        if (button.getTag(R.id.observable_boolean_listener) == null) {
            button.setTag(R.id.observable_boolean_listener, Boolean.TRUE);

            button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (value != null) {
                        value.set(isChecked);
                    }
                }
            });
        }

        if (value != null) {
            button.setChecked(value.get());
        }
    }

}
