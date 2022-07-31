package com.emrecinar.xxxx.aa.xa.x.restaurant_finder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.ActionBar
import com.emrecinar.xxxx.aa.xa.x.restaurant_finder.databinding.ActivityMainMenuBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.ArrayList

class MainMenuActivity : AppCompatActivity() {

    //ViewBinding
    private  lateinit var binding: ActivityMainMenuBinding

    //ActionBar
    private lateinit var actionBar: ActionBar

    //FirebaseAuth
    private  lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Configure action bar
        actionBar = supportActionBar!!
        actionBar.title = "Main-Menu"

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.CardView.setOnClickListener {
            startActivity(Intent(this,ProfileActivity::class.java))
        }


        val list = ArrayList<String>()
        val adapter = ArrayAdapter<String>(this, R.layout.list_view, list)
        binding.listView.adapter = adapter

        binding.spinnerCourse.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val selectedItem = parent?.getItemAtPosition(position).toString()

                if(selectedItem.equals("WantToGo")) {
                    startActivity(Intent(applicationContext, WantToGo::class.java))
                }
                else if(selectedItem.equals("Favorites")) {
                    startActivity(Intent(applicationContext, Favorites::class.java))
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


        /*
        binding.spinnerCourse.setOnItemClickListener { parent, view, position, id ->

            var selectedItem = parent.getItemAtPosition(position).toString()

            if(selectedItem.equals("Favorites")) {
                startActivity(Intent(this, ProfileActivity::class.java))
            }

        }*/

        val reference : DatabaseReference = FirebaseDatabase.getInstance().getReference().child("Restaurants")
        val postListener = object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                list.clear()

                for(snapshot : DataSnapshot in datasnapshot.children) {
                    list.add(snapshot.key.toString())
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        reference.addValueEventListener(postListener)

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            val element = parent.getItemAtPosition(position).toString()
            val intent = Intent(this, Restaurant::class.java)
            intent.putExtra("rest",element)
            startActivity(intent)
        }

        binding.searchEt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               adapter.filter.filter(s)
            }

        })
    }

    private fun checkUser() {
        //check user is logged in or not
        val firebaseUser = firebaseAuth.currentUser

        if(firebaseUser != null) {
            //user is not null, user logged in,get user info
            val email = firebaseUser.email
            //set to text view

            binding.textView.text = email!![0].toString().uppercase()
        }else {
            //user null, user is not logged in, go to login activity
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }
}