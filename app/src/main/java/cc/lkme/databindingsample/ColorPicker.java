package cc.lkme.databindingsample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by LinkedME06 on 20/04/2017.
 */

public class ColorPicker extends View {
    private int color;
    private OnColorChangeListener onColorChangeListener;
    private Paint textPaint;

    public ColorPicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (isInEditMode()) {
            setColor(R.color.colorAccent);
        }
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setColor(Color.argb(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)));
            }
        });
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
    }

    public void setColor(int color) {
        this.color = color;
        if (onColorChangeListener != null) {
            onColorChangeListener.onColorChange(this, color);
        }
        invalidate();
    }

    public int getColor() {
        return color;
    }

    public void addListener(OnColorChangeListener listener) {
        onColorChangeListener = listener;
    }

    public void removeListener(OnColorChangeListener listener) {
        onColorChangeListener = null;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        textPaint.setTextSize(getMeasuredHeight());
        canvas.drawText("点击变更颜色", 0, getMeasuredHeight(), textPaint);
        setBackgroundColor(color);
    }

    public interface OnColorChangeListener {
        void onColorChange(ColorPicker colorPicker, int newColor);
    }


}
