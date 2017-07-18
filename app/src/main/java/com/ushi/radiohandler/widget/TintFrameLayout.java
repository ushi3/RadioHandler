package com.ushi.radiohandler.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.support.v4.view.TintableBackgroundView;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.ushi.radiohandler.widget.internal.BackgroundTintHelper;

/**
 * FrameLayout for Background tinting.
 *
 * @author ushi
 */
public class TintFrameLayout extends FrameLayout implements TintableBackgroundView {

    private BackgroundTintHelper mBackgroundTintHelper;

    public TintFrameLayout(Context context) {
        this(context, null);
    }

    public TintFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TintFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBackgroundTintHelper = new BackgroundTintHelper(this, attrs);
    }

    @Override
    public void setSupportBackgroundTintList(@Nullable ColorStateList tint) {
        mBackgroundTintHelper.setBackgroundTintList(tint);
    }

    @Nullable
    @Override
    public ColorStateList getSupportBackgroundTintList() {
        return mBackgroundTintHelper.getBackgroundTintList();
    }

    @Override
    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode tintMode) {
        mBackgroundTintHelper.setBackgroundTintMode(tintMode);
    }

    @Nullable
    @Override
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return mBackgroundTintHelper.getBackgroundTintMode();
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        mBackgroundTintHelper.drawableStateChanged();
    }

}
