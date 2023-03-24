package com.example.jkshop.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Entity(indices = [Index(value = ["shop_id"], unique = true)])
@Parcelize
data class ShopItemEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "shop_id")val shopId: String,
    val name: String,
    val description: String,
    val price: Int = 0,
    val createTime: String,
    val img: String? = null
): Parcelable
