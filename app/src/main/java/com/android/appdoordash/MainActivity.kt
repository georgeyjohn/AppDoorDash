package com.android.appdoordash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.android.appdoordash.adapter.RestaurantAdapter
import com.android.appdoordash.entities.Restaurant
import com.android.appdoordash.http.DoorDashAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchRestaurants()
    }

    private fun fetchRestaurants() {
        DoorDashAPI.getInstance().getRestaurants()
            .enqueue(object : Callback<List<Restaurant>> {
                override fun onResponse(call: Call<List<Restaurant>>, response: Response<List<Restaurant>>) {
                    val adapterRestaurant = response.body()?.let {
                        RestaurantAdapter(this@MainActivity, it)
                    }
                    rv_restaurant?.layoutManager = LinearLayoutManager(this@MainActivity)
                    rv_restaurant?.setHasFixedSize(true)
                    rv_restaurant?.adapter = adapterRestaurant
                    rv_restaurant?.addItemDecoration(
                        DividerItemDecoration(
                            rv_restaurant?.context,
                            DividerItemDecoration.VERTICAL
                        )
                    )

                    adapterRestaurant?.notifyDataSetChanged()
                    adapterRestaurant?.setOnRestaurantClickListener(object :
                        RestaurantAdapter.OnRestaurantClickEventListener {
                        override fun onRestaurantClick(restaurant: Restaurant) {
                            val intent = Intent(this@MainActivity, RestaurantDetailsActivity::class.java)
                            intent.putExtra(RESTAURANT_ID, restaurant.getId())
                            startActivity(intent)
                        }
                    })

                }

                override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Oops!! Something went wrong. Please try after sometimes.", Toast.LENGTH_SHORT).show()

                }
            })
    }

    companion object {
        const val RESTAURANT_ID = "restaurantId"
    }

}
