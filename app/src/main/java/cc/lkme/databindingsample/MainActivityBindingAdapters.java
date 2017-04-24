package cc.lkme.databindingsample;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.databinding.adapters.ListenerUtil;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import cc.lkme.databindingsample.model.UserInfo;

/**
 * Created by LinkedME06 on 16/8/25.
 */
@InverseBindingMethods({
        @InverseBindingMethod(type = cc.lkme.databindingsample.ColorPicker.class,
                attribute = "color",
                method = "getColor")
})
public class MainActivityBindingAdapters {

    /**
     * 该方式类似于自定义视图时自定义相关属性,该方法的强大之处在于<b>可以进行逻辑的处理</b>
     *
     * 此处bind可自定义,无固定要求
     *
     * @param view         需要绑定的组件类型(必选参数)
     * @param userInfo     用户信息
     * @param defaultColor 参数值
     */
    @BindingAdapter(value = {"userInfo", "customColor"}, requireAll = false)
    public static void setCustomColor(final TextView view, final UserInfo userInfo, final int defaultColor) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(userInfo.age.get()) % 2 == 0) {
                    view.setTextColor(Color.YELLOW);
                } else {
                    view.setTextColor(defaultColor);
                }
            }
        });

    }


//    @InverseBindingAdapter(attribute = "color")
//    public static int getColor(ColorPicker view) {
//        return convertColorToInt(view.getColor());
//    }
//
//    @BindingAdapter("color")
//    public static void setColor(ColorPicker view, int color) {
//        view.setColor(convertIntToColor(color));
//    }

    @BindingAdapter(value = {"onColorChange", "colorAttrChanged"},
            requireAll = false)
    public static void setColorChangeListener(ColorPicker view,
                                              final ColorPicker.OnColorChangeListener onColorChangeListener,
                                              final InverseBindingListener inverseBindingListener) {
        ColorPicker.OnColorChangeListener newListener;
        if (inverseBindingListener == null) {
            newListener = onColorChangeListener;
        } else {
            newListener = new ColorPicker.OnColorChangeListener() {
                @Override
                public void onColorChange(ColorPicker colorPicker,
                                          int newColor) {
                    if (onColorChangeListener != null) {
                        onColorChangeListener.onColorChange(colorPicker,
                                newColor);
                    }
                    inverseBindingListener.onChange();
                }
            };
        }

        ColorPicker.OnColorChangeListener oldListener =
                ListenerUtil.trackListener(view, newListener,
                        R.id.colorChangeListener);

        if (oldListener != null) {
            view.removeListener(oldListener);
        }
        if (newListener != null) {
            view.addListener(newListener);
        }
    }


}
