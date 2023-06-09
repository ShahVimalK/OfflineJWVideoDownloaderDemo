package com.example.offlinejwvideodownloaderdemoapplication.model

import com.google.gson.annotations.SerializedName

data class Beans (
    @SerializedName("courseId"           ) var courseId           : Int,
    @SerializedName("videoId"            ) var videoId            : String?  = null,
    @SerializedName("courseTitle"        ) var courseTitle        : String?  = null,
    @SerializedName("courseSlug"         ) var courseSlug         : String?  = null,
    @SerializedName("courseThumbnail"    ) var courseThumbnail    : String?  = null,
    @SerializedName("courseTypeName"     ) var courseTypeName     : String?  = null,
    @SerializedName("courseTypeId"       ) var courseTypeId       : Int?     = null,
    @SerializedName("lessonId"           ) var lessonId           : Int?     = null,
    @SerializedName("assignedLessonId"   ) var assignedLessonId   : Int?     = null,
    @SerializedName("lessonName"         ) var lessonName         : String?  = null,
    @SerializedName("lastTimestamp"      ) var lastTimestamp      : Int?     = null,
    @SerializedName("videoTotalTime"     ) var videoTotalTime     : Int?     = null,
    @SerializedName("totalWatchTime"     ) var totalWatchTime     : String?  = null,
    @SerializedName("contentType"        ) var contentType        : String?  = null,
    @SerializedName("languageId"         ) var languageId         : Int?     = null,
    @SerializedName("viewCount"          ) var viewCount          : Int?     = null,
    @SerializedName("isCompleted"        ) var isCompleted        : Boolean? = null,
    @SerializedName("embedCode"          ) var embedCode          : String?  = null,
    @SerializedName("videoTypeId"        ) var videoTypeId        : Int?     = null,
    @SerializedName("elementContentType" ) var elementContentType : Int?     = null,
    @SerializedName("testSeriesId"       ) var testSeriesId       : Int?     = null,
    @SerializedName("cruxStatus"         ) var cruxStatus         : Int?     = null,
    @SerializedName("pptStatus"          ) var pptStatus          : Int?     = null,
    @SerializedName("snippetStatus"      ) var snippetStatus      : Int?     = null,
    @SerializedName("quizStatus"         ) var quizStatus         : Int?     = null,
    @SerializedName("resourceStatus"     ) var resourceStatus     : Int?     = null,
    @SerializedName("s3Enabled"          ) var s3Enabled          : Int?     = null,
    @SerializedName("s3Url"              ) var s3Url              : String?  = null,
    @SerializedName("upcomingStatus"     ) var upcomingStatus     : Int?     = null,
    @SerializedName("isDrmEnabled"       ) var isDrmEnabled       : Int?     = null,
    @SerializedName("uuid"               ) var uuid               : String?  = null,
    @SerializedName("mediaId"            ) var mediaId            : String,
    @SerializedName("drmProvider"        ) var drmProvider        : String?  = null,
    @SerializedName("freeCourse"         ) var freeCourse         : Int?     = null,
    @SerializedName("orderCourseType"    ) var orderCourseType    : Int?     = null,
    @SerializedName("upcomingDate"       ) var upcomingDate       : String?  = null,
    @SerializedName("date"               ) var date               : String?  = null,
    @SerializedName("lastVisited"        ) var lastVisited        : String?  = null
)
