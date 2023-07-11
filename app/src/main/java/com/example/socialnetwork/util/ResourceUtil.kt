package com.example.socialnetwork.util

data class ResourceUtil<T> (val status : Status, val data : T?,val messsage : String?) {
    companion object{
        fun<T> success(data : T ) : ResourceUtil<T> = ResourceUtil(Status.SUCCESS,data, messsage = null)
        fun<T> error(data : T,messsage: String) : ResourceUtil<T> = ResourceUtil(Status.ERORR,data, messsage = messsage )
        fun<T> loadding(data : T ) : ResourceUtil<T> = ResourceUtil(Status.LOADING,data, messsage = null)
    }
}

enum class Status{
    LOADING,
    SUCCESS,
    ERORR
}
