package cc.lkme.databindingsample.model;

import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import cc.lkme.databindingsample.BR;
import cc.lkme.databindingsample.R;

/**
 * Created by LinkedME06 on 19/04/2017.
 */

public class DependentUser extends BaseObservable {
    private String firstName;
    private String lastName;
    private String userName;
    private Resources resources;


    public DependentUser(Resources resources) {
        this.resources = resources;
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @Bindable({"firstName", "lastName", "userName"})
    public String getDisplayName() {
        if (firstName == null) {
            if (lastName == null) {
                return userName;
            } else {
                return lastName;
            }
        } else if (lastName == null) {
            return firstName;
        } else {
            return resources.getString(R.string.display_name,
                    firstName, lastName);
        }
    }
}
