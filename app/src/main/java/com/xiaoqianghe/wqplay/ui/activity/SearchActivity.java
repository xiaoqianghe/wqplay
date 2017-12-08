package com.xiaoqianghe.wqplay.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.xiaoqianghe.wqplay.R;
import com.xiaoqianghe.wqplay.bean.SearchResult;
import com.xiaoqianghe.wqplay.common.font.WqplayFont;
import com.xiaoqianghe.wqplay.common.rx.RxSchedulers;
import com.xiaoqianghe.wqplay.di.component.AppComponent;

import com.xiaoqianghe.wqplay.di.component.DaggerSearchComponent;
import com.xiaoqianghe.wqplay.di.module.SearchModule;
import com.xiaoqianghe.wqplay.presenter.SearchPresenter;
import com.xiaoqianghe.wqplay.presenter.contract.SearchContract;
import com.xiaoqianghe.wqplay.ui.adapter.AppInfoAdapter;
import com.xiaoqianghe.wqplay.ui.adapter.SearchHistoryAdapter;
import com.xiaoqianghe.wqplay.ui.adapter.SuggestionAdapter;
import com.xiaoqianghe.wqplay.ui.decoration.DividerItemDecoration;
import com.xiaoqianghe.wqplay.ui.widget.SpaceItemDecoration2;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import zlc.season.rxdownload2.RxDownload;



/**
 * Author：Wq
 * Date：2017/12/5 11:21
 * Description：//todo
 */

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.SearchView {
    @BindView(R.id.searchTextView)
    EditText searchTextView;
    @BindView(R.id.action_clear_btn)
    ImageView actionClearBtn;
    @BindView(R.id.search_bar)
    RelativeLayout searchBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_clear)
    ImageView btnClear;
    @BindView(R.id.recycler_view_history)
    RecyclerView recyclerViewHistory;
    @BindView(R.id.layout_history)
    LinearLayout layoutHistory;
    @BindView(R.id.recycler_view_suggestion)
    RecyclerView recyclerViewSuggestion;
    @BindView(R.id.recycler_view_result)
    RecyclerView recyclerViewResult;
    @BindView(R.id.activity_search)
    LinearLayout activitySearch;


    private SuggestionAdapter mSuggestionAdapter;
    private AppInfoAdapter mAppInfoAdpater;

    private SearchHistoryAdapter mHistoryAdatper;

