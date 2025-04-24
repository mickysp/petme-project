package com.example.petme

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petme.databinding.PetRecyclerItemBinding

class PetAdapter (val petList: ArrayList<PetHome>, val context: Context):
    RecyclerView.Adapter<PetAdapter.ViewHolder>(){

    inner class ViewHolder(view: View, val binding: PetRecyclerItemBinding) :
        RecyclerView.ViewHolder(view) {
        init {
            binding.cvPet.setOnClickListener{
                val item = petList[adapterPosition]
                val contextView: Context = view.context
                val intent = Intent(contextView, DetailActivity::class.java)
                intent.putExtra("mId",item.petdetail_id.toString())
                intent.putExtra("mImage",item.picture_pet)
                intent.putExtra("mOld",item.old.toString())
                intent.putExtra("mMonth",item.month.toString())
                intent.putExtra("mDistrict",item.district)
                intent.putExtra("mAdminid",item.admin_id.toString())
                intent.putExtra("mUserid",item.user_id.toString())
                intent.putExtra("mDetail",item.detail)
                intent.putExtra("mName",item.name)
                intent.putExtra("mType",item.typeanimal)
                intent.putExtra("mGender",item.gender)
                intent.putExtra("mStatus",item.status_pet)
                contextView.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PetRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent,
            false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding

        Glide.with(context).load(petList[position].picture_pet).into(binding.imagePeet)
        binding.txtName.text = petList!![position].name
        binding.txtDistrict.text = petList!![position].district
        binding.txtold.text = "อายุ: " + petList!![position].old.toString() + " ปี " + petList!![position].month.toString() + " เดือน"
    }

    override fun getItemCount(): Int {
        return petList!!.size
    }
}
