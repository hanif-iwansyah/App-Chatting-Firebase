package hi.iwansyy.appchatfirebase.model

import com.google.gson.annotations.SerializedName

data class ResultsItem(
    @field:SerializedName("message_id")
    val messageId: String
)
