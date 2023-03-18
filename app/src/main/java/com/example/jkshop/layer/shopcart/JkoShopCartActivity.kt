package com.example.jkshop.layer.shopcart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.jkshop.R
import com.example.jkshop.databinding.ActivityShopCartBinding
import com.example.jkshop.databinding.ActivityShopDetailBinding
import com.example.jkshop.databinding.ItemShoppingCartBinding
import com.example.jkshop.model.ShopItemEntity
import com.example.jkshop.util.JkShopStaticValue
import org.koin.androidx.viewmodel.ext.android.viewModel

class JkoShopCartActivity: AppCompatActivity() {

    companion object {
        const val EXTRA_SHOP_ITEM_ID = "EXTRA_SHOP_ITEM_ID"
    }

    private lateinit var binding: ActivityShopCartBinding

    private val viewModel: JkoShopCartViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolBar()

        binding.tvTitle.text = getString(R.string.shop_cart_title, JkShopStaticValue.getNowUserName())

        binding.btnCheckOut.setOnClickListener {

        }

        viewModel.getShoppingCartList()

        observeData()
    }

    private fun initToolBar() {
        with(binding.toolbar) {
            setSupportActionBar(this)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            navigationIcon = ContextCompat.getDrawable(this@JkoShopCartActivity, R.drawable.chevron_left_sm_v2)?.apply {
                setTint(ContextCompat.getColor(this@JkoShopCartActivity, R.color.white))
            }
            setNavigationOnClickListener {
                onBackPressed()
            }
            title = getString(R.string.toolbar_title_shop_car)
        }
    }

    private fun observeData() {
        viewModel.apply {
            getShoppingCartList.observe(this@JkoShopCartActivity) { cartList ->
                if (cartList.isNotEmpty()) {
                    binding.scrollView.visibility = View.VISIBLE
                    binding.tvNoResult.visibility = View.GONE

                    binding.llShopList.removeAllViews()
                    cartList.forEach { item ->
                        ItemShoppingCartBinding.inflate(LayoutInflater.from(this@JkoShopCartActivity)).apply {
                            root.tag = item
                            tvName.text = item.name
                            cbChooseItem.setOnCheckedChangeListener { _, isChecked ->
                                val shopItem = root.tag as ShopItemEntity
                                if (isChecked) {
                                    buyList.add(shopItem)
                                } else {
                                    buyList.remove(shopItem)
                                }
                                checkButtonEnable()
                            }
                            binding.llShopList.addView(this.root)
                        }
                    }
                } else {
                    binding.scrollView.visibility = View.GONE
                    binding.tvNoResult.visibility = View.VISIBLE
                }
            }
            isBtnEnable.observe(this@JkoShopCartActivity) { enable ->
                binding.btnCheckOut.isEnabled = enable
            }
        }
    }
}