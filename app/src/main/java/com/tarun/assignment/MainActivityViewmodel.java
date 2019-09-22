package com.tarun.assignment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.tarun.assignment.model.ApiResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewmodel extends ViewModel {

    private Repository repository;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<ApiResponse> responseLiveData1 = new MutableLiveData<>();
    private final MutableLiveData<ApiResponse> responseLiveData2 = new MutableLiveData<>();
    private final MutableLiveData<ApiResponse> responseLiveData3 = new MutableLiveData<>();
    private final MutableLiveData<ApiResponse> responseLiveData4 = new MutableLiveData<>();


    public MainActivityViewmodel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ApiResponse> loginResponse1() {
        return responseLiveData1;
    }

    public MutableLiveData<ApiResponse> loginResponse2() {
        return responseLiveData2;
    }

    public MutableLiveData<ApiResponse> loginResponse3() {
        return responseLiveData3;
    }

    public MutableLiveData<ApiResponse> loginResponse4() {
        return responseLiveData4;
    }

    public void hitAllApi() {

        /*disposables.add(repository.fetchCommentsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> responseLiveData1.setValue(ApiResponse.loading()))
                .subscribe(
                        result -> responseLiveData1.setValue(ApiResponse.success(result)),
                        throwable -> responseLiveData1.setValue(ApiResponse.error(throwable))
                ));

        disposables.add(repository.fetchPostsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> responseLiveData4.setValue(ApiResponse.loading()))
                .subscribe(
                        result -> responseLiveData4.setValue(ApiResponse.success(result)),
                        throwable -> responseLiveData4.setValue(ApiResponse.error(throwable))
                ));*/

        disposables.add(Observable.merge(
                repository.fetchCommentsList().subscribeOn(Schedulers.io()),
                repository.fetchPostsList().subscribeOn(Schedulers.io()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                result -> responseLiveData1.setValue(ApiResponse.success(result)),
                throwable -> responseLiveData1.setValue(ApiResponse.error(throwable))
        ));
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
