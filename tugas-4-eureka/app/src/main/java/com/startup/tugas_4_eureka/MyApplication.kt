package com.startup.tugas_4_eureka

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//inisialisasi hilt, sebagai basis dari project untuk memanfaatkan hilt
@HiltAndroidApp
open class  MyApplication : Application() {
}