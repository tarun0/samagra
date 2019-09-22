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

    public Observable<List<Comment>> fetchPhotosList() {
        return apiCallInterface.fetchPhotosList();
    }

    public Observable<List<Comment>> fetchTodoList() {
        return apiCallInterface.fetchTodoList();
    }

    public Observable<List<Comment>> fetchPostsList() {
        return apiCallInterface.fetchPostsList();
    }
}
