package com.android.appdoordash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.android.appdoordash.MainActivity.Companion.RESTAURANT_ID
import com.android.appdoordash.entities.Restaurant
import com.android.appdoordash.http.DoorDashAPI
import kotlinx.android.synthetic.main.activity_restaurant_details.*
import kotlinx.android.synthetic.main.item_restaurant.tv_restaurant_name
import kotlinx.android.synthetic.main.item_restaurant.tv_status
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantDetailsActivity : AppCompatActivity() {

    private var cellNumber: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_details)
        val restaurantId = intent.getIntExtra(RESTAURANT_ID, 0)
        getRestaurantDetails(restaurantId)


    }

    private fun getRestaurantDetails(restaurantId: Int) {
        DoorDashAPI.getInstance()
            .getRestaurantDetails(restaurantId)
            .enqueue(object : Callback<Restaurant> {
                override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>) {
                    setRestaurantDetails(response.body()!!)
                }

                override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                    Toast.makeText(
                        this@RestaurantDetailsActivity,
                        "Oops!! Something went wrong. Please try after sometimes.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setRestaurantDetails(restaurant: Restaurant) {
        tv_restaurant_name.text = restaurant.getName()
        tv_fee.text = restaurant.getDisplayDeliveryFee()
        tv_status.text = restaurant.getStatus()
        tv_description.text = restaurant.getDescription()
        tv_total_rating.text = restaurant.getTotalRating()
        tv_cell_no.text = restaurant.getCellNumber()
        cellNumber = restaurant.getCellNumber()
        val rating = restaurant.getDisplayRating()?.toFloat() ?: 0.toFloat()
        rb_rating.rating = rating
    }
}
