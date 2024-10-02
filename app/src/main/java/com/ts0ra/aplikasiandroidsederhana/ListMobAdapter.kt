package com.ts0ra.aplikasiandroidsederhana

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ts0ra.aplikasiandroidsederhana.databinding.ItemRowMobBinding

class ListMobAdapter(private val listMob: ArrayList<Mob>) : RecyclerView.Adapter<ListMobAdapter.ListViewHolder>() {

    class ListViewHolder(var binding: ItemRowMobBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowMobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listMob.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listMob[position]
        holder.binding.imgItemPhoto.setImageResource(photo)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description
        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra(DetailActivity.MOB_DETAIL, listMob[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

}