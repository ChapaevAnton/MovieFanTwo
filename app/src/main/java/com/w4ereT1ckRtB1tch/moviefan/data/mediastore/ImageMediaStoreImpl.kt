package com.w4ereT1ckRtB1tch.moviefan.data.mediastore

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.provider.MediaStore.Images.Media
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import java.net.URL
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class ImageMediaStoreImpl @Inject constructor(val context: Context) : ImageMediaStore {

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun downloadImageUrl(imageUrl: String?): Bitmap {
        return suspendCoroutine {
            val url = URL(imageUrl)
            url.openConnection().getInputStream().use { u ->
                val bitmap = BitmapFactory.decodeStream(u)
                it.resume(bitmap)
            }
        }
    }

    override fun saveImageToGallery(image: Bitmap, film: Film?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val contentValues = ContentValues().apply {
                put(Media.TITLE, film?.title.handleSingleQuote())
                put(Media.DISPLAY_NAME, film?.title.handleSingleQuote())
                put(Media.MIME_TYPE, "image/jpeg")
                put(Media.DATE_ADDED, System.currentTimeMillis() / 1000)
                put(Media.DATE_TAKEN, System.currentTimeMillis())
                put(
                    Media.RELATIVE_PATH,
                    "Pictures/${context.getString(R.string.app_name).replace(" ", "")}"
                )
            }
            val contentResolver = context.contentResolver
            val uri = contentResolver.insert(
                Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            uri?.let {
                contentResolver.openOutputStream(it).use { outputStream ->
                    image.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                }
            }
        } else {
            @Suppress("DEPRECATION")
            Media.insertImage(
                context.contentResolver,
                image,
                film?.title.handleSingleQuote(),
                film?.description.handleSingleQuote()
            )
        }
    }

    private fun String?.handleSingleQuote(): String? {
        return this?.replace("'", "")
    }
}