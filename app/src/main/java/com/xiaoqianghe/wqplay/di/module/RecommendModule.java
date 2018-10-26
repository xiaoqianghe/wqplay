package com.xiaoqianghe.wqplay.di.module;

import android.app.ProgressDialog;

import com.xiaoqianghe.wqplay.data.AppInfoModel;
import com.xiaoqianghe.wqplay.data.RecommedModel;
import com.xiaoqianghe.wqplay.http.ApiService;
import com.xiaoqianghe.wqplay.presenter.RecommendPresenter;
import com.xiaoqianghe.wqplay.presenter.contract.AppInfoContract;
import com.xiaoqianghe.wqplay.presenter.contract.RecommendContract;
import com.xiaoqianghe.wqplay.ui.Fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Author：Wq
 * Date：2017/6/15 10:25
 * Description：//todo
 */
@Module
public class RecommendModule {

    private AppInfoContract.View view;

    public RecommendModule(AppInfoContract.View view) {
        this.view = view;
    }

//    @Provides
//    public RecommendPresenter providePresenter(RecommedModel model,RecommendContract.View view){
//        return new RecommendPresenter(model,(RecommendFragment)view);
//    }

    @Provides
    public AppInfoContract.View provideView(){
        return view;
    }

    @Provides
    public RecommedModel provideModel(ApiService mApiService){

            return new RecommedModel(mApiService);
    }
    @Provides
    public ProgressDialog provideProghressDialog(AppInfoContract.View view){

        return new ProgressDialog(((RecommendFragment)view).getActivity());
    }


    @Provides
    public AppInfoModel privodeModel(ApiService apiService){

        return  new AppInfoModel(apiService);
    }


}
