package cc.lkme.databindingsample.model;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

/**
 * Created by LinkedME06 on 16/8/13.
 */
//public class UserInfo extends BaseObservable{
//    @Bindable
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//        notifyPropertyChanged(BR.name);
//    }
//
//
//    private String name;
//
//    @Bindable
//    public boolean isShow() {
//        return show;
//    }
//
//    public void setShow(boolean show) {
//        this.show = show;
//        notifyPropertyChanged(BR.show);
//    }
//
//    private boolean show;
//
//    @Bindable
//    public String getAge() {
//        return age;
//    }
//
//    public void setAge(String age) {
//        this.age = age;
//        notifyPropertyChanged(BR.age);
//    }
//
//    //不能是int类型,会出现问题,Resource Not Found Exception,因为系统把int类型当成了一个系统资源,所以找不到
//    private String age;
//
//    @Bindable
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//        notifyPropertyChanged(BR.address);
//    }
//
//    private String address;
//
//}

public class UserInfo{
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> age = new ObservableField<>();
    public final ObservableField<String> address = new ObservableField<>();
    public final ObservableBoolean show = new ObservableBoolean();

}
