package com.test.admin.conurbations.fragments;

import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.test.admin.conurbations.activitys.INewInfoIndexView;
import com.test.admin.conurbations.adapter.BaseListAdapter;
import com.test.admin.conurbations.adapter.NewsInfoIndexAdapter;
import com.test.admin.conurbations.model.entity.News;
import com.test.admin.conurbations.model.entity.NewsList;
import com.test.admin.conurbations.presenter.NewsInfoIndexPresenter;
import com.test.admin.conurbations.widget.ILayoutManager;
import com.test.admin.conurbations.widget.MyStaggeredGridLayoutManager;
import com.test.admin.conurbations.widget.PullRecycler;

import javax.inject.Inject;

/**
 * Created by zhouqiong on 2016/9/23.
 */
public class NewsInfoIndexFragment extends BaseLazyListFragment<News, NewsInfoIndexPresenter> implements INewInfoIndexView {

    protected String mOrdDate;
    protected String range;

    @Inject
    NewsInfoIndexAdapter mNewsInfoIndexAdapter;

    public void setRange(String range) {
        this.range = range;
    }

    @Override
    protected void initData(Bundle bundle) {
        getFragmentComponent().inject(this);
    }

    @Override
    public void setNewListData(NewsList newListData) {
        if (action == PullRecycler.ACTION_PULL_TO_REFRESH) {
            mDataList.clear();
        }
        if (newListData.stories == null || newListData.stories.size() == 0) {
            recycler.enableLoadMore(false);
        } else {
            mOrdDate = newListData.getDate();
            recycler.enableLoadMore(true);
            mDataList.addAll(newListData.stories);
            mNewsInfoIndexAdapter.setList(mDataList);
            mNewsInfoIndexAdapter.setDate(mOrdDate);
            mNewsInfoIndexAdapter.notifyDataSetChanged();
        }
        recycler.onRefreshCompleted();
    }

    @Override
    protected ILayoutManager getLayoutManager() {
        return new MyStaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected void refreshList(int page) {
        if (mPresenter != null) {
            mPresenter.getNewListData(page, mOrdDate, isRefresh);
            isRefresh = true;
        }
    }

    @Override
    protected BaseListAdapter setUpAdapter() {
        return mNewsInfoIndexAdapter;
    }
}
