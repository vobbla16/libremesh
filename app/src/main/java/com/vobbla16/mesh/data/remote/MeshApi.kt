package com.vobbla16.mesh.data.remote

import com.vobbla16.mesh.common.Constants
import com.vobbla16.mesh.common.wrapToResourceOrLoading
import com.vobbla16.mesh.data.remote.dto.acadYears.AcademicYearsItemDto
import com.vobbla16.mesh.data.remote.dto.acadYears.toDomain
import com.vobbla16.mesh.data.remote.dto.homework.HomeworkItemDto
import com.vobbla16.mesh.data.remote.dto.homework.toDomain
import com.vobbla16.mesh.data.remote.dto.marks.MarksReportItemDto
import com.vobbla16.mesh.data.remote.dto.marks.toDomain
import com.vobbla16.mesh.data.remote.dto.profile.ProfileDto
import com.vobbla16.mesh.data.remote.dto.profile.toDomain
import com.vobbla16.mesh.data.remote.dto.schedule.ScheduleDto
import com.vobbla16.mesh.data.remote.dto.schedule.toDomain
import com.vobbla16.mesh.domain.model.acadYears.AcademicYearItemModel
import com.vobbla16.mesh.domain.model.homework.HomeworkItemsForDateModel
import com.vobbla16.mesh.domain.model.marks.MarksSubjectModel
import com.vobbla16.mesh.domain.model.profile.ProfileModel
import com.vobbla16.mesh.domain.model.schedule.ScheduleModel
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MeshApi : KoinComponent {
    private val httpClient: HttpClient by inject()
    suspend fun getSchedule(
        studentId: String,
        date: String
    ) = wrapToResourceOrLoading<ScheduleDto, ScheduleModel>({ it.toDomain() }) {
        httpClient.get(Constants.MESH_API_BASE_DOMAIN_SCHOOL + Constants.SCHEDULE_ENDPOINT) {
            url {
                parameter("student_id", studentId)
                parameter("date", date)
            }
        }
    }

    suspend fun getProfile() =
        wrapToResourceOrLoading<ProfileDto, ProfileModel>({ it.toDomain() }) {
            httpClient.get(Constants.MESH_API_BASE_DOMAIN_SCHOOL + Constants.PROFILE_ENDPOINT)
        }

    suspend fun getAcademicYears() =
        wrapToResourceOrLoading<List<AcademicYearsItemDto>, List<AcademicYearItemModel>>({ it.toDomain() }) {
            httpClient.get(Constants.MESH_API_BASE_DOMAIN_SCHOOL + Constants.ACADEMIC_YEARS_ENDPOINT)
        }

    suspend fun getMarksReport(
        studentId: String,
        academicYearId: String
    ) =
        wrapToResourceOrLoading<List<MarksReportItemDto>, List<MarksSubjectModel>>({ it.toDomain() }) {
            httpClient.get(Constants.MESH_API_BASE_DOMAIN_SCHOOL + Constants.MARKS_ENDPOINT) {
                url {
                    parameter("academic_year_id", academicYearId)
                    parameter("student_profile_id", studentId)
                }
            }
        }

    suspend fun getHomework(
        studentId: Long,
        beginDate: String,
        endDate: String
    ) =
        wrapToResourceOrLoading<List<HomeworkItemDto>, List<HomeworkItemsForDateModel>>({ it.toDomain() }) {
            httpClient.get(Constants.MESH_API_BASE_DOMAIN_DNEVNIK + "/core/api/student_homeworks") {
                url {
                    parameter("student_profile_id", studentId)
                    parameter("begin_prepared_date", beginDate)
                    parameter("end_prepared_date", endDate)
                }
            }
        }
}