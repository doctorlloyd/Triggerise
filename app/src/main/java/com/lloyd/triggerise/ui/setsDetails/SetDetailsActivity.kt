package com.lloyd.triggerise.ui.setsDetails

import android.os.Bundle
import androidx.activity.viewModels
import com.lloyd.triggerise.ITEM_KEY
import com.lloyd.triggerise.data.models.sets.Set
import com.lloyd.triggerise.databinding.DetailsLayoutBinding
import com.lloyd.triggerise.ui.base.BaseActivity
import com.lloyd.triggerise.utils.observe
import com.lloyd.triggerise.utils.toGone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetDetailsActivity : BaseActivity() {

    private val viewModelSet: SetDetailsViewModel by viewModels()

    private lateinit var binding: DetailsLayoutBinding

    override fun initViewBinding() {
        binding = DetailsLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelSet.initIntentData(intent.getParcelableExtra(ITEM_KEY) ?: Set())
    }

    override fun observeViewModel() {
        observe(viewModelSet.setData, ::initializeView)
    }

    private fun initializeView(set: Set) {
        binding.tvName.text = set.name
        binding.tvHeadline.text = set.releaseDate
        binding.tvDescription.text = set.type
        binding.pbLoading.toGone()
    }
}
