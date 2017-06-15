package com.xiaoqianghe.wqplay.di.module;

import android.app.ProgressDialog;

import com.xiaoqianghe.wqplay.data.RecommedModel;
import com.xiaoqianghe.wqplay.http.ApiService;
import com.xiaoqianghe.wqplay.presenter.RecommendPresenter;
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

    private RecommendContract.View view;

    public RecommendModule(RecommendContract.View view) {
        this.view = view;
    }

//    @Provides
//    public RecommendPresenter providePresenter(RecommedModel model,RecommendContract.View view){
//        return new RecommendPresenter(model,(RecommendFragment)view);
//    }

    @Provides
    public RecommendContract.View provideView(){
        return view;
    }

    @Provides
    public RecommedModel provideModel(ApiService mApiService){

            return new RecommedModel(mApiService);
    }
    @Provides
    public ProgressDialog provideProghressDialog(RecommendContract.View view){

        return new ProgressDialog(((RecommendFragment)view).getActivity());
    }
}
