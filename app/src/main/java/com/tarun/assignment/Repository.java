package com.tarun.assignment;

import com.google.gson.JsonElement;
import com.tarun.assignment.model.Comment;
import com.tarun.assignment.network.ApiCallInterface;

import java.util.List;

import io.reactivex.Observable;

public class Repository {

    private ApiCallInterface apiCallInterface;

    public Repository(ApiCallInterface apiCallInterface) {
        this.apiCallInterface = apiCallInterface;
    }

    public Observable<List<Comment>> fetchCommentsList() {
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
