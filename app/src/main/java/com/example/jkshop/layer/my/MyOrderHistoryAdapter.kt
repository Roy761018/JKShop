package com.example.jkshop.layer.my

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jkshop.R
import com.example.jkshop.databinding.ItemOrderHistoryBinding
import com.example.jkshop.databinding.ItemShopListBinding
import com.example.jkshop.model.ShopItemEntity
import com.example.jkshop.model.ShopOrderEntity

class MyOrderHistoryAdapter(private val deleteAction: (String) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var orderList: List<ShopOrderEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemOrderHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false).run {
            ItemViewHolder(this)
        }
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bindView(orderList[position], deleteAction)
        }
    }

    class ItemViewHolder(private val binging: ItemOrderHistoryBinding): RecyclerView.ViewHolder(binging.root) {

        fun bindView(shopOrderEntity: ShopOrderEntity, deleteAction: (String) -> Unit) {
            val context = binging.root.context
            binging.tvOrderId.text = context.getString(R.string.order_serial_id, shopOrderEntity.orderID)
            binging.tvOrderPrice.text = context.getString(R.string.order_price, shopOrderEntity.orderPrice.toString())
            binging.tvOrderCreateTime.text = context.getString(R.string.order_create_time, shopOrderEntity.orderCreateTime)

            binging.llOrderItem.removeAllViews()
            shopOrderEntity.shopItemEntityList.forEach {
                ItemShopListBinding.inflate(LayoutInflater.from(context)).apply {
                    tvName.text = it.name
                    tvPrice.text = it.price.toString()
                    binging.llOrderItem.addView(this.root)
                }
            }
            binging.btnDeleteOrder.setOnClickListener {
                deleteAction.invoke(shopOrderEntity.orderID)
            }
        }
    }
}