//
//
//    RxDownload rxDownload1 = RxDownload.getInstance(this);
//
//
//
//
    @Inject
    RxDownload rxDownload;

    private Disposable disposable;



    public static final int STATUS_REQUESTING=0;
    public static final int STATUS_FINISH=1;
    public int   status=STATUS_FINISH;



    @Override
    protected void init() {
        
        
        mPresenter.showHistory();
        
        initView();

        setupSearchView();

        setupSuggestionRecyclerView();

        initSearchResultRecycyleView();

    }

    private void initView() {
        toolbar.setNavigationIcon(
                new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_arrow_back)
                .sizeDp(16)
                .color(getResources().getColor(R.color.md_white_1000))

        );


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        actionClearBtn.setImageDrawable(new IconicsDrawable(this,Ionicons.Icon.ion_ios_close_empty)
                .color(ContextCompat.getColor(this,R.color.white)).sizeDp(16));

        RxView.clicks(btnClear).subscribe(new Consumer<Object>() {

            @Override
            public void accept(@NonNull Object o) throws Exception {

            }
        });


        disposable = RxTextView.textChanges(searchTextView)
                .debounce(400, TimeUnit.MILLISECONDS)
                .compose(RxSchedulers.<CharSequence>io_main())
                .filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(@NonNull CharSequence charSequence) throws Exception {
                        return charSequence.toString().trim().length()>0;
                    }
                })

                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(@NonNull CharSequence charSequence) throws Exception {

                        Log.d("SearchActivity",charSequence.toString()+"，status="+status);

                        if(charSequence.length()>0){
                            actionClearBtn.setVisibility(View.VISIBLE);
                        }
                        else{
                            actionClearBtn.setVisibility(View.GONE);
                        }

                        mPresenter.getSuggestions(charSequence.toString().trim());

                    }
                });






    }




    private void setupSuggestionRecyclerView(){

        mSuggestionAdapter=new SuggestionAdapter();


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewSuggestion.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);

        recyclerViewSuggestion.addItemDecoration(itemDecoration);

        recyclerViewSuggestion.setAdapter(mSuggestionAdapter);

        recyclerViewSuggestion.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                search(mSuggestionAdapter.getItem(position));
            }
        });


    }

    private  void initSearchResultRecycyleView(){
//        mAppInfoAdpater = AppInfoAdapter.builder().showBrief(false).showCategoryName(true).rxDownload(rxDownload).build();
//        mAppInfoAdpater = AppInfoAdapter.builder().showBrief(false).showCategoryName(true).rxDownload(rxDownload).build();

        mAppInfoAdpater=AppInfoAdapter.builder().showBrief(false).showCategoryName(true).rxDownload(rxDownload).build();

        mAppInfoAdpater = AppInfoAdapter.builder().showBrief(false).showCategoryName(true).rxDownload(rxDownload).build();

        LinearLayoutManager layoutManager =  new LinearLayoutManager(this);
        recyclerViewResult.setLayoutManager(layoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST);
        recyclerViewResult.addItemDecoration(itemDecoration);

        recyclerViewResult.setAdapter(mAppInfoAdpater);

        recyclerViewResult.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });


    }

    private void initHistoryRecycleView(List<String> list){

        mHistoryAdatper=new SearchHistoryAdapter();
        mHistoryAdatper.addData(list);

        RecyclerView.LayoutManager lm=new GridLayoutManager(this,5);
        SpaceItemDecoration2 itemd=new SpaceItemDecoration2(10);

        recyclerViewHistory.addItemDecoration(itemd);

        recyclerViewHistory.setLayoutManager(lm);
        recyclerViewHistory.setAdapter(mHistoryAdatper);

        recyclerViewHistory.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                search(mHistoryAdatper.getItem(position));
            }
        });
    }



    private void setupSearchView(){

        RxView.clicks(actionClearBtn).subscribe(new Consumer<Object>() {


            @Override
            public void accept(@NonNull Object o) throws Exception {

                searchTextView.setText("");

                layoutHistory.setVisibility(View.VISIBLE);
                recyclerViewSuggestion.setVisibility(View.GONE);

                recyclerViewResult.setVisibility(View.GONE);

            }
        });



        RxTextView.editorActions(searchTextView).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {

                search(searchTextView.getText().toString().trim());
            }
        });


        disposable = RxTextView.textChanges(searchTextView)
                .debounce(400, TimeUnit.MILLISECONDS)
                .compose(RxSchedulers.<CharSequence>io_main())
                .filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(@NonNull CharSequence charSequence) throws Exception {
                        return charSequence.toString().trim().length()>0;
                    }
                })

                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(@NonNull CharSequence charSequence) throws Exception {

                        Log.d("SearchActivity",charSequence.toString()+"，status="+status);

                        if(charSequence.length()>0){
                            actionClearBtn.setVisibility(View.VISIBLE);
                        }
                        else{
                            actionClearBtn.setVisibility(View.GONE);
                        }

                        mPresenter.getSuggestions(charSequence.toString().trim());

                    }
                });


    }


    private void search(String keyword){


        mPresenter.search(keyword);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

//        DaggerSearchComponent.builder().appComponent(appComponent)
//                .searchModule(new SearchModule(this))
//                .build()
//                .inject(this);

//
//        DaggerSearchComponent.builder().appComponent(appComponent)
//                .searchModule(new SearchModule(this))
//                .build()
//        .inject(this);


        DaggerSearchComponent.builder().appComponent(appComponent)
                .searchModule(new SearchModule(this))
                .build()
                .inject(this);

















    }

    @Override
    public void showSearchHistory(List<String> list) {

        initHistoryRecycleView(list);

        recyclerViewSuggestion.setVisibility(View.GONE);
        layoutHistory.setVisibility(View.VISIBLE);

        recyclerViewResult.setVisibility(View.GONE);

    }

    @Override
    public void showSuggestions(List<String> list) {


        mSuggestionAdapter.setNewData(list);
        recyclerViewSuggestion.setVisibility(View.VISIBLE);
        layoutHistory.setVisibility(View.GONE);

        recyclerViewResult.setVisibility(View.GONE);

    }

    @Override
    public void showSearchResult(SearchResult result) {

        mAppInfoAdpater.setNewData(result.getListApp());
        recyclerViewSuggestion.setVisibility(View.GONE);

        layoutHistory.setVisibility(View.GONE);

        recyclerViewResult.setVisibility(View.VISIBLE);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showError(String str) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
