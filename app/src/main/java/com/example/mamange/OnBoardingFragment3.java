package com.example.mamange;

import android.os.Bundle;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;
import androidx.fragment.app.Fragment;

public class OnBoardingFragment3 extends Fragment{

    @Override
    public View onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.fragment_on_boarding3, container, attachToRoot: false);

        return root;
    }
}
