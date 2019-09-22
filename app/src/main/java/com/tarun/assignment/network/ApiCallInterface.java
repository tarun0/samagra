package com.tarun.assignment.network;

import com.tarun.assignment.model.Comment;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiCallInterface {
    @GET("comments")
    Observable<List<Comment>> fetchCommentsList();

    @GET("photos")
    Observable<List<Comment>> fetchPhotosList();

    @GET("todos")
    Observable<List<Comment>> fetchTodoList();

    @GET("posts")
    Observable<List<Comment>> fetchPostsList();
}
