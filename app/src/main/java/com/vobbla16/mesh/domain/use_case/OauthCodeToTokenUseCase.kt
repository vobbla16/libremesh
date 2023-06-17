package com.vobbla16.mesh.domain.use_case

import com.vobbla16.mesh.common.Constants
import com.vobbla16.mesh.common.Resource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.cookie
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OauthCodeToTokenUseCase(private val httpClient: HttpClient) {

    suspend operator fun invoke(code: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading)

        val aupdRequest = httpClient.get(Constants.OAUTH_CODE_TO_AUPD_URL) {
            url {
                parameters.append("code", code)
            }
        }

        val setCookieHeader = aupdRequest.headers["set-cookie"]
        if (setCookieHeader == null) {
            emit(Resource.Error("Header doesn't contain new cookie"))
            return@flow
        }

        val aupdToken = setCookieHeader.split("=")[1].split(";")[0]

        val tokenRequest = httpClient.get(Constants.AUPD_TO_TOKEN_URL) {
            cookie("aupd_token", aupdToken)
        }

        val token = tokenRequest.body<String>()
        emit(Resource.Success(token))
    }
}