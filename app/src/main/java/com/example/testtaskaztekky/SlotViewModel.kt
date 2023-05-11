package com.example.testtaskaztekky

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SlotViewModel @Inject constructor(private val rateSlotInterface: RateSlotInterface) :
    ViewModel() {

    private val _balance: MutableLiveData<Int> = MutableLiveData()
    val balance: LiveData<Int> = _balance

    private val _rate: MutableLiveData<Int> = MutableLiveData()
    val rate: LiveData<Int> = _rate

    private val _image0: MutableLiveData<RandomIm> = MutableLiveData()
    val image0: LiveData<RandomIm> = _image0
    private val _image1: MutableLiveData<RandomIm> = MutableLiveData()
    val image1: LiveData<RandomIm> = _image1
    private val _image2: MutableLiveData<RandomIm> = MutableLiveData()
    val image2: LiveData<RandomIm> = _image2
    private val _image3: MutableLiveData<RandomIm> = MutableLiveData()
    val image3: LiveData<RandomIm> = _image3
    private val _image4: MutableLiveData<RandomIm> = MutableLiveData()
    val image4: LiveData<RandomIm> = _image4
    private val _image5: MutableLiveData<RandomIm> = MutableLiveData()
    val image5: LiveData<RandomIm> = _image5
    private val _image6: MutableLiveData<RandomIm> = MutableLiveData()
    val image6: LiveData<RandomIm> = _image6
    private val _image7: MutableLiveData<RandomIm> = MutableLiveData()
    val image7: LiveData<RandomIm> = _image7
    private val _image8: MutableLiveData<RandomIm> = MutableLiveData()
    val image8: LiveData<RandomIm> = _image8

    private val _result: MutableLiveData<String> = MutableLiveData()
    val result: LiveData<String> = _result


    init {
        getBalance()
        getRate()
    }

    fun getNumber() {
        _image0.value = rateSlotInterface.getRandomImages()
        _image1.value = rateSlotInterface.getRandomImages()
        _image2.value = rateSlotInterface.getRandomImages()
        _image3.value = rateSlotInterface.getRandomImages()
        _image4.value = rateSlotInterface.getRandomImages()
        _image5.value = rateSlotInterface.getRandomImages()
        _image6.value = rateSlotInterface.getRandomImages()
        _image7.value = rateSlotInterface.getRandomImages()
        _image8.value = rateSlotInterface.getRandomImages()

    }

    fun checkWin() {
        if (image3.value?.image == image4.value?.image && image4.value?.image == image5.value?.image) {
            _result.value = "Win"
            _rate.value?.let { rateSlotInterface.winStake(it) }
        } else if (image3.value?.image == image4.value?.image || image4.value?.image == image5.value?.image || image3.value?.image == image5.value?.image) {
            _result.value = "Small Win"
            _rate.value?.let { rateSlotInterface.smallStake(it) }
        } else {
            _result.value = "You loose"
            _rate.value?.let { rateSlotInterface.loseStake(it) }
        }
        getBalance()
    }


    fun increaseRate() {
        rateSlotInterface.increaseRateSlot()
        getRate()
    }

    fun decreaseRate() {
        rateSlotInterface.decreaseRateSlot()
        getRate()
    }

    private fun getRate() {
        _rate.value = rateSlotInterface.getRate()
    }

    private fun getBalance() {
        _balance.value = rateSlotInterface.getBalance()
    }


}