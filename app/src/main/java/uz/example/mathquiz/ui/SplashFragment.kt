package uz.example.mathquiz.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.example.mathquiz.R
import uz.example.mathquiz.databinding.FragmentMenuBinding
import uz.example.mathquiz.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

val binding by lazy { FragmentSplashBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val scaleAnim=AnimationUtils.loadAnimation(requireContext(),R.anim.scale_anim)
        val rotateAnim=AnimationUtils.loadAnimation(requireContext(),R.anim.rotate_anim)

        binding.textView.startAnimation(scaleAnim)
        binding.imageView.startAnimation(rotateAnim)

        lifecycleScope.launch {
            delay(2800)
            findNavController().navigate(R.id.menuFragment)
        }

        return binding.root
    }

}