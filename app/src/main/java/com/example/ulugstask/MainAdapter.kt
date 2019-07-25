package com.example.ulugstask

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.user_info.view.*

class MainAdapter(private var postList: List<Repos>): RecyclerView.Adapter<MainViewholder>()
{
    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainViewholder {
            val itemView= LayoutInflater.from(parent?.context)
                .inflate(R.layout.user_info,parent,false)
                return MainViewholder(itemView)
    }

    override fun onBindViewHolder(holder: MainViewholder?, position: Int) {
        holder?.txtFork?.text = postList[position].forks.toString()
        holder?.txtLikes?.text=postList[position].stargazers_count.toString()
        holder?.txtWatches?.text=postList[position].watchers_count.toString()
        holder?.txtProjects?.text=postList[position].name

     }
}
class MainViewholder(itemView: View):RecyclerView.ViewHolder(itemView) {
    var txtLikes = itemView.tv_like
    var txtWatches = itemView.tv_watches
    var txtFork = itemView.tv_folk
    var txtProjects = itemView.project

}