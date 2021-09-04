package com.vmmarinc.sweetanikca2.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vmmarinc.sweetanikca2.data.models.Comment
import com.vmmarinc.sweetanikca2.ui.listeners.ProductListener
import com.vmmarinc.sweetanikca2.data.models.Product
import com.vmmarinc.sweetanikca2.databinding.ItemProductBinding

class ProductAdapter(var items: List<Product>): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    var listener: ProductListener? = null
    class ViewHolder(val view: ItemProductBinding) : RecyclerView.ViewHolder(view.root)

    override fun getItemCount(): Int {
        return items.size

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemProductBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val view = holder.view
        view.itemProductTitle.text = item.title
        view.itemProductPrice.text = item.price
        Glide.with(view.root).load(item.image).into(view.itemProductImage);
        view.root.setOnClickListener {
            listener?.onClick(item)
        }

    }
    fun newDataset(products: List<Product>){
        items = products
        notifyDataSetChanged()
    }

}
