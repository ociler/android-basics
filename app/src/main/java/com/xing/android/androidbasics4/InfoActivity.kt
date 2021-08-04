package com.xing.android.androidbasics4

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InfoActivity: AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val fullName: TextView = findViewById(R.id.fullName)
        val email: TextView = findViewById(R.id.email)
        val ipAddress: TextView = findViewById(R.id.ipAddress)
        val macAddress: TextView = findViewById(R.id.macAddress)
        val website: TextView = findViewById(R.id.website)
        val profileImage: ImageView = findViewById(R.id.profileImage)

        val userPosition = intent.getIntExtra("currentUserPosition", 0)
        
        val currentUser = UsersAdapter.userMap[userPosition]!!

        fullName.text = "${fullName.text} ${currentUser.firstname} ${currentUser.lastname}"
        email.text = "${email.text} ${currentUser.email}"
        ipAddress.text = "${ipAddress.text} ${currentUser.ipAddress}"
        macAddress.text = "${macAddress.text} ${currentUser.macAddress}"
        website.text = "${website.text} ${currentUser.website}"
        Picasso.get().load(currentUser.image.replace("http", "https")).into(profileImage)
    }
}
