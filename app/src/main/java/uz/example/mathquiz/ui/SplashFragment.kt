package uz.example.mathquiz.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.example.mathquiz.R
import uz.example.mathquiz.databinding.FragmentMenuBinding
import uz.example.mathquiz.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

val binding by lazy { FragmentSplashBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.button.setOnClickListener {
        findNavController().navigate(R.id.menuFragment2)
        }
        return binding.root
    }

}