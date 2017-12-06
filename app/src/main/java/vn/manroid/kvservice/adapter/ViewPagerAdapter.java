package vn.manroid.kvservice.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import vn.manroid.kvservice.R;
import vn.manroid.kvservice.view.fragment.ChartFragment;
import vn.manroid.kvservice.view.fragment.DetailsFragment;
import vn.manroid.kvservice.view.fragment.NotiFragment;

/**
 * Created by manro on 04/12/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final SparseArray<WeakReference<Fragment>> mFragmentArray = new SparseArray<>();
    private List<Holder> mListHolder = new ArrayList<>();
    @NonNull
    final Context mContext;
    @NonNull
    final String[] title;

    public ViewPagerAdapter(@NonNull Context mContext, FragmentManager fm) {
        super(fm);
        this.mContext = mContext;
        title = new String[]{
               null,null,null
        };
        final FRAGMENT_LIST[] fragments = FRAGMENT_LIST.values();
        for (FRAGMENT_LIST fragment : fragments) {
            add(fragment.getFragmentClass(),null);
        }
    }

    private void add(Class<? extends Fragment> mClassName, Bundle bundle) {
        Holder mHolder = new Holder();
        mHolder.className = mClassName.getName();
        mHolder.params = bundle;
        final int position = mListHolder.size();
        mListHolder.add(position, mHolder);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        final Holder mHolder = mListHolder.get(position);
        return Fragment.instantiate(mContext,mHolder.className,mHolder.params);
    }

    @Override
    public int getCount() {
        return mListHolder.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container,position);
        WeakReference<Fragment> weakFragment = mFragmentArray.get(position);
        if (weakFragment != null) {
            weakFragment.clear();
        }
        mFragmentArray.put(position,new WeakReference<Fragment>(fragment));
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        WeakReference<Fragment> mWeakFragment = mFragmentArray.get(position);
        if (mWeakFragment != null) {
            mWeakFragment.clear();
        }
    }


    public enum FRAGMENT_LIST {
        Chart(ChartFragment.class),
        Details(DetailsFragment.class),
        Noti(NotiFragment.class);

        private final Class<? extends Fragment> mFragmentClass;

        FRAGMENT_LIST(Class<? extends Fragment> mFragmentClass) {
            this.mFragmentClass = mFragmentClass;
        }

        public Class<? extends Fragment> getFragmentClass() {
            return mFragmentClass;
        }
    }

    private static class Holder {
        String className;
        Bundle params;
    }
}


