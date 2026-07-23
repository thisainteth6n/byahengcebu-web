package com.byahengcebu.mobile.features.issue.repository

import com.byahengcebu.mobile.features.issue.model.Issue
import com.byahengcebu.mobile.shared.api.ApiClient
import retrofit2.Response

class IssueRepository {

    suspend fun submitIssue(
        issue: Issue
    ): Response<Issue> {

        return ApiClient.api.submitIssue(issue)

    }

    suspend fun getDriverIssues(
        driverName: String
    ): Response<List<Issue>> {

        return ApiClient.api.getDriverIssues(driverName)

    }

}