package com.ushi.radiohandler.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ushi.radiohandler.R;
import com.ushi.radiohandler.adapter.MenuAdapter;
import com.ushi.radiohandler.data.Menu;


public class MenuFragment extends Fragment {

    public interface MenuItemSelectedListener {

        void onMenuSelected(MenuFragment fragment, Menu menu);
    }

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    private MenuItemSelectedListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Object o = getParentFragment();
        if (o == null) {
            o = getActivity();
        }

        if (o instanceof MenuItemSelectedListener) {
            mListener = (MenuItemSelectedListener) o;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        MenuAdapter adapter = new MenuAdapter();
        adapter.setOnItemSelectedListener((adapter1, menu, position) -> {
            if (mListener != null) {
                mListener.onMenuSelected(this, menu);
            }
        });

        recyclerView.setAdapter(adapter);

        return view;
    }
}
