/*
 * Copyright 2017 lizhaotailang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skyle.myreadingsoftware.homepage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.skyle.myreadingsoftware.R;
import com.skyle.myreadingsoftware.adapter.MainPagerAdapter;

import java.util.Random;

/**
 * Created by Lizhaotailang on 2016/8/23.
 */

public class MainFragment extends Fragment {
    private static final  String TAG="MainFragment";
    private Context context;
    private MainPagerAdapter adapter;

    private TabLayout tabLayout;

    private ZhihuDailyFragment zhihuDailyFragment;
    private GuokrFragment guokrFragment;
    private DoubanMomentFragment doubanMomentFragment;

    private ZhihuDailyPresenter zhihuDailyPresenter;
    private GuokrPresenter guokrPresenter;
    private DoubanMomentPresenter doubanMomentPresenter;

    public MainFragment() {}

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"MainFragment启动？1-1");
        this.context = getActivity();

        if (savedInstanceState != null) {
            FragmentManager manager = getChildFragmentManager();
            zhihuDailyFragment = (ZhihuDailyFragment) manager.getFragment(savedInstanceState, "zhihu");
            guokrFragment = (GuokrFragment) manager.getFragment(savedInstanceState, "guokr");
            doubanMomentFragment = (DoubanMomentFragment) manager.getFragment(savedInstanceState, "douban");
        } else {
            zhihuDailyFragment = ZhihuDailyFragment.newInstance();
            guokrFragment = GuokrFragment.newInstance();
            doubanMomentFragment = DoubanMomentFragment.newInstance();
        }
        Log.e(TAG,"MainFragment启动？2-1");
        zhihuDailyPresenter = new ZhihuDailyPresenter(context, zhihuDailyFragment);
        guokrPresenter = new GuokrPresenter(context, guokrFragment);
        doubanMomentPresenter = new DoubanMomentPresenter(context, doubanMomentFragment);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initViews(view);
        Log.e(TAG,"MainFragment启动？3-6");
        setHasOptionsMenu(true);
        Log.e(TAG,"MainFragment启动？3-7");
        // 当tab layout位置为果壳精选时，隐藏fab
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e(TAG,"MainFragment启动？3-1");
                FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
                if (tab.getPosition() == 1) {
                    fab.hide();
                    Log.e(TAG,"MainFragment启动？3-4");
                } else {
                    fab.show();
                    Log.e(TAG,"MainFragment启动？3-5");
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.e(TAG,"MainFragment启动？3-2");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.e(TAG,"MainFragment启动？3-3");
            }

        });

        return view;
    }


    private void initViews(View view) {
        Log.e(TAG,"MainFragment启动？4-1");
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3);

        adapter = new MainPagerAdapter(
                getChildFragmentManager(),
                context,
                zhihuDailyFragment,
                guokrFragment,
                doubanMomentFragment);
        Log.e(TAG,"MainFragment启动？4-2");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        Log.e(TAG,"MainFragment启动？4-3");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_feel_lucky) {
            Log.e(TAG,"MainFragment启动？5-1");
            feelLucky();
        }
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        FragmentManager manager = getChildFragmentManager();
        Log.e(TAG,"MainFragment启动？6-1");
        manager.putFragment(outState, "zhihu", zhihuDailyFragment);
        manager.putFragment(outState, "guokr", guokrFragment);
        manager.putFragment(outState, "douban", doubanMomentFragment);
    }

    public void feelLucky() {
        Random random = new Random();
        int type = random.nextInt(3);
        switch (type) {
            case 0:
                zhihuDailyPresenter.feelLucky(); Log.e(TAG,"MainFragment启动？7-1");
                break;
            case 1:
                guokrPresenter.feelLucky();
                Log.e(TAG,"MainFragment启动？7-2");
                break;
            default:
                doubanMomentPresenter.feelLucky();
                Log.e(TAG,"MainFragment启动？7-3");
                break;
        }
    }

    public MainPagerAdapter getAdapter() {
        return adapter;
    }
}
