package me.chuvashev.databindingutils;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.widget.EditText;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 12/09/15
 * Time: 20:15
 */
public class ObservableString extends BaseObservable implements Parcelable, Serializable {

    private String mValue;

    public ObservableString() {
    }

    public ObservableString(String value) {
        this.mValue = value;
    }

    protected ObservableString(Parcel in) {
        mValue = in.readString();
    }

    @NonNull
    public String get() {
        if (mValue == null) {
            return "";
        }
        return mValue;
    }

    public void set(@Nullable String text) {
        if (!Objects.equals(text, mValue)) {
            mValue = text;
            notifyChange();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mValue);
    }

    public static final Creator<ObservableString> CREATOR = new Creator<ObservableString>() {
        @Override
        public ObservableString createFromParcel(Parcel in) {
            return new ObservableString(in);
        }

        @Override
        public ObservableString[] newArray(int size) {
            return new ObservableString[size];
        }
    };

    @BindingConversion
    public static String convert(ObservableString string) {
        return string.get();
    }

    @BindingAdapter("bind:observeText")
    public static void bindEditText(EditText editText, final ObservableString text) {
        if (editText.getTag(R.id.text_watcher_listener) == null) {
            editText.setTag(R.id.text_watcher_listener, true);

            editText.addTextChangedListener(new TextWatcherAdapter() {
                @Override
                public void afterTextChanged(Editable s) {
                    text.set(s.toString());
                }
            });
        }

        String newText = text.get();
        if (!Objects.equals(newText, editText.getText().toString())) {
            editText.setText(newText);
        }
    }

}
