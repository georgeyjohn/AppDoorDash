package com.android.appdoordash.http

import com.android.appdoordash.entities.Restaurant
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DoorDashAPI : IDoorDashAPI {

    private var mDoorDashAPI: IDoorDashAPI? = null
    private val lat = 37.422740
    private val lng = -122.139956

    init {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        mDoorDashAPI = retrofit.create(IDoorDashAPI::class.java)
    }

    companion object {
        private val instance: DoorDashAPI = DoorDashAPI()
        fun getInstance(): DoorDashAPI {
            return instance
        }
    }

    override fun getRestaurants(lat: Double, lng: Double): Call<List<Restaurant>> {
        return mDoorDashAPI!!.getRestaurants(lat, lng)
    }

    override fun getRestaurantDetails(restaurantId: Int): Call<Restaurant> {
        return mDoorDashAPI!!.getRestaurantDetails(restaurantId)
    }

    fun getRestaurants(): Call<List<Restaurant>> {
        return getRestaurants(lat, lng)
    }
}