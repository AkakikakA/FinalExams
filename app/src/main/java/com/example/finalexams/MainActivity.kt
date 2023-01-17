package com.example.finalexams

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.finalexams.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding :ActivityMainBinding
    private lateinit var database :DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(home())
        binding.click_me.setOnClickListener {
            val id = binding.id.text.toString()
            val skill = binding.skill.text.toString()
            database = FirebaseDatabase.getInstance().getReferance ("User")
            val User=User(id,skill)
            database.child(userName).setValue(User).addOnSuccessListener {
                binding.id.text.clear()
                binding.skill.text.clear()
                Toast.makeText( this,"Successfully Saved", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {
                Toast.makeText(this, "failed",Toast.LENGTH_SHORT ).show()
            }
        }

        val clickme = findViewById<TextView>(R.id.click_me)

        clickme.setOnClickListener {

            Toast.makeText(this, "Information sent", Toast.LENGTH_SHORT).show()

        }



        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(home())
                R.id.profile -> replaceFragment(Profile())
                R.id.settings -> replaceFragment(Settings())
                else ->{

                }
            }
            true
        }


    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()



    }

}