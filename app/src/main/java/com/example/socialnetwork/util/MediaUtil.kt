import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.File

fun convertBase64ToImageBitmap(base64String: String): ImageBitmap? {
    val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
    val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    return bitmap?.asImageBitmap()
}

fun convertUriToImageFile(uri: Uri?,context : Context): ByteArray? {
    if(uri != null){
        val contentResolver = context.contentResolver
        val inputStream = contentResolver.openInputStream(uri)
        val imageBytes = inputStream?.readBytes()
        inputStream?.close()
        return imageBytes
    }
    return null
}