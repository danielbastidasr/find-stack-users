package co.danielbastidas.findstackusers.app.api.model

import android.os.Parcel
import android.os.Parcelable


data class StackUser(val id:String,
                     val name:String,
                     val url:String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StackUser> {
        override fun createFromParcel(parcel: Parcel): StackUser {
            return StackUser(parcel)
        }

        override fun newArray(size: Int): Array<StackUser?> {
            return arrayOfNulls(size)
        }
    }
}