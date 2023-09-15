package com.startup.tugas_5_eureka.utils

import java.net.URL

object ImageLinkChecker {
    /***
     *   this method is to get the image file extention
     *   @param urlString to pass the image link or url
     *   @author Andi
     *   @since September 15th, 2023
     * */
    private fun getFileExtension(urlString: String): String? {
        try {
            val url = URL(urlString)
            val path = url.path
            val lastDotIndex = path.lastIndexOf('.')
            if (lastDotIndex >= 0) {
                return path.substring(lastDotIndex + 1)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /***
     *   this method is to check the image link extention.
     *   Available image extention:
     *   1. .jpg
     *   2. .jpeg
     *   3. .png
     *   4. .gif
     *   @param imageUrl to get the image URL
     *   @author Andi
     *   @since September 15th, 2023
     * */
     fun checkImageLinkExtention(imageUrl : String) : Boolean {
        val fileExtension = getFileExtension(imageUrl)
        return if (fileExtension != null) {
            fileExtension.equals("jpg", ignoreCase = true) || fileExtension.equals("jpeg", ignoreCase = true) || fileExtension.equals("png", ignoreCase = true) || fileExtension.equals("gif", ignoreCase = true)
        } else {
            false
        }
    }
}