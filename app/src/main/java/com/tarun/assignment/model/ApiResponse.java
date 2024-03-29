package com.tarun.assignment.model;

import com.tarun.assignment.Status;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public class ApiResponse {
    public final Status status;

    @Nullable
    public final List<Comment> data;

    @Nullable
    public final Throwable error;

    private ApiResponse(Status status, @Nullable List<Comment> data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static ApiResponse loading() {
        return new ApiResponse(Status.LOADING, null, null);
    }

    public static ApiResponse success(@NonNull List<Comment> data) {
        return new ApiResponse(Status.SUCCESS, data, null);
    }

    public static ApiResponse error(@NonNull Throwable error) {
        return new ApiResponse(Status.ERROR, null, error);
    }
}
