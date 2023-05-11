package com.example.challengessrflextech.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.challengessrflextech.R
import com.example.challengessrflextech.data.models.Cat
import com.example.challengessrflextech.databinding.ItemCatsBinding

class CatsAdapter(
    private val listCat:List<Cat>,
    var onItemClicked: (item: Cat) -> Unit = {}
) :RecyclerView.Adapter<CatsAdapter.CatsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val binding = ItemCatsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatsViewHolder(parent.context ,binding)
    }

    override fun getItemCount(): Int {
        return listCat.size
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
       holder.bind(listCat[position])
    }
    inner class CatsViewHolder(
        private val context:Context,
         itemView:ItemCatsBinding
    ):RecyclerView.ViewHolder(itemView.root) {

        fun bind(cat:Cat){
            Glide.with(context)
                .load(cat.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .into((itemView.findViewById<ImageView>(R.id.ivCat)))

            itemView.findViewById<ImageView>(R.id.ivCat).setOnClickListener {
                onItemClicked.invoke(cat)
            }
        }



    }

}