package dog.snow.androidrecruittest.repository.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RawPhoto(
    var id: Int?,
    var albumId: Int?,
    var title: String?,
    var url: String?,
    var thumbnailUrl: String?
) : Parcelable {
    constructor() : this(null,null,null,null,null)
}