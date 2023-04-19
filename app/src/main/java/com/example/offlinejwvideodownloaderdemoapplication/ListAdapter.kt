package com.example.offlinejwvideodownloaderdemoapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.offlinejwvideodownloaderdemoapplication.model.Beans

class ListAdapter(private val listener: CustomListeners, private val itemList: ArrayList<Beans>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    public interface CustomListeners {
        fun onClickListener(item: Beans)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.video_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.textView.text = item.lessonName
        holder.textView.setOnClickListener {
            listener.onClickListener(item)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView
        init {
            textView = itemView.findViewById(R.id.title)
        }
    }
}
