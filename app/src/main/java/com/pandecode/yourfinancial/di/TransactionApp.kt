package com.pandecode.yourfinancial.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/*
    This is the Application class to start the injection(koin)
 */
class TransactionApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TransactionApp)
            modules(
                listOf(
                    databaseModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}