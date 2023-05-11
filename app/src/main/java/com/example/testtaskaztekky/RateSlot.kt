package com.example.testtaskaztekky

import javax.inject.Inject

class RateSlot @Inject constructor() : RateSlotInterface {

    private var balance = 50234
    private var rate = 0

    override fun increaseRateSlot() {
        rate += 1023
    }

    override fun decreaseRateSlot() {
        if (rate != 0) rate -= 1023
    }

    override fun getRandomImages(): RandomIm = RandomIm(
        image = kotlin.random.Random.nextInt(8),
        rotate = kotlin.random.Random.nextInt(15 - 8 + 1) + 8
    )

    override fun loseStake(rate: Int) {
       if (rate == 0){
           this.rate = 0
       }else{
           balance -= rate
       }
    }

    override fun winStake(rate: Int) {
        balance += rate * 3
    }

    override fun smallStake(rate: Int) {
        balance += rate * 2
    }


    override fun getBalance(): Int = balance
    override fun getRate(): Int = rate


}