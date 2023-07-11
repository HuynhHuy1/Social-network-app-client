package com.example.socialnetwork.model

data class ProfileModel(
     var id : Int = 0,
     var email : String? =null,
     var name : String? = null,
     var avatar : String? = null,
     var follower : Int = 0,
     var following : Int = 0,
     var listImage : List<String>? = null,
)