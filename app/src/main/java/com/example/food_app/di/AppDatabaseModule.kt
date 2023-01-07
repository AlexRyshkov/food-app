package com.example.food_app.di

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.food_app.R
import com.example.food_app.data.restaurant.Restaurant
import com.example.food_app.data.AppDatabase
import com.example.food_app.data.food.FoodDao
import com.example.food_app.data.food.RestaurantDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


val restaurantsList = listOf(
    Restaurant(1, "Food grill", "30-40 m", R.drawable.restaurant, 4.3),
    Restaurant(2, "Food grill", "30-40 m", R.drawable.restaurant, 4.3),
    Restaurant(3, "Food grill", "30-40 m", R.drawable.restaurant, 4.3),
    Restaurant(4, "Food grill", "30-40 m", R.drawable.restaurant, 4.3),
    Restaurant(5, "Food grill", "30-40 m", R.drawable.restaurant, 4.3),
    Restaurant(6, "Food grill", "30-40 m", R.drawable.restaurant, 4.3),
    Restaurant(7, "Food grill", "30-40 m", R.drawable.restaurant, 4.3),
    Restaurant(8, "Food grill", "30-40 m", R.drawable.restaurant, 4.3),
    Restaurant(9, "Food grill", "30-40 m", R.drawable.restaurant, 4.3),
    Restaurant(10, "Food grill", "30-40 m", R.drawable.restaurant, 4.3),
)

@InstallIn(SingletonComponent::class)
@Module
class AppDatabseModule {
    @Provides
    fun provideFoodDao(database: AppDatabase): FoodDao {
        return database.foodDao()
    }

    @Provides
    fun provideRestaurantDao(database: AppDatabase): RestaurantDao {
        return database.restaurantDao()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        val rdc: RoomDatabase.Callback = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                try {
                    db.beginTransaction()
                    for (item in restaurantsList) {
                        val values = ContentValues()
                        values.apply {
                            put("id", item.id)
                            put("name", item.name)
                            put("deliveryTime", item.deliveryTime)
                            put("image", item.image)
                            put("rating", item.rating)
                        }
                        db.insert("Restaurant", SQLiteDatabase.CONFLICT_REPLACE, values)
                    }
                    db.setTransactionSuccessful()
                } catch (exception: Exception) {
                    Log.e("DATABASE", exception.message!!)
                } finally {
                    db.endTransaction()
                }
            }
        }

        val appDatabase = Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, "food_app.db"
        ).allowMainThreadQueries().addCallback(rdc).build()



        return appDatabase
    }
}