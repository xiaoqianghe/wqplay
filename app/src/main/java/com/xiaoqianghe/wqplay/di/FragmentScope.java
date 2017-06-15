package com.xiaoqianghe.wqplay.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Author：Wq
 * Date：2017/6/15 13:56
 * Description：//todo
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface FragmentScope {
}
