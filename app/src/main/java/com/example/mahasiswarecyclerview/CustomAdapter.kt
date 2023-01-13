package com.example.mahasiswarecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mahasiswarecyclerview.data.Response

class CustomAdapter(private val mList: List<Response>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
        val npm: TextView = ItemView.findViewById(R.id.npmTextView)
        val name: TextView = ItemView.findViewById(R.id.nameTextView)
        val gender: TextView = ItemView.findViewById(R.id.genderTextView)
        val major:TextView = ItemView.findViewById(R.id.majorTextView)
        val created: TextView = ItemView.findViewById(R.id.createdTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]

        holder.npm.text = itemsViewModel.npm
        holder.name.text = itemsViewModel.nama
        holder.gender.text = itemsViewModel.jenisKelamin
        holder.major.text = itemsViewModel.jurusan
        holder.created.text = itemsViewModel.createdAt
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}