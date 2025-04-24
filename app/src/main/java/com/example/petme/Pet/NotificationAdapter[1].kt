package com.example.petme.Pet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petme.PetAdapter
import com.example.petme.PetHome
import com.example.petme.databinding.NotiFieldBinding
import com.example.petme.databinding.PetRecyclerItemBinding

class NotificationAdapter (val verifyList: ArrayList<VerifyNoti>, val context: Context):
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>(){
    inner class ViewHolder(view: View, val binding: NotiFieldBinding) :
        RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationAdapter.ViewHolder {
        val binding = NotiFieldBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: NotificationAdapter.ViewHolder, position: Int) {
        val binding = holder.binding
        binding.textNoti.text = verifyList!![position].text_noti
    }

    override fun getItemCount(): Int {
        return verifyList!!.size
    }
}