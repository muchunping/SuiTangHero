package cn.hero.suitanghero.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import cn.hero.suitanghero.R;

public class MainActivity extends FragmentActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks{
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private RadioGroup group;
    private Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        typeface = Typeface.createFromAsset(getAssets(), "fonts/fzlb_5.0.ttf");

        NavigationDrawerFragment mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

        group = (RadioGroup) findViewById(R.id.radioGroup);
        ((RadioButton) group.getChildAt(0)).setTypeface(typeface);
        ((RadioButton) group.getChildAt(1)).setTypeface(typeface);
        ((RadioButton) group.getChildAt(2)).setTypeface(typeface);
        ((RadioButton) group.getChildAt(3)).setTypeface(typeface);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            private Fragment lastFragment = null;

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Log.d(LOG_TAG, "当前选中的是第" + i + "个");
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                if (lastFragment != null) ft.detach(lastFragment);
                String tag = radioGroup + ":" + i;
                Log.d(LOG_TAG, "TAG是 " + tag);
                Fragment fragment = fm.findFragmentByTag(tag);
                Log.d(LOG_TAG, "fragment = null ? " + (fragment == null));
                if (fragment == null) {
                    String fname = "";
                    switch (i) {
                        case R.id.radioButton1:
                            fname = FortressFragment.class.getName();
                            break;
                        case R.id.radioButton2:
                            fname = WorldFragment.class.getName();
                            break;
                        case R.id.radioButton3:
                            fname = CapitalFragment.class.getName();
                            break;
                        case R.id.radioButton4:
                            fname = DuplicateFragment.class.getName();
                            break;
                    }
                    if(fname.equals("")){
                        return;
                    }
                    fragment = Fragment.instantiate(radioGroup.getContext(), fname);
                    ft.add(R.id.container, fragment, tag);
                } else {
                    ft.attach(fragment);
                }

                lastFragment = fragment;
                ft.commit();
            }
        });
        ((RadioButton) group.getChildAt(0)).setChecked(true);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
    }

    public Typeface getTypeface(){
        return typeface;
    }
}
