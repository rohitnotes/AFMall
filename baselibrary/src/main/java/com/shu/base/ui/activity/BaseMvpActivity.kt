package com.shu.base.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import com.shu.base.common.BaseApplication
import com.shu.base.injection.component.ActivityComponent
import com.shu.base.injection.component.DaggerActivityComponent
import com.shu.base.injection.module.ActivityModule
import com.shu.base.presenter.BasePresenter
import com.shu.base.presenter.view.BaseView
import javax.inject.Inject

/**
 * Created by wangshufu on 2018/3/20.
 */
abstract open class BaseMvpActivity<T:BasePresenter<*>> : BaseActivity(),BaseView {
    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    @Inject
    lateinit var mPresenter:T

    lateinit var activityComponent:ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityComponent()
        injectComponent()
    }

    abstract fun injectComponent()

    private fun initActivityComponent() {

        activityComponent = DaggerActivityComponent.builder().appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .build()

    }
}