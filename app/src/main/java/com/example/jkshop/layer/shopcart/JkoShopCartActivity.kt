package com.example.jkshop.layer.shopcart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.jkshop.R
import com.example.jkshop.base.observeLiveData
import com.example.jkshop.databinding.ActivityShopCartBinding
import com.example.jkshop.databinding.ItemShoppingCartBinding
import com.example.jkshop.layer.order.JkoShopOrderConfirmActivity
import com.example.jkshop.model.ShopItemEntity
import com.example.jkshop.util.JkShopStaticValue
import org.koin.androidx.viewmodel.ext.android.viewModel

class JkoShopCartActivity: AppCompatActivity() {

    companion object {
        private const val EXTRA_SHOP_ORDER_LIST = "EXTRA_SHOP_ORDER_LIST"

    }

    private lateinit var binding: ActivityShopCartBinding

    private val viewModel: JkoShopCartViewModel by viewModel()

    private val orderConfirmResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolBar()

        binding.tvTitle.text = getString(R.string.shop_cart_title, JkShopStaticValue.getNowUserName())

        binding.btnCheckOut.setOnClickListener {
            Intent(this@JkoShopCartActivity, JkoShopOrderConfirmActivity::class.java).apply {
                putExtra(EXTRA_SHOP_ORDER_LIST, viewModel.buyList)
                orderConfirmResultLauncher.launch(this)
            }
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
            observeLiveData(getShoppingCartList) { cartList ->
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
            observeLiveData(isBtnEnable) { enable ->
                binding.btnCheckOut.isEnabled = enable
            }
        }
    }
}