package com.example.jkshop.layer.shoplist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jkshop.databinding.ItemShopListBinding
import com.example.jkshop.model.ShopItemEntity

class JkoShopListAdapter(private val clickAction: (ShopItemEntity) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var shopList: List<ShopItemEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemShopListBinding.inflate(LayoutInflater.from(parent.context), parent, false).run {
            ItemViewHolder(this)
        }
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bindView(shopList[position], clickAction)
        }
    }

    class ItemViewHolder(private val binging: ItemShopListBinding): RecyclerView.ViewHolder(binging.root) {

        fun bindView(shopItemEntity: ShopItemEntity, clickAction: (ShopItemEntity) -> Unit) {
            binging.tvName.text = shopItemEntity.name
            binging.tvPrice.text = shopItemEntity.price.toString() + " 元"
            binging.root.setOnClickListener {
                clickAction.invoke(shopItemEntity)
            }
        }
    }
}