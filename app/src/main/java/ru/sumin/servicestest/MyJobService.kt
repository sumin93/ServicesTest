package ru.sumin.servicestest

import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class MyJobService: JobService() {

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
        scope.cancel()
    }

    override fun onStartJob(p0: JobParameters?): Boolean {
        scope.launch {
            log("onStartJob")
            for (i in 0..100) {
                delay(1000)
                log("Timer: $i")
            }
            jobFinished(p0,false)
        }
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        log("onStopJob")
        return false
    }

    private fun log(msg: String) {
        Log.d("SERVICE_TAG", "MyJobService: $msg")
    }

    companion object{
        const val JOB_ID = 33
    }
}