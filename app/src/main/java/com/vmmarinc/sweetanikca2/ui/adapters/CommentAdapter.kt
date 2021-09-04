package com.vmmarinc.sweetanikca2.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vmmarinc.sweetanikca2.ui.listeners.CommentListener
import com.vmmarinc.sweetanikca2.R
import com.vmmarinc.sweetanikca2.data.models.Comment
import com.vmmarinc.sweetanikca2.databinding.ItemCommentBinding

class CommentAdapter(var items: List<Comment>): RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    var listener: CommentListener? = null

    class ViewHolder(val view: ItemCommentBinding): RecyclerView.ViewHolder(view.root)

    override fun getItemCount(): Int {
        return items.size

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemCommentBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val view = holder.view
        view.itemCommentName.text = item.name
        view.itemCommentDescription.text = item.description
        Glide.with(view.root).load(item.image).into(view.itemCommentImage);
        //view.itemCommentImage.setImageResource(R.mipmap.ic_launcher)
        view.root.setOnClickListener {
            listener?.onClick(item)
        }
    }

    fun newDataset(comments: List<Comment>){
        items = comments
        notifyDataSetChanged()
    }

}