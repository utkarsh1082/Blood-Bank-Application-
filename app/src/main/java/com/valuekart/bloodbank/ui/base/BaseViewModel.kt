package com.valuekart.bloodbank.ui.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel() : ViewModel(){
    val compositeDisposable=CompositeDisposable()
}