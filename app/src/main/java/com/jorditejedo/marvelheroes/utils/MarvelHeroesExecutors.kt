package com.jorditejedo.marvelheroes.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

open class MarvelHeroesExecutors {
    private val mDiskIO: ExecutorService = Executors.newSingleThreadExecutor()
    private val mNetworkIO: ExecutorService = Executors.newFixedThreadPool(2)
    private val mMainThread: Executor

    init {
        this.mMainThread = MainThreadExecutor()
    }

    open fun diskIO(): ExecutorService {
        return mDiskIO
    }

    open fun networkIO(): ExecutorService {
        return mNetworkIO
    }

    open fun mainThread(): Executor {
        return mMainThread
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}