package com.nestdev.memorypetproject

import android.app.Application
import com.nestdev.memorypetproject.data.roomDatabase.AppDatabase

class MemoryPetProjectApplication : Application() {
        val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}