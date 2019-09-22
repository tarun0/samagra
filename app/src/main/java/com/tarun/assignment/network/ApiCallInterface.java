package com.tarun.assignment.network;

import com.google.gson.JsonElement;
import com.tarun.assignment.model.ApiResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiCallInterface {
    @GET("comments")
    Observable<JsonElement> fetchCommentsList();
}
