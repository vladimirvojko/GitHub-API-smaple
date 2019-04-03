package com.github.api.contributors

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.api.R
import com.github.api.common.actviity.BaseActivity
import com.github.api.model.GHContributor
import kotlinx.android.synthetic.main.activity_contributors.*
import toothpick.Scope
import javax.inject.Inject

class ContributorsActivity : BaseActivity(), ContributorsContract.View, ContributorsAdapter.OnLocationClickListener {
    override val layoutResId get() = R.layout.activity_contributors

    @Inject
    lateinit var presenter: ContributorsContract.Presenter

    private lateinit var adapter: ContributorsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = ContributorsAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        presenter.start()
    }

    override fun installModules(scope: Scope) {
        super.installModules(scope)
        scope.installModules(ContributorsModule(this))
    }

    override fun showMessage(text: String) = Toast.makeText(this, text, Toast.LENGTH_LONG).show()

    override fun showProgress() {
        progressBar.isVisible = true
        recyclerView.isVisible = false
    }

    override fun hideProgress() {
        progressBar.isVisible = false
    }

    override fun showContributors(contributors: List<GHContributor>) {
        recyclerView.isVisible = true

        adapter.items = contributors
        adapter.notifyDataSetChanged()
    }

    override fun onLocationClicked(holder: ContributorInfoVH) =
        presenter.showLocation(adapter.getItem(holder.adapterPosition))
}
