package com.example.baitapcanhan;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import Fragment.ItemFragment;
public class TabProductAdapter extends FragmentPagerAdapter {

    public TabProductAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return ItemFragment.newInstance(0);
            case 1:
                return ItemFragment.newInstance(1);
            case 2:
                return ItemFragment.newInstance(2);
            default:
                return ItemFragment.newInstance(0);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Recently";
            case 1:
                return "Today";
            case 2:
                return "Upcoming";
            default:
                return "Later";
        }
    }
}