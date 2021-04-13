package com.lloyd.triggerise.ui.sets

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.lloyd.triggerise.ITEM_KEY
import com.lloyd.triggerise.R
import com.lloyd.triggerise.data.Resource
import com.lloyd.triggerise.data.models.sets.Set
import com.lloyd.triggerise.data.models.sets.Sets
import com.lloyd.triggerise.databinding.SetActivityBinding
import com.lloyd.triggerise.ui.base.BaseActivity
import com.lloyd.triggerise.ui.cards.CardListActivity
import com.lloyd.triggerise.ui.sets.adapter.SetAdapter
import com.lloyd.triggerise.ui.setsDetails.SetDetailsActivity
import com.lloyd.triggerise.utils.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SetListActivity : BaseActivity() {
    private lateinit var binding: SetActivityBinding
    private var menu: Menu? = null
    private val setListViewModel: SetListViewModel by viewModels()
    private lateinit var setAdapter: SetAdapter

    override fun initViewBinding() {
        binding = SetActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.set)
        val layoutManager = LinearLayoutManager(this)
        binding.rvSetList.layoutManager = layoutManager
        binding.rvSetList.setHasFixedSize(true)
        setListViewModel.getSets()
    }

    private fun bindListData(list: Sets) {
        if (list.sets.isNotEmpty()) {
            setAdapter = SetAdapter(setListViewModel, list.sets)
            binding.rvSetList.adapter = setAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun navigateToDetailsScreen(navigateEvent: SingleEvent<Set>) {
        navigateEvent.getContentIfNotHandled()?.let {
            val nextScreenIntent = Intent(this, SetDetailsActivity::class.java).apply {
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
        binding.rvSetList.visibility = if (show) VISIBLE else GONE
        binding.pbLoading.toGone()
    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
        binding.tvNoData.toGone()
        binding.rvSetList.toGone()
    }

    private fun handleSetList(status: Resource<Sets>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data?.let { bindListData(list = it) }
            is Resource.DataError -> {
                showDataView(false)
                status.errorCode?.let { setListViewModel.showToastMessage(it) }
            }
        }
    }

    override fun observeViewModel() {
        observe(setListViewModel.setsLiveData, ::handleSetList)
        observeEvent(setListViewModel.openSetDetails, ::navigateToDetailsScreen)
        observeSnackBarMessages(setListViewModel.showSnackBar)
        observeToast(setListViewModel.showToast)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.get_cards -> startActivity(Intent(this,CardListActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}
