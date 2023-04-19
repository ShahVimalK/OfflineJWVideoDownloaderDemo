package com.example.offlinejwvideodownloaderdemoapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(context: Context?, private val items: List<String>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    private var selectedItem = -1
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
    }

    fun setSelectedItem(position: Int) {
        selectedItem = position
        notifyDataSetChanged()
    }

    fun getSelectedItem(): Int {
        return selectedItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.dialog_quality_option, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.textItem.text = item
        holder.radioItem.isChecked = position == selectedItem
        holder.itemView.setOnClickListener {

            if (selectedItem != holder.bindingAdapterPosition) {
                selectedItem = holder.bindingAdapterPosition
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textItem: TextView
        var radioItem: RadioButton

        init {
            textItem = itemView.findViewById(R.id.quality)
            radioItem = itemView.findViewById(R.id.radio_button)
        }
    }
}