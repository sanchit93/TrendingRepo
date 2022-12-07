package com.example.trendingrepo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.RepoDataDetails
import com.example.trendingrepo.R
import com.example.trendingrepo.databinding.ItemRepoBinding

/**
 * adapter class for repo items
 */
class RepoAdapter(val data : ObservableArrayList<RepoDataDetails>): RecyclerView.Adapter<RepoAdapter.MainViewHolder>() {
    lateinit var callbackListener: (item: Any, position:Int) -> Unit

    init {
        data.addOnListChangedCallback(object :ObservableList.OnListChangedCallback<ObservableList<RepoDataDetails>>(){
            override fun onChanged(sender: ObservableList<RepoDataDetails>?) {
                notifyDataSetChanged()
            }

            override fun onItemRangeRemoved(
                sender: ObservableList<RepoDataDetails>?,
                positionStart: Int,
                itemCount: Int,
            ) {
                notifyItemRangeRemoved(positionStart, itemCount)
            }

            override fun onItemRangeMoved(
                sender: ObservableList<RepoDataDetails>?,
                fromPosition: Int,
                toPosition: Int,
                itemCount: Int,
            ) {
                notifyDataSetChanged()
            }

            override fun onItemRangeInserted(
                sender: ObservableList<RepoDataDetails>?,
                positionStart: Int,
                itemCount: Int,
            ) {
                notifyItemRangeInserted(positionStart,itemCount)
            }

            override fun onItemRangeChanged(
                sender: ObservableList<RepoDataDetails>?,
                positionStart: Int,
                itemCount: Int,
            ) {
                notifyItemRangeChanged(positionStart, itemCount)
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
                R.layout.item_repo, parent, false),parent.context)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(data[position],position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    /**
     *  This itemViewHolder class hold the item state for this adapter
     *   and handle the view click event.
     */
    inner class MainViewHolder(val binding:ItemRepoBinding, val context: Context):RecyclerView.ViewHolder(binding.root) {
        /**
         * this is to bind the view
         * with the data binding
         */
        fun bind(
            data: RepoDataDetails,
            position: Int
        ) {
            binding.tvTitle.text = if (data.description?.isNullOrEmpty()==true) data.owner?.login else data.description
            Glide.with(context)
                .load(data.owner?.avatar_url)
                .placeholder(R.drawable.ic_account_circle_24)
                .into(binding.ivImage)
            binding.tvName.text = data.owner?.login?:""
            binding.tvDescription.text = data.url
            binding.cdRepo.strokeColor =  ContextCompat.getColor(context,if (data.isSelected==true) R.color.design_default_color_secondary else R.color.white)
            binding.cdRepo.setOnClickListener {
                data.isSelected = !data.isSelected
                callbackListener.invoke(data,position)
            }
            binding.tvStars.text = "Stars: ${data.stars}"
        }
    }
}


