package com.vobbla16.mesh.data.remote.dto.schedule


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Lesson(
    @SerialName("absence_reason_id")
    val absenceReasonId: Long?, // null
    @SerialName("ae_fake_group")
    val aeFakeGroup: Boolean? = false, // false
    @SerialName("bell_id")
    val bellId: Long?, // 16104756
    @SerialName("course_lesson_type")
    val courseLessonType: String?, // null
    @SerialName("esz_field_id")
    val eszFieldId: Int?, // 1
    @SerialName("evaluation")
    val evaluation: String?, // null
    @SerialName("homework")
    val homework: String, // не задано
    @SerialName("homework_count")
    val homeworkCount: HomeworkCount,
    @SerialName("is_cancelled")
    val isCancelled: Boolean?, // false
    @SerialName("is_missed_lesson")
    val isMissedLesson: Boolean, // false
    @SerialName("is_virtual")
    val isVirtual: Boolean, // false
    @SerialName("lesson_education_type")
    val lessonEducationType: String, // OO
    @SerialName("lesson_type")
    val lessonType: String, // NORMAL
    @SerialName("link_types")
    val linkTypes: List<String>? = null,
    @SerialName("marks")
    val marks: List<Mark>,
    @SerialName("materials_count")
    val materialsCount: MaterialsCount? = null,
    @SerialName("replaced")
    val replaced: Boolean, // false
    @SerialName("schedule_item_id")
    val scheduleItemId: Long, // 378977336
    @SerialName("subject_id")
    val subjectId: Long, // 33623666
    @SerialName("subject_name")
    val subjectName: String, // Немецкий язык
    @SerialName("teacher")
    val teacher: Teacher,
    @SerialName("nonattendance_reason_id")
    val nonAttendanceReasonId: Long? = null,
    @SerialName("disease_status_type")
    val diseaseStatusType: String? = null,
)