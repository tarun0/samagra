package com.tarun.assignment;

import com.google.gson.JsonElement;
import com.tarun.assignment.network.ApiCallInterface;

import io.reactivex.Observable;

public class Repository {

    private ApiCallInterface apiCallInterface;

    public Repository(ApiCallInterface apiCallInterface) {
        this.apiCallInterface = apiCallInterface;
    }

    public Observable<JsonElement> fetchCommentsList() {
        return apiCallInterface.fetchCommentsList();
    }

    public Observable<JsonElement> fetchPhotosList() {
        return apiCallInterface.fetchPhotosList();
    }

    public Observable<JsonElement> fetchTodoList() {
        return apiCallInterface.fetchTodoList();
    }

    public Observable<JsonElement> fetchPostsList() {
        return apiCallInterface.fetchPostsList();
    }
}
