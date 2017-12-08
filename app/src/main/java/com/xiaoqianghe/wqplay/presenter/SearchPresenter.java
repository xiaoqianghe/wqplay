package com.xiaoqianghe.wqplay.presenter;

import com.xiaoqianghe.wqplay.bean.SearchResult;
import com.xiaoqianghe.wqplay.common.rx.RxHttpResponseCompat;
import com.xiaoqianghe.wqplay.common.rx.subscriber.ProgressSubscriber;
import com.xiaoqianghe.wqplay.presenter.contract.SearchContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Author：Wq
 * Date：2017/12/5 11:32
 * Description：//todo
 */

public class SearchPresenter extends BasePresenter<SearchContract.ISearchModel,SearchContract.SearchView> {


    @Inject
    public SearchPresenter(SearchContract.ISearchModel mModel, SearchContract.SearchView mView) {
        super(mModel, mView);
    }





    public void getSuggestions(String keyword){



        mModel.getSuggestion(keyword)
                .compose(RxHttpResponseCompat.<List<String>>compatResult())
                .subscribe(new ProgressSubscriber<List<String>>(mContext,mView) {
                    @Override
                    public void onNext(List<String> suggestions) {

                        mView.showSuggestions(suggestions);
                    }
                });

    }



    public void search(String keyword){



        saveSearchHistory(keyword);

        mModel.search(keyword)
                .compose(RxHttpResponseCompat.<SearchResult>compatResult())
                .subscribe(new ProgressSubscriber<SearchResult>(mContext,mView) {
                    @Override
                    public void onNext(SearchResult searchResult) {
                        mView.showSearchResult(searchResult);
                    }
                });

    }

    private void saveSearchHistory(String keyword) {

        // save to database
    }


    public void showHistory(){

        // get search history from  database


        List<String> list = new ArrayList<>();
        list.add("地图");
        list.add("KK");
        list.add("爱奇艺");
        list.add("播放器");
        list.add("支付宝");
        list.add("微信");
        list.add("QQ");
        list.add("TV");
        list.add("直播");
        list.add("妹子");
        list.add("美女");

        mView.showSearchHistory(list);


    }

}
