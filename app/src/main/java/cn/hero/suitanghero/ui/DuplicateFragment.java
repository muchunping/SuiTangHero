package cn.hero.suitanghero.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.hero.suitanghero.R;

/**
 * Duplicate
 * Created by hp on 2014/12/12.
 */
public class DuplicateFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_duplicate, container, false);
        ((TextView) rootView.findViewById(R.id.titleView)).setTypeface(MainActivity.typeface);
        return rootView;
    }
}
