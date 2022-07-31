package com.emrecinar.xxxx.aa.xa.x.restaurant_finder

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.ActionBar
import com.emrecinar.xxxx.aa.xa.x.restaurant_finder.databinding.ActivityRestaurantBinding
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.util.ArrayList

class Restaurant : AppCompatActivity() {

    //ViewBinding
    private  lateinit var binding: ActivityRestaurantBinding

    //ActionBar
    private lateinit var actionBar: ActionBar

    //Firebase StorageReference
    private lateinit var storageReference: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val content = intent.getStringExtra("rest")
        binding.yazi.text = content

        actionBar = supportActionBar!!
        actionBar.title = content

        var menusContent = ""
        var list = ArrayList<String>()
        var adapter = ArrayAdapter<String>(this, R.layout.list_view, list)
        binding.menuListView.adapter = adapter

        var ref : DatabaseReference = FirebaseDatabase.getInstance().getReference().child("Restaurants").child(content!!)
        var reference : DatabaseReference = ref.child("Menu")
        val postListener = object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                list.clear()

                for(snapshot : DataSnapshot in datasnapshot.children) {
                    menusContent += snapshot.key.toString() + "   ~   Fiyat :" + snapshot.getValue().toString() + "TL"
                    list.add(menusContent)
                    menusContent = ""
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        reference.addValueEventListener(postListener)

        var rating = ""
        var location = ""
        var number = ""
        val postListener2 = object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                for(snapshot : DataSnapshot in datasnapshot.children) {
                    if(snapshot.key.toString().equals("rating")){
                    rating += snapshot.key.toString() + " : " + snapshot.getValue().toString()
                    binding.rating.text = rating
                    }
                    else if(snapshot.key.toString().equals("number")){
                        number += snapshot.getValue().toString()

                        storageReference = FirebaseStorage.getInstance().getReference("images/"+number+".jpg")
                        var localFile : File = File.createTempFile("tempfile",".jpg")
                        storageReference.getFile(localFile)
                            .addOnSuccessListener {
                                var bitmap : Bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                                binding.imageView.setImageBitmap(bitmap)
                            }
                            .addOnFailureListener {

                            }

                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        ref.addValueEventListener(postListener2)

        binding.location.setOnClickListener {
            var loc = binding.location.text.toString()
            DisplayTrack(loc)
        }

        binding.favBtn.setOnClickListener {
            Favorites.favList.add(content)
        }

        binding.wantToGo.setOnClickListener {
            WantToGo.wantToGoList.add(content)
        }

/*
        var location = ""
        val postListener3 = object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                for(snapshot : DataSnapshot in datasnapshot.children) {
                    if(snapshot.key.toString().equals("place")){
                        location += snapshot.key.toString() + " : " + snapshot.getValue().toString()
                        binding.location.text = location
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        ref.addValueEventListener(postListener3)*/
    }

    private fun DisplayTrack(loc: String) {
        try {
            var uri : Uri = Uri.parse("https://www.google.co.in/maps/search/" + binding.yazi.text)

            var intent = Intent(Intent.ACTION_VIEW,uri)
            intent.setPackage("com.google.android.apps.maps")
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }catch (ex : ActivityNotFoundException) {
            var uri2 : Uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps")
            var intent2 = Intent(Intent.ACTION_VIEW, uri2)
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent2)
        }

    }


}