package com.test.channel9.data.source.remote.services

import android.content.Context
import androidx.work.*
import com.test.channel9.data.source.remote.workers.LatestNewsWorker
import java.time.Duration
import java.util.concurrent.TimeUnit
import javax.inject.Inject

const val LOAD_DATA_WORK_NAME = "load_data_work_name"

interface WorkerService {
    fun setup(context: Context)
}

class WorkerServiceImpl @Inject constructor(): WorkerService {
    override fun setup(context: Context) {
        val latestNewsWorkerRequest = PeriodicWorkRequestBuilder<LatestNewsWorker>(1, TimeUnit.HOURS,15, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            LOAD_DATA_WORK_NAME,
            ExistingPeriodicWorkPolicy.UPDATE,
            latestNewsWorkerRequest
        )
    }
}