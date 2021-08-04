package com.xing.android.androidbasics4

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var userAdapter: UsersAdapter
        val userRecyclerView: RecyclerView = findViewById(R.id.recyclerUsers)
        userRecyclerView.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)

        lifecycleScope.launch {
            val users = getUsers()
            Log.d(TAG, "Retrieved $users")
            userAdapter = UsersAdapter(users!!, baseContext)
            userRecyclerView.adapter = userAdapter
            //userAdapter.notifyDataSetChanged()
        }
    }

    private suspend fun getUsers(): List<User>? {
        return withContext(Dispatchers.IO) {
            MyApi().getUsers().body()?.results
        }
    }
}
