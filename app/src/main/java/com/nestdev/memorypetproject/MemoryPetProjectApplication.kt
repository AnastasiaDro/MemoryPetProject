package com.nestdev.memorypetproject

import android.app.Application
import com.nestdev.memorypetproject.roomDatabase.AppDatabase

class MemoryPetProjectApplication : Application() {
        val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}