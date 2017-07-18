package com.ushi.radiohandler.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ushi.radiohandler.R;


public class ExampleSimpleFragment extends Fragment {

    public static ExampleSimpleFragment newInstance() {
        return new ExampleSimpleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_example_simple, container, false);
    }
}
