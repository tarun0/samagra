package com.tarun.assignment.di;

import com.tarun.assignment.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {

    void doInjection(MainActivity mainActivity);

}
