package com.ushi.radiohandler.widget.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.AttributeSet;
import android.view.View;

import com.ushi.radiohandler.R;

/**
 * like AppCompatBackgroundHelper in support-appcompat
 */
public class BackgroundTintHelper {

    private View mView;

    private ColorStateList mBackgroundTint;

    private PorterDuff.Mode mBackgroundTintMode;

    public BackgroundTintHelper(@NonNull View view, AttributeSet attrs) {
        mView = view;

        Context context = mView.getContext();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BackgroundTintHelper, 0, 0);

        if (ta.hasValue(R.styleable.BackgroundTintHelper_backgroundTint)) {
            setBackgroundTintList(ta.getColorStateList(R.styleable.BackgroundTintHelper_backgroundTint));
        }

        int modeOrder = ta.getInt(R.styleable.BackgroundTintHelper_backgroundTintMode, -1);
        setBackgroundTintMode(modeOrder != -1 ? PorterDuff.Mode.values()[modeOrder] : PorterDuff.Mode.SRC_IN);

        ta.recycle();
    }

    private void applyBackgroundTint() {
        final Drawable background = mView.getBackground();
        if (background == null) return;

        if (mBackgroundTint != null && mBackgroundTintMode != null) {
            PorterDuffColorFilter colorFilter = AppCompatDrawableManager.getPorterDuffColorFilter(
                    mBackgroundTint.getColorForState(mView.getDrawableState(), mBackgroundTint.getDefaultColor()),
                    mBackgroundTintMode);
            background.setColorFilter(colorFilter);

        } else {
            background.clearColorFilter();
        }
    }

    public void setBackgroundTintList(ColorStateList tint) {
        mBackgroundTint = tint;
        applyBackgroundTint();
    }

    @Nullable
    public ColorStateList getBackgroundTintList() {
        return mBackgroundTint;
    }

    public void setBackgroundTintMode(@Nullable PorterDuff.Mode tintMode) {
        mBackgroundTintMode = tintMode;
        applyBackgroundTint();
    }

    @Nullable
    public PorterDuff.Mode getBackgroundTintMode() {
        return mBackgroundTintMode;
    }

    public void drawableStateChanged() {
        applyBackgroundTint();
    }
}
