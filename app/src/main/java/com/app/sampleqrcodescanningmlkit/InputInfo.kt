package com.app.sampleqrcodescanningmlkit

import android.graphics.Bitmap
import com.app.sampleqrcodescanningmlkit.utils.Utils
import com.app.sampleqrcodescanningmlkit.utils.camera.FrameMetadata
import java.nio.ByteBuffer

interface InputInfo {
    fun getBitmap(): Bitmap
}

class CameraInputInfo(private val frameByteBuffer: ByteBuffer, private val frameMetadata: FrameMetadata) : InputInfo {

    private var bitmap: Bitmap? = null

    @Synchronized
    override fun getBitmap(): Bitmap {
        return bitmap ?: let {
            bitmap = Utils.convertToBitmap(
                frameByteBuffer, frameMetadata.width, frameMetadata.height, frameMetadata.rotation
            )
            bitmap!!
        }
    }
}

class BitmapInputInfo(private val bitmap: Bitmap) : InputInfo {
    override fun getBitmap(): Bitmap {
        return bitmap
    }
}