package com.xing.android.androidbasics4

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class UsersAdapter(private val items: List<User>, private val context: Context) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private val picasso = Picasso.get()

    companion object{
        var userMap = mutableMapOf<Int, User>()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val userName: TextView = view.findViewById(R.id.fullName)
        val profileImage: ImageView = view.findViewById(R.id.userProfile)
        val userLayout: LinearLayout = view.findViewById(R.id.userLayout)

        fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
            itemView.setOnClickListener {
                event.invoke(adapterPosition, itemViewType)
            }
            return this
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_view, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = items[position]
        holder.userName.text = "${user.firstname} ${user.lastname}"
        picasso.load(user.image.replace("http", "https")).into(holder.profileImage)

        holder.userLayout.setOnClickListener {
            toInfoActivity(position)
        }

        userMap[position] = user
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    private fun toInfoActivity(position: Int) {
        val intent = Intent(context, InfoActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.putExtra("currentUserPosition", position)
        context.startActivity(intent)
    }
}
