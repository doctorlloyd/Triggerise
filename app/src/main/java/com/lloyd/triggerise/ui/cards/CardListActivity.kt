package com.lloyd.triggerise.ui.cards

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.lloyd.triggerise.ITEM_KEY
import com.lloyd.triggerise.R
import com.lloyd.triggerise.data.Resource
import com.lloyd.triggerise.data.models.cards.Card
import com.lloyd.triggerise.data.models.cards.Cards
import com.lloyd.triggerise.databinding.HomeActivityBinding
import com.lloyd.triggerise.ui.base.BaseActivity
import com.lloyd.triggerise.ui.cardDetails.DetailsActivity
import com.lloyd.triggerise.ui.cards.adapter.CardAdapter
import com.lloyd.triggerise.utils.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CardListActivity : BaseActivity() {
    private lateinit var binding: HomeActivityBinding

    private val cardListViewModel: CardListViewModel by viewModels()
    private lateinit var cardAdapter: CardAdapter

    override fun initViewBinding() {
        binding = HomeActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.card)
        val layoutManager = LinearLayoutManager(this)
        binding.rvCardList.layoutManager = layoutManager
        binding.rvCardList.setHasFixedSize(true)
        cardListViewModel.getCards()
    }

    private fun bindListData(list: Cards) {
        if (list.cards.isNotEmpty()) {
            cardAdapter = CardAdapter(cardListViewModel, list.cards)
            binding.rvCardList.adapter = cardAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun navigateToDetailsScreen(navigateEvent: SingleEvent<Card>) {
        navigateEvent.getContentIfNotHandled()?.let {
            val nextScreenIntent = Intent(this, DetailsActivity::class.java).apply {
                putExtra(ITEM_KEY, it)
            }
            startActivity(nextScreenIntent)
        }
    }

    private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
        binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }

    private fun showDataView(show: Boolean) {
        binding.tvNoData.visibility = if (show) GONE else VISIBLE
        binding.rvCardList.visibility = if (show) VISIBLE else GONE
        binding.pbLoading.toGone()
    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
        binding.tvNoData.toGone()
        binding.rvCardList.toGone()
    }

    private fun handleCardList(status: Resource<Cards>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data?.let { bindListData(list = it) }
            is Resource.DataError -> {
                showDataView(false)
                status.errorCode?.let { cardListViewModel.showToastMessage(it) }
            }
        }
    }

    override fun observeViewModel() {
        observe(cardListViewModel.cardsLiveData, ::handleCardList)
        observeEvent(cardListViewModel.openCardDetails, ::navigateToDetailsScreen)
        observeSnackBarMessages(cardListViewModel.showSnackBar)
        observeToast(cardListViewModel.showToast)

    }
}
