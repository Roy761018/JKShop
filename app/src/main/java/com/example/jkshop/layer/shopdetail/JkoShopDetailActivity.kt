package com.example.jkshop.layer.shopdetail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.jkshop.R
import com.example.jkshop.databinding.ActivityShopDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class JkoShopDetailActivity: AppCompatActivity() {

    companion object {
        const val EXTRA_SHOP_ITEM_ID = "EXTRA_SHOP_ITEM_ID"
    }

    private lateinit var binding: ActivityShopDetailBinding

    private val viewModel: JkoShopDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolBar()

        viewModel.getShopDetail(intent.extras?.getString(EXTRA_SHOP_ITEM_ID, "").toString())

        binding.btnAddToCart.setOnClickListener {
            viewModel.addItemToShopCart()
        }

        binding.btnByNow.setOnClickListener {

        }

        observeData()
    }

    private fun initToolBar() {
        with(binding.toolbar) {
            setSupportActionBar(this)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            navigationIcon = ContextCompat.getDrawable(this@JkoShopDetailActivity, R.drawable.chevron_left_sm_v2)?.apply {
                setTint(ContextCompat.getColor(this@JkoShopDetailActivity, R.color.white))
            }
            setNavigationOnClickListener {
                onBackPressed()
            }
            title = getString(R.string.toolbar_title_shop_detail)
        }
    }

    private fun observeData() {
        viewModel.apply {
            getShopItem.observe(this@JkoShopDetailActivity) {
                binding.tvName.text = it.name
                binding.tvDescription.text = it.description
                binding.tvShopId.text = getString(R.string.shop_item_id_text, it.shopId)
                binding.tvPrice.text = getString(R.string.shop_item_price_text, it.price.toString())
            }
            isAddSuccess.observe(this@JkoShopDetailActivity) { isSuccess ->
                if (isSuccess) {
                    Toast.makeText(this@JkoShopDetailActivity, getString(R.string.add_to_cart_success), Toast.LENGTH_SHORT).show()
                    onBackPressed()
                } else {
                    Toast.makeText(this@JkoShopDetailActivity, getString(R.string.add_to_cart_fail), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}