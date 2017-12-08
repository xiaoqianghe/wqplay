package com.xiaoqianghe.wqplay.presenter.contract;

import com.xiaoqianghe.wqplay.bean.SearchResult;
import com.xiaoqianghe.wqplay.bean.requestbean.BaseBean;
import com.xiaoqianghe.wqplay.ui.BaseView;

import java.util.List;

import io.reactivex.Observable;


/**
 * Author：Wq
 * Date：2017/12/5 11:23
 * Description：//todo
 */

public class SearchContract {

    public interface  SearchView extends BaseView{

        void showSearchHistory(List<String> list);

        void showSuggestions(List<String> list);

        void showSearchResult(SearchResult result);

    }

    public interface ISearchModel{
      Observable<BaseBean<List<String>>> getSuggestion(String keyword);
        Observable<BaseBean<SearchResult>>  search(String keyword);


    }
}
