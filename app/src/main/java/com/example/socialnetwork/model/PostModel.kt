package com.example.socialnetwork.model

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.example.socialnetwork.R
import java.sql.Timestamp
import java.util.Base64

data class PostModel(
    val postId : Int = 0,
    val userName : String? = null,
    val content: String = "",
    val image: String? = null,
    val commentCount : Int = 0,
    val likeCount : Int = 0,
    val timeCreate : Timestamp,
    val avatarBase64: String? = null,
    val userID : Int = 0,
    val timeFormatString : String = "",
    val stateLike : Boolean )  : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString() ?: "",
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        Timestamp(parcel.readLong()),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(postId)
        parcel.writeString(userName)
        parcel.writeString(content)
        parcel.writeString(image)
        parcel.writeInt(commentCount)
        parcel.writeInt(likeCount)
        parcel.writeLong(timeCreate.time)
        parcel.writeString(avatarBase64)
        parcel.writeInt(userID)
        parcel.writeString(timeFormatString)
        parcel.writeByte(if (stateLike) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PostModel> {
        override fun createFromParcel(parcel: Parcel): PostModel {
            return PostModel(parcel)
        }

        override fun newArray(size: Int): Array<PostModel?> {
            return arrayOfNulls(size)
        }
    }
}
