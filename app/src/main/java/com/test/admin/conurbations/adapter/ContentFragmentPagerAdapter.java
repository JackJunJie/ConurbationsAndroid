package com.test.admin.conurbations.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.test.admin.conurbations.fragments.FragmentFour;
import com.test.admin.conurbations.fragments.Two;
import com.test.admin.conurbations.fragments.WelfareFragment;
import com.test.admin.conurbations.data.entity.Moment;

/**
 * Created by waly6 on 2015/10/8.
 */
public class ContentFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] titles = new String[]{"精选", "竞技", "热榜" ,"录像"};

    private Fragment[] fragments = new Fragment[4];

    public ContentFragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);

        fragments[0] = new WelfareFragment();
        ((WelfareFragment) fragments[0]).setRange(Moment.Range.ONE);
        fragments[1] = new Two();
        ((Two) fragments[1]).setRange(Moment.Range.TWO);
        fragments[2] = new Two();
        ((Two) fragments[2]).setRange(Moment.Range.THREE);
        fragments[3] = new FragmentFour();
        ((FragmentFour) fragments[3]).setRange(Moment.Range.FOUR);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
