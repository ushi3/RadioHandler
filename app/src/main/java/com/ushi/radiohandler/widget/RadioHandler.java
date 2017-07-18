package com.ushi.radiohandler.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import android.widget.FrameLayout;

import com.ushi.radiohandler.R;
import com.ushi.radiohandler.widget.internal.ViewFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ushi
 */
public class RadioHandler extends FrameLayout {

    private Checkable mCheckableChild;

    private final List<RadioHandler> mHandlers = new ArrayList<>();

    private final String mGroupTag;

    // 選択状態から未選択状態に戻せるかどうか
    private boolean mCanRevertUnchecked;

    public RadioHandler(Context context) {
        this(context, null);
    }

    public RadioHandler(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadioHandler(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RadioHandler, defStyleAttr, 0);
        mGroupTag = ta.getString(R.styleable.RadioHandler_radioGroupTag);
        mCanRevertUnchecked = ta.getBoolean(R.styleable.RadioHandler_canRevertUnchecked, false);

        ta.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        // find child
        List<Checkable> types = ViewFinder.findViewAsTypes(Checkable.class, this, false);
        if (types.isEmpty()) {
            mCheckableChild = null;

        } else {
            if (types.size() == 1) {
                mCheckableChild = types.get(0);
            } else {
                throw new IllegalStateException("Checkable child must have only one.");
            }
        }

        // find handlers
        mHandlers.clear();
        for (RadioHandler handler : ViewFinder.findViewAsTypes(RadioHandler.class, getParent(), true)) {
            if (Objects.equals(mGroupTag, handler.mGroupTag)) {
                mHandlers.add(handler);
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mCanRevertUnchecked) {
            return super.onInterceptTouchEvent(ev);
        }

        return mCheckableChild.isChecked();
    }

    @Override
    public void childDrawableStateChanged(View child) {
        super.childDrawableStateChanged(child);

        if (mCheckableChild != null && child == mCheckableChild) {
            if (mCheckableChild.isChecked()) {
                postRadioExclusiveHandling();
            }
        }
    }

    private void postRadioExclusiveHandling() {
        for (RadioHandler handler : mHandlers) {
            if (handler == this) {
                continue;
            }

            handler.dispatchChildDrawableStateChanged(this);
        }
    }

    private void dispatchChildDrawableStateChanged(RadioHandler caller) {
        if (caller != this && mCheckableChild != null) {
            mCheckableChild.setChecked(false);
        }
    }
}