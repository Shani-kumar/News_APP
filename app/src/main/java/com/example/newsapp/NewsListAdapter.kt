package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listner:Newsitemclicked):RecyclerView.Adapter<NewsViewHolder>() {
    private val items:ArrayList<News> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): NewsViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder=NewsViewHolder(view)
        view.setOnClickListener{
            listner.onItemClicked(items[viewHolder.absoluteAdapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem=items[position]
        holder.titleview.text=currentItem.title
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.imageView)
    }
    fun updateNews(updatedNews:ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()
    }
}
class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleview:TextView=itemView.findViewById(R.id.title)
    val imageView:ImageView=itemView.findViewById(R.id.imageview)

}
interface Newsitemclicked{
    fun onItemClicked(item:News)
}