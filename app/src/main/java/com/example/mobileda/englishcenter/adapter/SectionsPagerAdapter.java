package com.example.mobileda.englishcenter.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import com.example.mobileda.englishcenter.fragment.RegistorSubjectFragment;
import com.example.mobileda.englishcenter.fragment.UnregisteredSubjectFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> lstFragments = new ArrayList<>();

    public SectionsPagerAdapter(FragmentManager fm) {

        super(fm);
        lstFragments.add(RegistorSubjectFragment.newInstance("Registration"));
        lstFragments.add(UnregisteredSubjectFragment.newInstance("Unregistration"));
    }

    @Override
    public Fragment getItem(int position) {
        // position + 1 vì position bắt đầu từ số 0.
        return lstFragments.get(position);
    }

    @Override
    public int getCount() {
        return lstFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "MY COURSE";
            case 1:
                return "REGISTRATION";
        }
        return null;
    }
}