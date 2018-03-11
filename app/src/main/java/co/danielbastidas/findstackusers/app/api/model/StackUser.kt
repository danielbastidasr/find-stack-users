package co.danielbastidas.findstackusers.app.api.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.time.Instant
import java.util.*

/*
"badge_counts": {
                "bronze": 0,
                "silver": 0,
                "gold": 0
            },
            "account_id": 11823809,
            "is_employee": false,
            "last_access_date": 1510115231,
            "reputation_change_year": 0,
            "reputation_change_quarter": 0,
            "reputation_change_month": 0,
            "reputation_change_week": 0,
            "reputation_change_day": 0,
            "reputation": 1,
            "creation_date": 1506036606,
            "user_type": "registered",
            "user_id": 8652210,
            "link": "https://stackoverflow.com/users/8652210/b1soft-corp-daniel-garcia",
            "profile_image": "https://www.gravatar.com/avatar/f0ad16cce747a16bb734c2847ce47a79?s=128&d=identicon&r=PG&f=1",
            "display_name": " B1SOFT Corp Daniel Garcia"

 */

data class StackUser(
        @SerializedName("badge_counts")
        val badgeCounts:Badges,
        @SerializedName("is_employee")
        val isEmployee:Boolean,
        val age:Int,
        val reputation:Int,
        @SerializedName("creation_date")
        val creationDate:Date,
        @SerializedName("user_id")
        val userId:Int,
        val location:String,
        @SerializedName("profile_image")
        val profileImage:String,
        @SerializedName("display_name")
        val displayName:String) : Parcelable {


    constructor(parcel: Parcel) : this(
            parcel.readParcelable(Badges::class.java.classLoader),
            parcel.readByte() != 0.toByte(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readSerializable() as Date,
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    data class Badges(val bronze:Int, val silver:Int, val gold:Int) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readInt(),
                parcel.readInt()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(bronze)
            parcel.writeInt(silver)
            parcel.writeInt(gold)
        }

        override fun describeContents(): Int {
            return 0
        }

        override fun toString(): String {
            return "Bronze: $bronze\nSilver: $silver\nGold: $gold"
        }

        companion object CREATOR : Parcelable.Creator<Badges> {
            override fun createFromParcel(parcel: Parcel): Badges {
                return Badges(parcel)
            }

            override fun newArray(size: Int): Array<Badges?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(badgeCounts, flags)
        parcel.writeByte(if (isEmployee) 1 else 0)
        parcel.writeInt(age)
        parcel.writeInt(reputation)
        parcel.writeSerializable(creationDate)
        parcel.writeInt(userId)
        if(location.isNullOrBlank())
            parcel.writeString("No location available")
        else parcel.writeString(location)
        parcel.writeString(profileImage)
        parcel.writeString(displayName)
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

