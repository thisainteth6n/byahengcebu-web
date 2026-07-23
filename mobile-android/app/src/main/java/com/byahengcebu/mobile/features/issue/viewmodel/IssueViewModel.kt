package com.byahengcebu.mobile.features.issue.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.byahengcebu.mobile.features.issue.model.Issue
import com.byahengcebu.mobile.features.issue.repository.IssueRepository
import com.byahengcebu.mobile.shared.session.SessionManager
import kotlinx.coroutines.launch

class IssueViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = IssueRepository()

    private val session =
        SessionManager(application)

    var issues by mutableStateOf<List<Issue>>(emptyList())
        private set

    var loading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set

    fun loadIssues() {

        loading = true

        viewModelScope.launch {

            try {

                val driverName = session.getFullname()

                val response =
                    repository.getDriverIssues(driverName)

                if (response.isSuccessful) {

                    issues = response.body() ?: emptyList()

                } else {

                    errorMessage =
                        "Unable to load issues."

                }

            } catch (e: Exception) {

                errorMessage =
                    e.localizedMessage ?: "Connection Error"

            }

            loading = false

        }

    }

    fun submitIssue(

        title: String,

        description: String,

        severity: String

    ) {

        viewModelScope.launch {

            try {

                val issue = Issue(

                    title = title,

                    description = description,

                    severity = severity

                )

                repository.submitIssue(issue)

                loadIssues()

            } catch (e: Exception) {

                errorMessage =
                    e.localizedMessage ?: "Connection Error"

            }

        }

    }

}