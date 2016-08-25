package cc.lkme.databindingsample;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

import cc.lkme.databindingsample.model.UserInfo;

/**
 * Created by LinkedME06 on 16/8/15.
 */
public class MainHandler {

    private Context context;

    public MainHandler(Context context) {
        this.context = context;
    }

    /**
     * Method References
     * @param view
     */
    public void onClickRecyclerView(View view){
        Toast.makeText(context, "Method References click!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Listener Bindings
     * android:onClick="@{(view) -> handlers.onClickLambda(view)}"
     * @param view
     */
    public void onClickLambda(View view){
        Toast.makeText(context, "Listener Bindings click!", Toast.LENGTH_SHORT).show();
    }
    /**
     * Listener Bindings
     * android:onClick="@{() -> handlers.onClickLambda()}"
     */
    public void onClickLambda(){
        Toast.makeText(context, "Listener Bindings click!", Toast.LENGTH_SHORT).show();
    }
    /**
     * Listener Bindings
     * android:onClick="@{() -> handlers.onClickLambda(user)}"
     */
    public void onClickLambda(UserInfo userInfo){
        //Observable Objects
//        Toast.makeText(context, "Listener Bindings click! user.name=" + userInfo.getName(), Toast.LENGTH_SHORT).show();
        // ObservableField
        Toast.makeText(context, "Listener Bindings click! user.name=" + userInfo.name.get(), Toast.LENGTH_SHORT).show();
    }
    /**
     * Listener Bindings
     * android:onCheckedChanged="@{(cb, isChecked) -> handlers.onClickLambda(user, isChecked)}"
     */
    public void onClickLambda(UserInfo userInfo, boolean isChecked){
        //Observable Objects
//        Toast.makeText(context, "Listener Bindings click! user.name=" + userInfo.getName() + " and isChecked=" + isChecked, Toast.LENGTH_SHORT).show();
        // ObservableField
        Toast.makeText(context, "Listener Bindings click! user.name=" + userInfo.name.get() + " and isChecked=" + isChecked, Toast.LENGTH_SHORT).show();
    }
    /**
     * Observable Objects
     * 检测对象的更改
     */
    public void changeObjectValue(UserInfo userInfo){
        //Observable Objects
//       userInfo.setAge(String.valueOf(Integer.parseInt(userInfo.getAge())+ new Random().nextInt(10)));
        // ObservableField
       userInfo.age.set(String.valueOf(Integer.parseInt(userInfo.age.get())+ new Random().nextInt(10)));
    }
    /**
     * Observable Objects
     * 检测对象的更改
     */
    public void changeObjectValueThread(final UserInfo userInfo){
        //Observable Objects
//       userInfo.setAge(String.valueOf(Integer.parseInt(userInfo.getAge())+ new Random().nextInt(10)));
        // ObservableField
        new Thread(new Runnable() {
            @Override
            public void run() {
                userInfo.age.set(String.valueOf(Integer.parseInt(userInfo.age.get())+ new Random().nextInt(10)));
            }
        }).start();
    }

    /**
     * 改变自定义Setter的值
     * @param view
     */
    public void changeCustomColor(View view){
        Toast.makeText(context, "改变自定义Setter的值", Toast.LENGTH_SHORT).show();
    }
}
