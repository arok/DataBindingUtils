package me.chuvashev.databindingutils;

import android.databinding.BaseObservable;
import android.support.annotation.Nullable;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 08/01/16
 * Time: 18:30
 */
public class ObservableDate extends BaseObservable {

    private Date mValue;

    public ObservableDate() {
    }

    public ObservableDate(Date value) {
        mValue = value;
    }

    @Nullable
    public Date get() {
        return mValue;
    }

    public void set(@Nullable Date date) {
        if (!Objects.equals(date, mValue)) {
            mValue = date;
            notifyChange();
        }
    }
}
