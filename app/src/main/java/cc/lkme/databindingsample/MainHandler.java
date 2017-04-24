package cc.lkme.databindingsample;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
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
     */
    public void onClickRecyclerView(View view) {
        Toast.makeText(context, "Method References click!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Listener Bindings
     * android:onClick="@{(view) -> handlers.onClickLambda(view)}"
     */
    public void onClickLambda(View view) {
        Toast.makeText(context, "Listener Bindings click!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Listener Bindings
     * android:onClick="@{() -> handlers.onClickLambda()}"
     */
    public void onClickLambda() {
        Toast.makeText(context, "Listener Bindings click!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Listener Bindings
     * android:onClick="@{() -> handlers.onClickLambda(user)}"
     */
    public void onClickLambda(UserInfo userInfo) {
        //Observable Objects
//        Toast.makeText(context, "Listener Bindings click! user.name=" + userInfo.getName(), Toast.LENGTH_SHORT).show();
        // ObservableField
        Toast.makeText(context, "Listener Bindings click! user.name=" + userInfo.name.get(), Toast.LENGTH_SHORT).show();
    }

    /**
     * Listener Bindings
     * android:onCheckedChanged="@{(cb, isChecked) -> handlers.onClickLambda(user, isChecked)}"
     */
    public void onClickLambda(UserInfo userInfo, boolean isChecked) {
        //Observable Objects
//        Toast.makeText(context, "Listener Bindings click! user.name=" + userInfo.getName() + " and isChecked=" + isChecked, Toast.LENGTH_SHORT).show();
        // ObservableField
        Toast.makeText(context, "Listener Bindings click! user.name=" + userInfo.name.get() + " and isChecked=" + isChecked, Toast.LENGTH_SHORT).show();
    }

    /**
     * Observable Objects
     * 检测对象的更改
     */
    public void changeObjectValue(UserInfo userInfo) {
        //Observable Objects
//       userInfo.setAge(String.valueOf(Integer.parseInt(userInfo.getAge())+ new Random().nextInt(10)));
        // ObservableField
        userInfo.age.set(String.valueOf(Integer.parseInt(userInfo.age.get()) + new Random().nextInt(10)));
    }

    /**
     * Observable Objects
     * 检测对象的更改
     */
    public void changeObjectValueThread(final UserInfo userInfo) {
        //Observable Objects
//       userInfo.setAge(String.valueOf(Integer.parseInt(userInfo.getAge())+ new Random().nextInt(10)));
        // ObservableField
        new Thread(new Runnable() {
            @Override
            public void run() {
                userInfo.age.set(String.valueOf(Integer.parseInt(userInfo.age.get()) + new Random().nextInt(10)));
            }
        }).start();
    }

    /**
     * 改变自定义Setter的值
     */
    public void changeCustomColor(View view) {
        Toast.makeText(context, "改变自定义Setter的值", Toast.LENGTH_SHORT).show();
    }

    public void showNumberType(PhoneNumberType phoneNumberType) {
        Toast.makeText(context, phoneNumberType.name(), Toast.LENGTH_SHORT).show();
    }

    public void showVal(double val) {
        Toast.makeText(context, val + "", Toast.LENGTH_SHORT).show();
    }

    public void changeVal(UserInfo uInfo) {
        uInfo.setVal(10.002);
    }

    public void colorChanged(int color){
        Toast.makeText(context, "color change:" + color, Toast.LENGTH_SHORT).show();
    }

    public void showColor(View view, int color) {
        TextView content = new TextView(view.getContext());
        content.setHeight(100);
        content.setBackgroundColor(color);
        AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).setTitle("当前颜色")
                .setView(content).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create();
        alertDialog.show();
    }


}
