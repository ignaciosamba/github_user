package com.sambataro.ignacio.anwbassesment.data.network.response


import androidx.annotation.NonNull
import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.sambataro.ignacio.anwbassesment.data.db.entity.Owner



@Entity(tableName = "current_user")
data class CurrentUserReposResponse(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @Embedded(prefix = "owner_")
    val owner: Owner,
    val name: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @ColumnInfo(defaultValue = "No description")
    @NonNull
    val description : String,
    @ColumnInfo(defaultValue = "")
    @NonNull
    @SerializedName("svn_url")
    val svnUrl : String
//    val url: String
//    @SerializedName("avatar_url")
//    val avatarUrl: String
//    val blog: String,
//    val company: String,
//    @SerializedName("created_at")
//    val createdAt: String,
//    @SerializedName("events_url")
//    val eventsUrl: String,
//    val followers: Int,
//    @SerializedName("followers_url")
//    val followersUrl: String,
//    val following: Int,
//    @SerializedName("following_url")
//    val followingUrl: String,
//    @SerializedName("gists_url")
//    val gistsUrl: String,
//    @SerializedName("gravatar_id")
//    val gravatarId: String,
//    @SerializedName("html_url")
//    val htmlUrl: String,
//    val location: String,
//    val login: String,
//    @SerializedName("node_id")
//    val nodeId: String,
//    @SerializedName("organizations_url")
//    val organizationsUrl: String,
//    @SerializedName("public_gists")
//    val publicGists: Int,
//    @SerializedName("public_repos")
//    val publicRepos: Int,
//    @SerializedName("received_events_url")
//    val receivedEventsUrl: String,
//    @SerializedName("repos_url")
//    val reposUrl: String,
//    @SerializedName("site_admin")
//    val siteAdmin: Boolean,
//    @SerializedName("starred_url")
//    val starredUrl: String,
//    @SerializedName("subscriptions_url")
//    val subscriptionsUrl: String,
//    val type: String,

)