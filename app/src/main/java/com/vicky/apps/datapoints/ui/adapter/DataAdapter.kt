package com.vicky.apps.datapoints.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.ui.viewmodel.ArtistsNameList
import de.hdodenhof.circleimageview.CircleImageView


class DataAdapter constructor(var response: ArtistsNameList) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_child_view,parent,false)
        return DataViewHolder(v)
    }

    override fun getItemCount(): Int = response.data?.size ?: 0

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        Picasso.get().load(response.data?.get(position)?.picture).into(holder.artistImageView)
        holder.artistTextView.text = response.data?.get(position)?.name

    }

    fun updateData(response: ArtistsNameList) {
        this.response = response
        notifyDataSetChanged()
    }
    class DataViewHolder(v:View): RecyclerView.ViewHolder(v){
        var artistTextView: TextView = v.findViewById(R.id.artistName)
        var artistImageView: CircleImageView = v.findViewById(R.id.artistImage)

    }
}