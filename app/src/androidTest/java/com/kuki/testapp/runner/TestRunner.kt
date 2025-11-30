package com.kuki.testapp.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.kuki.testapp.TestApplication

class TestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application? {
        return super.newApplication(cl, TestApplication::class.java.name, context)
    }
}