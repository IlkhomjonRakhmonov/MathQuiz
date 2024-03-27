package uz.example.mathquiz.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import uz.example.mathquiz.R
import uz.example.mathquiz.databinding.FragmentMenuBinding

//@Suppress("DEPRECATION")
@Suppress("DEPRECATION")
class MenuFragment : Fragment() {
    private val binding by lazy { FragmentMenuBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.gameFragment)
        }
//       val actionBar= (requireActivity() as AppCompatActivity).supportActionBar

        return binding.root
    }

//    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId){
            android.R.id.home->{ requireActivity().supportFragmentManager.popBackStack()
                Toast.makeText(requireContext(), "back bosildi", Toast.LENGTH_SHORT).show()
//                requireActivity().onBackPressed()
                Log.d("home", "onOptionsItemSelected: ${android.R.id.home}")

               true
            }
            R.id.save ->{
                Toast.makeText(requireContext(), "Save", Toast.LENGTH_SHORT).show()
                Log.d("save", "onOptionsItemSelected: ${R.id.save}")
                true
            }
            
            R.id.delete->{
                Toast.makeText(requireContext(), "Delete", Toast.LENGTH_SHORT).show()
                true
            }
            
            R.id.search->{
                Toast.makeText(requireContext(), "Search", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)

        }


    }



}