package com.example.homeworkfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homeworkfragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listFragment = supportFragmentManager.findFragmentById(R.id.fragment_in_main_activity) as ListFragment

        listFragment.setItemClickListener {
            val detailsFragment = DetailsFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.details_fragment, detailsFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
