package com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.details


import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.data.mediastore.ImageMediaStore
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import com.w4ereT1ckRtB1tch.moviefan.utils.SingleLiveEvent
import kotlinx.coroutines.*
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
class FilmDetailsViewModel @Inject constructor(
    private val context: Context,
    private val imageMediaStore: ImageMediaStore
) :
    ViewModel() {

    private val film: MutableLiveData<Film> = MutableLiveData()
    private val isVisible: MutableLiveData<Boolean> = MutableLiveData()
    private val permission: SingleLiveEvent<Unit> = SingleLiveEvent()
    private val loadComplete: SingleLiveEvent<Unit> = SingleLiveEvent()
    private val isLoadImage: MutableLiveData<Boolean> = MutableLiveData()

    fun getFilm(): LiveData<Film> = film

    fun setFilm(filmValue: Film?) {
        film.value = filmValue
    }

    fun isVisible(): LiveData<Boolean> = isVisible

    fun getPermission(): LiveData<Unit> = permission

    fun getLoadComplete(): LiveData<Unit> = loadComplete

    fun isLoadImage(): LiveData<Boolean> = isLoadImage

    fun onClickedDetails() {
        if (isVisible.value == null) isVisible.value = false
        val visible = isVisible.value?.let { !it }
        Log.d("TAG", "onClickedDetails: ok -> $visible")
        isVisible.value = visible
    }

    fun onClickedFavorites() {
        val filmValue = film.value?.let { it.copy(isFavorites = !it.isFavorites) }
        film.value = filmValue
        Log.d("TAG", "onClickedFavorites: ${film.value}")
    }

    fun onClickedShare() {
        film.value?.let {
            val message = context.getString(
                R.string.intent_message,
                it.title,
                it.description,
                it.year?.year ?: context.getString(R.string.not_announced),
                it.rating.toString()
            )
            val intent = Intent().apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, message)
                type = "text/plain"
            }
            context.startActivity(intent)
        }
    }

    fun onClickedDownloads() {
        downloadImage()
    }

    fun toImageGallery() {
        val intent = Intent
            .makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_GALLERY)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    private val downloadScope = CoroutineScope(Dispatchers.IO)

    private fun downloadImage() {
        if (isPermissionGranted()) {
            permission.value = Unit
            return
        }
        MainScope().launch {
            isLoadImage.value = true
            val downloadsJob = downloadScope.async {
                imageMediaStore.downloadImageUrl(film.value?.backdrop)
            }
            imageMediaStore.saveImageToGallery(downloadsJob.await(), film.value)
            loadComplete.value = Unit
            isLoadImage.value = false
        }
    }

    private fun isPermissionGranted(): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED
    }
}