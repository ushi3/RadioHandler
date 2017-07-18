package com.ushi.radiohandler.widget.internal;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.ArrayList;
import java.util.List;

/**
 * find dependent view
 *
 * @author ushi
 */
public class ViewFinder {

    public static <T extends View> T findView(Class<T> viewClass, ViewParent viewParent, int id) {
        if (!(viewParent instanceof ViewGroup) || id == View.NO_ID) {
            return null;
        }

        ViewGroup viewGroup = (ViewGroup) viewParent;
        View view = viewGroup.findViewById(id);
        if (view == null) {
            return findView(viewClass, viewGroup.getParent(), id);
        }
        if (viewClass.isInstance(view)) {
            return viewClass.cast(view);
        }

        return null;
    }

    /**
     * @param viewClass find this class
     * @param viewParent view
     * @param referHierarchy if true, refer view hierarchy
     * @param <T> any type
     * @return List of found view
     */
    public static <T> List<View> findViews(Class<T> viewClass, ViewParent viewParent, boolean referHierarchy) {
        ArrayList<View> list = new ArrayList<>();
        if (!(viewParent instanceof ViewGroup)) {
            return list;
        }

        ViewGroup viewGroup = (ViewGroup) viewParent;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            if (viewClass.isInstance(child)) {
                list.add(child);

            } else {
                if (referHierarchy && child instanceof ViewParent) {
                    list.addAll(findViews(viewClass, (ViewParent) child, true));
                }
            }
        }

        return list;
    }

    public static <T> List<T> findViewAsTypes(Class<T> typeClass, ViewParent viewParent, boolean referHierarchy) {
        ArrayList<T> list = new ArrayList<>();
        if (!(viewParent instanceof ViewGroup)) {
            return list;
        }

        ViewGroup viewGroup = (ViewGroup) viewParent;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            if (typeClass.isInstance(child)) {
                list.add(typeClass.cast(child));

            } else {
                if (referHierarchy && child instanceof ViewParent) {
                    list.addAll(findViewAsTypes(typeClass, (ViewParent) child, true));
                }
            }
        }

        return list;
    }

}
