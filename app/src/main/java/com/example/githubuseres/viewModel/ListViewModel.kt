package com.example.githubuseres.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuseres.model.UsersData
import com.example.githubuseres.model.UsersService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel: ViewModel()  {

    private val usersService = UsersService()
    private val disposable = CompositeDisposable()

    val users = MutableLiveData<List<UsersData>>()
    val usersLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        fetchUsers()
    }

    private fun fetchUsers(){
        loading.value = true
        disposable.add(
            usersService.getUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<UsersData>>(){
                    override fun onSuccess(value: List<UsersData>?) {
                        users.value = value
                        usersLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        usersLoadError.value = true
                        loading.value = false
                    }
                }))

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}