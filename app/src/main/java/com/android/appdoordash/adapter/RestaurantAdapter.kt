package com.android.appdoordash.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.android.appdoordash.R
import com.android.appdoordash.entities.Restaurant
import com.bumptech.glide.Glide

class RestaurantAdapter(private var mContext: Context, private var mRestaurants: List<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    private var mOnRestaurantClickListener: OnRestaurantClickEventListener? = null


    inner class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRestaurantName: TextView = itemView.findViewById(R.id.tv_restaurant_name)
        val tvRestaurantDescription: TextView = itemView.findViewById(R.id.tv_restaurant_description)
        val tvDeliveryFee: TextView = itemView.findViewById(R.id.tv_delivery_fee)
        val tvStatus: TextView = itemView.findViewById(R.id.tv_status)
        var imRestaurantImage: ImageView = itemView.findViewById(R.id.im_restaurant)
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(p0.context)
            .inflate(R.layout.item_restaurant, p0, false)
        return RestaurantViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mRestaurants.size
    }

    override fun onBindViewHolder(p0: RestaurantViewHolder, p1: Int) {
        val restaurant = mRestaurants[p1]


        Glide.with(mContext)
            .load(restaurant.getCoverImgUrl())
            .into(p0.imRestaurantImage)

        p0.tvRestaurantName.text = restaurant.getName()
        p0.tvRestaurantDescription.text = restaurant.getDescription()
        p0.tvDeliveryFee.text = restaurant.getDisplayDeliveryFee()
        p0.tvStatus.text = restaurant.getStatus()

        p0.itemView.setOnClickListener {
            mOnRestaurantClickListener?.onRestaurantClick(restaurant)
        }
    }

    fun setOnRestaurantClickListener(listener: OnRestaurantClickEventListener) {
        mOnRestaurantClickListener = listener
    }

    interface OnRestaurantClickEventListener {
        fun onRestaurantClick(restaurant: Restaurant)
    }
}


