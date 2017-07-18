package com.ushi.radiohandler.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Checkable;

import com.ushi.radiohandler.R;


public class CheckableFrameLayout extends TintFrameLayout implements Checkable {

    private static final int[] STATE_CHECKED = {android.R.attr.state_checked};

    private boolean mChecked;

    public CheckableFrameLayout(Context context) {
        this(context, null);
    }

    public CheckableFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckableFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CheckableFrameLayout, defStyleAttr, 0);
        setChecked(ta.getBoolean(R.styleable.CheckableFrameLayout_android_checked, false));

        ta.recycle();
    }

    @Override
    public boolean performClick() {
        toggle();
        return super.performClick();
    }

    @Override
    public void setChecked(boolean checked) {
        if (mChecked != checked) {
            mChecked = checked;
            refreshDrawableState();
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        int[] states = super.onCreateDrawableState(extraSpace + STATE_CHECKED.length);

        if (mChecked) {
            mergeDrawableStates(states, STATE_CHECKED);
        }

        return states;
    }
}
