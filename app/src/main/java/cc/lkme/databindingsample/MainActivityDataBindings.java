package cc.lkme.databindingsample;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import cc.lkme.databindingsample.model.UserInfo;

/**
 * Created by LinkedME06 on 16/8/25.
 */
public class MainActivityDataBindings {

    /**
     *
     * 该方式类似于自定义视图时自定义相关属性,该方法的强大之处在于<b>可以进行逻辑的处理</b>
     *
     * 此处bind可自定义,无固定要求
     * @param view 需要绑定的组件类型(必选参数)
     * @param userInfo 用户信息
     * @param defaultColor 参数值
     */
    @BindingAdapter({"bind:userInfo", "bind:customColor"})
    public static void setCustomColor(final TextView view, final UserInfo userInfo, final int defaultColor){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(userInfo.age.get()) % 2 == 0){
                    view.setTextColor(Color.YELLOW);
                }else {
                    view.setTextColor(defaultColor);
                }
            }
        });

    }
}
