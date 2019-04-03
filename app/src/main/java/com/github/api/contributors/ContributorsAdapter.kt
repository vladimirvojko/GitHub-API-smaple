package com.github.api.contributors

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.api.R
import com.github.api.common.extensions.layoutInflater
import com.github.api.common.extensions.weak
import com.github.api.model.GHContributor

class ContributorsAdapter(listener: OnLocationClickListener) : RecyclerView.Adapter<ContributorInfoVH>() {
    private val listener = listener.weak()

    var items = emptyList<GHContributor>()

    override fun getItemCount() = items.size

    fun getItem(position: Int) = items[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ContributorInfoVH(
        parent.layoutInflater.inflate(R.layout.view_contributor_info, parent, false)
    ).apply {
        locationButton.setOnClickListener {
            listener.get()?.onLocationClicked(this)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ContributorInfoVH, position: Int) {
        val item = getItem(position)

        val contributionsText =
            holder.itemView.context.getString(R.string.format_contributions_count, item.contributions)

        Glide.with(holder.avatarIv)
            .load(item.avatar)
            .into(holder.avatarIv)

        holder.loginTV.text = item.login
        holder.contributionsTV.text = contributionsText
        holder.locationButton.isVisible = item.location != null
    }

    interface OnLocationClickListener {
        fun onLocationClicked(holder: ContributorInfoVH)
    }
}