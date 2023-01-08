package com.example.food_app.di

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.food_app.R
import com.example.food_app.data.AppDatabase
import com.example.food_app.data.food.Food
import com.example.food_app.data.food.FoodDao
import com.example.food_app.data.food.RestaurantDao
import com.example.food_app.data.restaurant.Restaurant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.random.Random


val restaurantsList = listOf(
    Restaurant(0, "Food grill", "30-40 m", R.drawable.restaurant, 4.3),
    Restaurant(1, "Food grill", "30-40 m", R.drawable.restaurant, 4.3),
    Restaurant(2, "Food grill", "30-40 m", R.drawable.restaurant, 4.3),
    Restaurant(3, "Food grill", "30-40 m", R.drawable.restaurant, 4.3),
    Restaurant(4, "Food grill", "30-40 m", R.drawable.restaurant, 4.3),
    Restaurant(5, "Food grill", "30-40 m", R.drawable.restaurant, 4.3),
    Restaurant(6, "Food grill", "30-40 m", R.drawable.restaurant, 4.3),
    Restaurant(7, "Food grill", "30-40 m", R.drawable.restaurant, 4.3),
    Restaurant(8, "Food grill", "30-40 m", R.drawable.restaurant, 4.3),
    Restaurant(9, "Food grill", "30-40 m", R.drawable.restaurant, 4.3),
)

val foodList = listOf(
    Food(
        0,
        "Asian rice",
        "No 10 opp lekki phase 1 bridge in sangotedo estate",
        4.5,
        5.0,
        R.drawable.rice
    ),
    Food(
        1,
        "Caesar",
        "No 10 opp lekki phase 1 bridge in sangotedo estate",
        4.5,
        5.0,
        R.drawable.caesar_salad
    ),
    Food(
        2,
        "Burger",
        "No 10 opp lekki phase 1 bridge in sangotedo estate",
        4.5,
        5.0,
        R.drawable.burger
    ),
    Food(
        3,
        "Fried potato",
        "No 10 opp lekki phase 1 bridge in sangotedo estate",
        4.5,
        5.0,
        R.drawable.fried_potato
    ),
    Food(
        4,
        "Kebab",
        "No 10 opp lekki phase 1 bridge in sangotedo estate",
        4.5,
        5.0,
        R.drawable.kebab
    ),
    Food(
        5,
        "Octopus meat",
        "No 10 opp lekki phase 1 bridge in sangotedo estate",
        4.5,
        5.0,
        R.drawable.octopus
    ),
    Food(
        6,
        "Pizza",
        "No 10 opp lekki phase 1 bridge in sangotedo estate",
        4.5,
        5.0,
        R.drawable.pizza
    ),
    Food(
        7,
        "Ramen",
        "No 10 opp lekki phase 1 bridge in sangotedo estate",
        4.5,
        5.0,
        R.drawable.ramen
    ),
    Food(
        8,
        "Pork steak",
        "No 10 opp lekki phase 1 bridge in sangotedo estate",
        4.5,
        5.0,
        R.drawable.pork_steak
    ),
    Food(
        9,
        "Roll philadelphia",
        "No 10 opp lekki phase 1 bridge in sangotedo estate",
        4.5,
        5.0,
        R.drawable.philadelphia_roll
    ),
)

@InstallIn(SingletonComponent::class)
@Module
class AppDatabaseModule {
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
                    for (item in foodList) {
                        val values = ContentValues()
                        values.apply {
                            put("id", item.id)
                            put("name", item.name)
                            put("description", item.description)
                            put("image", item.image)
                            put("rating", item.rating)
                            put("price", item.price)
                        }
                        db.insert("Food", SQLiteDatabase.CONFLICT_REPLACE, values)
                    }

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
                        for (i in 0..5) {
                            val randomFoodIndex = Random.nextInt(0, 9)
                            db.insert("RestaurantFood",
                                SQLiteDatabase.CONFLICT_REPLACE,
                                ContentValues().apply {
                                    put("restaurantId", item.id)
                                    put("foodId", randomFoodIndex)
                                })
                        }
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
            appContext, AppDatabase::class.java, "food_app.db"
        ).allowMainThreadQueries().addCallback(rdc).build()



        return appDatabase
    }
}