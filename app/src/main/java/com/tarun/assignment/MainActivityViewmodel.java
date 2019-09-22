package com.tarun.assignment;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.JsonElement;
import com.tarun.assignment.db.DbComment;
import com.tarun.assignment.db.MyDatabase;
import com.tarun.assignment.model.ApiResponse;
import com.tarun.assignment.model.Comment;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewmodel extends ViewModel {

    private Repository repository;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final CompositeDisposable dbDisposable = new CompositeDisposable();
    private final MutableLiveData<ApiResponse> responseLiveDataComments = new MutableLiveData<>();
    private final MutableLiveData<ApiResponse> responseLiveDataPhotos = new MutableLiveData<>();
    private final MutableLiveData<ApiResponse> responseLiveDataTodo = new MutableLiveData<>();
    private final MutableLiveData<ApiResponse> responseLiveDataPosts = new MutableLiveData<>();
    private final MutableLiveData<Boolean> dbLiveDataCommentFinished = new MutableLiveData<>();
    private final MutableLiveData<Boolean> dbLiveDataTodoFinished = new MutableLiveData<>();
    private final MutableLiveData<Boolean> dbLiveDataPhotosFinished = new MutableLiveData<>();
    private final MutableLiveData<Boolean> dbLiveDataPostsFinished = new MutableLiveData<>();

    private MyDatabase db;

    public MainActivityViewmodel(Repository repository) {
        db = MyDatabase.getInstance(App.getContext());
        this.repository = repository;
    }

    public MutableLiveData<ApiResponse> loginResponseComments() {
        return responseLiveDataComments;
    }

    public MutableLiveData<Boolean> getDbLiveDataTodoFinished() {
        return dbLiveDataTodoFinished;
    }

    public MutableLiveData<Boolean> getDbLiveDataPhotosFinished() {
        return dbLiveDataPhotosFinished;
    }

    public MutableLiveData<Boolean> getDbLiveDataPostsFinished() {
        return dbLiveDataPostsFinished;
    }

    public MutableLiveData<Boolean> getDbLiveDataCommentFinished() {
        return dbLiveDataCommentFinished;
    }

    public MutableLiveData<ApiResponse> loginResponsePhotos() {
        return responseLiveDataPhotos;
    }

    public MutableLiveData<ApiResponse> loginResponseTodo() {
        return responseLiveDataTodo;
    }

    public MutableLiveData<ApiResponse> loginResponsePosts() {
        return responseLiveDataPosts;
    }

    public void hitAllApi() {

        disposables.add(repository.fetchCommentsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> responseLiveDataComments.setValue(ApiResponse.loading()))
                .subscribe(
                        result -> {
                            responseLiveDataComments.setValue(ApiResponse.success(result));

                        },
                        throwable -> responseLiveDataComments.setValue(ApiResponse.error(throwable))
                ));

        disposables.add(repository.fetchPhotosList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> responseLiveDataPhotos.setValue(ApiResponse.loading()))
                .subscribe(
                        result -> responseLiveDataPhotos.setValue(ApiResponse.success(result)),
                        throwable -> responseLiveDataPhotos.setValue(ApiResponse.error(throwable))
                ));

        disposables.add(repository.fetchTodoList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> responseLiveDataTodo.setValue(ApiResponse.loading()))
                .subscribe(
                        result -> responseLiveDataTodo.setValue(ApiResponse.success(result)),
                        throwable -> responseLiveDataTodo.setValue(ApiResponse.error(throwable))
                ));

        disposables.add(repository.fetchPostsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> responseLiveDataPosts.setValue(ApiResponse.loading()))
                .subscribe(
                        result -> responseLiveDataPosts.setValue(ApiResponse.success(result)),
                        throwable -> responseLiveDataPosts.setValue(ApiResponse.error(throwable))
                ));
    }

    void addComment(List<Comment> comments) {
        dbDisposable.add(insertComments(comments)
        .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Action() {
                            @Override
                            public void run() throws Exception {
                                dbLiveDataCommentFinished.postValue(true);

                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                dbLiveDataCommentFinished.postValue(false);
                            }
                        }
                ));

    }

    void addPhoto(List<Comment> comments) {
        dbDisposable.add(insertPhotos(comments)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Action() {
                            @Override
                            public void run() throws Exception {
                                dbLiveDataPhotosFinished.postValue(true);

                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                dbLiveDataPhotosFinished.postValue(false);
                            }
                        }
                ));

    }

    void addPost(List<Comment> comments) {
        dbDisposable.add(insertPosts(comments)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Action() {
                            @Override
                            public void run() throws Exception {
                                dbLiveDataPostsFinished.postValue(true);

                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                dbLiveDataPostsFinished.postValue(false);
                            }
                        }
                ));

    }

    void addTodo(List<Comment> comments) {
        dbDisposable.add(insertTodos(comments)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Action() {
                            @Override
                            public void run() throws Exception {
                                dbLiveDataTodoFinished.postValue(true);

                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                dbLiveDataTodoFinished.postValue(false);
                            }
                        }
                ));

    }

    private Completable insertComments(List<Comment> comments) {
        for (Comment comment: comments) {
            db.commentModel().addComment(new DbComment(comment));
        }
        return Completable.complete();
    }

    private Completable insertPosts(List<Comment> comments) {
        for (Comment comment: comments) {
            db.postModel().addPost(new DbComment(comment));
        }
        return Completable.complete();
    }

    private Completable insertPhotos(List<Comment> comments) {
        for (Comment comment: comments) {
            db.photosModel().addPhoto(new DbComment(comment));
        }
        return Completable.complete();
    }

    private Completable insertTodos(List<Comment> comments) {
        for (Comment comment: comments) {
            db.todoModel().addTodo(new DbComment(comment));
        }
        return Completable.complete();
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
