package hi.iwansyy.appchatfirebase.model

import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @field:SerializedName("canonical_ids")
    val canonicalIds: Int,
    val success: Int,
    val failure: Int,
    val result: List<ResultsItem>,
    @field:SerializedName("multicast_id")
    val multiCastId: Long
)