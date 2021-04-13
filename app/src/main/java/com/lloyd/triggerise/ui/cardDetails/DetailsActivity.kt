package com.lloyd.triggerise.ui.cardDetails

import android.os.Bundle
import androidx.activity.viewModels
import com.lloyd.triggerise.ITEM_KEY
import com.lloyd.triggerise.R
import com.lloyd.triggerise.data.models.cards.Card
import com.lloyd.triggerise.databinding.DetailsLayoutBinding
import com.lloyd.triggerise.ui.base.BaseActivity
import com.lloyd.triggerise.utils.observe
import com.lloyd.triggerise.utils.toGone
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : BaseActivity() {

    private val viewModel: DetailsViewModel by viewModels()

    private lateinit var binding: DetailsLayoutBinding

    override fun initViewBinding() {
        binding = DetailsLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initIntentData(intent.getParcelableExtra(ITEM_KEY) ?: Card())
    }

    override fun observeViewModel() {
        observe(viewModel.cardData, ::initializeView)
    }

    private fun initializeView(card: Card) {
        binding.tvName.text = card.name
        binding.tvHeadline.text = card.artist
        binding.tvDescription.text = card.originalText
        Picasso.get().load(card.imageUrl).placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.ivCardImage)
        binding.pbLoading.toGone()
    }
}
