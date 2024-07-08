package com.example.BrunComidas.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.BrunComidas.R

import com.example.BrunComidas.model.login.Photo
import com.example.BrunComidas.ui.main.FullScreenImageActivity


class PhotoAdapter(private val photos: List<Photo>) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    class PhotoViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val imageView = LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false) as ImageView
        return PhotoViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]
        Glide.with(holder.imageView.context).load(photo.url).into(holder.imageView)

        holder.imageView.setOnClickListener {
            val intent = Intent(it.context, FullScreenImageActivity::class.java)
            intent.putExtra("image_url", photo.url)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount() = photos.size
}