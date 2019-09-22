package com.tarun.assignment.di;


import com.tarun.assignment.MainActivity;
import com.tarun.assignment.di.AppModule;
import com.tarun.assignment.di.UtilsModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {

    void doInjection(MainActivity mainActivity);

}
