package com.my.zhomprass_java.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SubTabPaggerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList =new ArrayList();

    public SubTabPaggerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void  addFragment(Fragment fragment){
        fragmentList.add(fragment);
    }

}
