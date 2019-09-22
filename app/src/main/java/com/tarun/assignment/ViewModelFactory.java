package com.tarun.assignment;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;

public class ViewModelFactory implements android.arch.lifecycle.ViewModelProvider.Factory {
    private Repository repository;

    @Inject
    public ViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainActivityViewmodel.class)) {
            return (T) new MainActivityViewmodel(repository);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
