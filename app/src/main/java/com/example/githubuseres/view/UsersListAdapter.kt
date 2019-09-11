package com.example.githubuseres.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuseres.R
import com.example.githubuseres.model.UsersData
import com.example.githubuseres.util.getProgressDrawable
import com.example.githubuseres.util.loadImage
import kotlinx.android.synthetic.main.users_list.view.*

class UsersListAdapter(var users: ArrayList<UsersData>) :RecyclerView.Adapter<UsersListAdapter.UserViewHolder>(){


    fun updateUsers(newUsers: List<UsersData>){
        users.clear()
        users.addAll(newUsers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.users_list, parent, false)
        )


    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }


class UserViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val imageView = view.avatarImage_view
    private val userName = view.usersTextView
    private val repos = view.repos
    private val progressDrawable = getProgressDrawable(view.context)

    fun bind(users: UsersData){
        userName.text = users.loginName
        repos.text = users.repos
        imageView.loadImage(users.avatar,progressDrawable)

    }
}

}