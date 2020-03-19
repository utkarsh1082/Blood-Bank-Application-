package com.valuekart.bloodbank.ui.base

import android.os.Bundle
import android.support.annotation.Nullable
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<V : BaseViewModel> : DaggerAppCompatActivity() {
    abstract val viewModel: V
    val compositeDisposable=CompositeDisposable()
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
    }
}