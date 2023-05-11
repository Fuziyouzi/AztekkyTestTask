package com.example.testtaskaztekky

interface RateSlotInterface {

    fun getBalance(): Int
    fun getRate(): Int

    fun increaseRateSlot()

    fun decreaseRateSlot()

    fun getRandomImages(): RandomIm

    fun loseStake(rate: Int)

    fun winStake(rate: Int)
    fun smallStake(rate: Int)

}

data class RandomIm (val image: Int, val rotate: Int)