package com.route.newsappc31.UI.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.route.data.ArticlesItem
import com.route.newsappc31.R
import com.route.newsappc31.databinding.ItemNewsBinding


/**
 * Created by Mohamed Nabil Mohamed on 4/5/2020.
 * m.nabil.fci2015@gmail.com
 */
class NewsAdapter ():RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

    var data:List<ArticlesItem?>? =null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val databinding =DataBindingUtil.inflate<ItemNewsBinding>(
            LayoutInflater.from(parent.context),R.layout.item_news,parent,false)
            return ViewHolder(databinding)
    }

    override fun getItemCount(): Int {
        return data?.size?:0
    }

    fun changeData(items:List<ArticlesItem?>){
        this.data=items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news= data?.get(position)
        if (news==null)return
       /* Glide.with(holder.itemView)
            .load(news.urlToImage)
            .into(holder.databinding.image)
       */
        holder.bind(news)

    }

    class ViewHolder(val databinding:ItemNewsBinding):RecyclerView.ViewHolder(databinding.root){

        fun bind(item:ArticlesItem){
            databinding.item=item
            databinding.executePendingBindings()
        }
    }
}
