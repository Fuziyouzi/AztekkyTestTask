package com.example.testtaskaztekky

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.animation.doOnRepeat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.testtaskaztekky.databinding.SlotFragmentBinding
import com.example.testtaskaztekky.slots.IEventEnd
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random


@AndroidEntryPoint
class SlotFragment : Fragment(R.layout.slot_fragment), IEventEnd {

    private var binding: SlotFragmentBinding? = null
    private val bin get() = checkNotNull(binding)

    private var countDown = 0

    private val vm: SlotViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SlotFragmentBinding.inflate(inflater, container, false)

        vm.balance.observe(viewLifecycleOwner) {
            bin.balance.text = getString(R.string.balance, it.toString())
        }
        vm.rate.observe(viewLifecycleOwner) {
            bin.rate.text = "Rate  $it"
        }
        bin.plusRate.setOnClickListener {
            vm.increaseRate()
        }
        bin.minusRate.setOnClickListener {
            vm.decreaseRate()
        }

        bin.spin.setOnClickListener {
            vm.getNumber()
            bin.spin.isEnabled = false
        }

        vm.image0.observe(viewLifecycleOwner){
            bin.image0.setValueRandom(
                it.image,
                it.rotate
            )
        }
        vm.image1.observe(viewLifecycleOwner){
            bin.image1.setValueRandom(
                it.image,
                it.rotate
            )
        }
        vm.image2.observe(viewLifecycleOwner){
            bin.image2.setValueRandom(
                it.image,
                it.rotate
            )
        }
        vm.image3.observe(viewLifecycleOwner){
            bin.image3.setValueRandom(
                it.image,
                it.rotate
            )
        }
        vm.image4.observe(viewLifecycleOwner){
            bin.image4.setValueRandom(
                it.image,
                it.rotate
            )
        }
        vm.image5.observe(viewLifecycleOwner){
            bin.image5.setValueRandom(
                it.image,
                it.rotate
            )
        }
        vm.image6.observe(viewLifecycleOwner){
            bin.image6.setValueRandom(
                it.image,
                it.rotate
            )
        }
        vm.image7.observe(viewLifecycleOwner){
            bin.image7.setValueRandom(
                it.image,
                it.rotate
            )
        }
        vm.image8.observe(viewLifecycleOwner){
            bin.image8.setValueRandom(
                it.image,
                it.rotate
            )
        }

        bin.apply {
            image0.setEventEnd(this@SlotFragment)
            image1.setEventEnd(this@SlotFragment)
            image2.setEventEnd(this@SlotFragment)
            image3.setEventEnd(this@SlotFragment)
            image4.setEventEnd(this@SlotFragment)
            image5.setEventEnd(this@SlotFragment)
            image6.setEventEnd(this@SlotFragment)
            image7.setEventEnd(this@SlotFragment)
            image8.setEventEnd(this@SlotFragment)
        }

        vm.result.observe(viewLifecycleOwner){
            Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG).show()
        }

        return bin.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun eventEnd(result: Int, count: Int) {
        if (countDown < 8)
            countDown++
        else {
            countDown = 0
            bin.spin.isEnabled = true
            vm.checkWin()
        }
    }
}
