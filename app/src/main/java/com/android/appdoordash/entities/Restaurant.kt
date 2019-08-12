package com.android.appdoordash.entities

class Restaurant {
    private val dollar = "$"
    private val free = "FREE"

    private var id: Int = 0
    private var name: String? = null
    private var description: String? = null
    private var coverImgUrl: String? = null
    private var status: String? = null
    private var deliveryFee: Int = 0
    private var displayDeliveryFee: String? = null
    private var averageRating: Double = 0.toDouble()
    private var numberOfRatings: Int = 0
    private var displayRating: Double? = null
    private var phoneNumber: String? = null

    fun getId(): Int {
        return id
    }

    fun getName(): String? {
        return name
    }

    fun getDescription(): String? {
        return description
    }

    fun getCoverImgUrl(): String? {
        return coverImgUrl
    }

    fun getStatus(): String? {
        return status
    }

    fun getCellNumber(): String? {
        return "Phone Number: $phoneNumber"
    }

    fun getDisplayDeliveryFee(): String? {
        if (displayDeliveryFee == null) {
            updateDisplayDeliveryFee()
        }

        return displayDeliveryFee
    }

    fun getDisplayRating(): Double? {
        if (displayRating == null) {
            updateDisplayRating()
        }

        return displayRating
    }

    fun getTotalRating(): String? {
        return "$numberOfRatings reviews"
    }

    private fun updateDisplayDeliveryFee() {
        var display: String
        if (deliveryFee <= 0) {
            display = free
        } else {
            val dollars = deliveryFee / 100
            val cents = deliveryFee % 100
            display = "$dollar$dollars."

            if (cents < 10) {
                display += "0"
            }
            display += cents
        }
        displayDeliveryFee = display
    }

    private fun updateDisplayRating() {
        displayRating = averageRating
    }
}