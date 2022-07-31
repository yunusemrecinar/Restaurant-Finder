package com.emrecinar.xxxx.aa.xa.x.restaurant_finder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.ActionBar
import com.emrecinar.xxxx.aa.xa.x.restaurant_finder.databinding.ActivityWantToGoBinding
import com.google.firebase.database.*
import java.util.ArrayList

class WantToGo : AppCompatActivity() {

    private lateinit var binding: ActivityWantToGoBinding

    private lateinit var actionBar: ActionBar

    companion object {
        var wantToGoList = ArrayList<String>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWantToGoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title = "Want To Go"

        var wantToGoListView = ArrayList<String>()
        var adapter = ArrayAdapter<String>(this, R.layout.list_view, wantToGoListView)
        binding.wantToGoListView.adapter = adapter

        val reference : DatabaseReference = FirebaseDatabase.getInstance().getReference().child("Restaurants")
        val postListener = object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {

                for(snapshot : DataSnapshot in datasnapshot.children) {

                    if(wantToGoList.contains(snapshot.key.toString())) {
                        wantToGoListView.add(snapshot.key.toString())
                    }

                }
                adapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        reference.addValueEventListener(postListener)
    }
}