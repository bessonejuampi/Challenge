package com.example.challengessrflextech.ui.view

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.SimpleTarget
import com.example.challengessrflextech.R
import com.example.challengessrflextech.data.models.Cat
import com.example.challengessrflextech.databinding.DetailsAcrivityBinding
import com.example.challengessrflextech.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.bumptech.glide.request.transition.Transition
import com.example.challengessrflextech.ui.viewModels.DetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: DetailsAcrivityBinding

    var viewBottomSheet: View? = null

    private val viewModel: DetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailsAcrivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewBottomSheet = layoutInflater.inflate(R.layout.bottom_sheet_details, null, false)

        var cat: Cat? = null
        val bundle = intent.getBundleExtra(Constants.KEY_BUNDLE_MAIN_TO_DETAILS)
        if (bundle != null && bundle.containsKey(Constants.KEY_BUNDLE_MAIN_TO_DETAILS)) {
            cat = bundle.getParcelable(Constants.KEY_BUNDLE_MAIN_TO_DETAILS)
        }

        setCat(cat)
        setDetails(cat)


        val floatingButton: FloatingActionButton = findViewById(R.id.btDetails)

        floatingButton.setOnClickListener { view ->
            setBottomSheet(cat!!)
        }

    }

    private fun setCat(cat: Cat?) {
        if (cat != null) {
            Glide.with(this)
                .load(cat.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .into(binding.ivCat)
        }
    }

    private fun setDetails(cat: Cat?){
        if (cat != null && !cat.breeds.isNullOrEmpty()){
            cat.breeds.forEach { breed ->
                breed.name?.let {  viewBottomSheet?.findViewById<TextView>(R.id.tvName)?.text = "Breed $it" }
                breed.description?.let {  viewBottomSheet?.findViewById<TextView>(R.id.tvDescription)?.text = "Description: $it" }
                breed.adaptability?.let {  viewBottomSheet?.findViewById<RatingBar>(R.id.rbAdaptability)?.rating = it.toFloat() }
                breed.dogFriendly?.let {  viewBottomSheet?.findViewById<RatingBar>(R.id.rbDogFriendly)?.rating = it.toFloat() }
                breed.intelligence?.let {  viewBottomSheet?.findViewById<RatingBar>(R.id.rbInteligence)?.rating = it.toFloat() }
                breed.socialNeeds?.let {  viewBottomSheet?.findViewById<RatingBar>(R.id.rbSocialNeeds)?.rating = it.toFloat() }
            }
        }else{
            viewBottomSheet?.findViewById<TextView>(R.id.tvName)?.text = getString(R.string.text_empty_detalis)
            viewBottomSheet?.findViewById<TextView>(R.id.tvDescription)?.text = ""
            viewBottomSheet?.findViewById<LinearLayout>(R.id.llRaitingBar)?.visibility = View.GONE

        }
    }

    private fun setBottomSheet(cat: Cat) {
        val dialog = BottomSheetDialog(this)
        dialog.setCancelable(false)

        viewBottomSheet?.let { bottomSheet ->

            if (bottomSheet.parent != null) {
                (bottomSheet.parent as ViewGroup).removeView(bottomSheet)
            }

            dialog.setContentView(bottomSheet)
        }
        dialog.show()

        viewBottomSheet?.findViewById<ImageView>(R.id.ivClose)?.setOnClickListener {
            dialog.dismiss()
        }

        viewBottomSheet?.findViewById<Button>(R.id.btDownload)?.setOnClickListener {
            Glide.with(this)
                .asBitmap()
                .load(cat.url)
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        viewModel.saveImageToGallery(resource)
                    }
                })
        }
    }

}