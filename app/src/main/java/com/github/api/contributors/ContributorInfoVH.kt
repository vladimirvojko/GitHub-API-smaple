package com.github.api.contributors

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.api.R

class ContributorInfoVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val avatarIv: ImageView = itemView.findViewById(R.id.avatarIv)
    val loginTV: TextView = itemView.findViewById(R.id.login_tv)
    val contributionsTV: TextView = itemView.findViewById(R.id.contributions_tv)
    val locationButton: View = itemView.findViewById(R.id.location_button)
}