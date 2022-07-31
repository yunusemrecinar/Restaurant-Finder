package com.emrecinar.xxxx.aa.xa.x.restaurant_finder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.ActionBar
import com.emrecinar.xxxx.aa.xa.x.restaurant_finder.databinding.ActivityFavoritesBinding
import com.google.firebase.database.*
import java.lang.Exception
import java.util.ArrayList
import java.util.stream.Collector

class Favorites : AppCompatActivity() {

    private lateinit var binding : ActivityFavoritesBinding

    private lateinit var actionBar: ActionBar

    companion object {
        var favList = ArrayList<String>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title = "Favorites"

        var favListView = ArrayList<String>();
        var adapter = ArrayAdapter<String>(this, R.layout.list_view, favListView)
        binding.favListView.adapter = adapter

        val reference : DatabaseReference = FirebaseDatabase.getInstance().getReference().child("Restaurants")
        val postListener = object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {

                for(snapshot : DataSnapshot in datasnapshot.children) {

                    if(favList.contains(snapshot.key.toString())) {
                        favListView.add(snapshot.key.toString())
                    }

                }

                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        reference.addValueEventListener(postListener)

        binding.favListView.setOnItemClickListener { parent, view, position, id ->
            val element = parent.getItemAtPosition(position).toString()
            val intent = Intent(this, Restaurant::class.java)
            intent.putExtra("rest",element)
            startActivity(intent)
        }

        /*
        try {
            binding.textView2.text = list.get(0)
        }catch (ex : Exception) {

        }*/

    }

}