package com.vobbla16.mesh.ui.screens.homeworkScreen

import androidx.lifecycle.viewModelScope
import com.vobbla16.mesh.common.localDateTimeNow
import com.vobbla16.mesh.domain.use_case.GetHomeworkUseCase
import com.vobbla16.mesh.domain.use_case.GetHomeworkWithLessonUseCase
import com.vobbla16.mesh.ui.BaseViewModel
import com.vobbla16.mesh.ui.genericHolder.GenericHolder
import com.vobbla16.mesh.ui.genericHolder.LoadingState
import com.vobbla16.mesh.ui.genericHolder.processDataFromUseCase
import kotlinx.coroutines.launch

class HomeworkScreenViewModel(
    private val getHomeworkWithLessonUseCase: GetHomeworkWithLessonUseCase
) : BaseViewModel<HomeworkScreenState, HomeworkScreenAction>() {
    override fun setInitialState() = HomeworkScreenState(
        homeworkData = GenericHolder()
    )

    init {
        getHomework(false)
    }

    fun refreshData() = getHomework(true)
    fun retryOnError() = getHomework(false)

    private fun getHomework(refresh: Boolean) = viewModelScope.launch {
        processDataFromUseCase(
            useCase = getHomeworkWithLessonUseCase(localDateTimeNow().date),
            resultReducer = { this },
            loadingType = LoadingState.fromBool(refresh),
            onNotLoggedIn = { setAction { HomeworkScreenAction.NavigateToLoginScreen } },
            newStateApplier = { setState { copy(homeworkData = it) } }
        )
    }
}