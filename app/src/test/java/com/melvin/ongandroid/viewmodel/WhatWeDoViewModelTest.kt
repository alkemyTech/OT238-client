package com.melvin.ongandroid.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.melvin.ongandroid.MainCoroutinesRule
import com.melvin.ongandroid.data.ApiClient
import com.melvin.ongandroid.data.OngApi
import com.melvin.ongandroid.model.entities.us.Member
import com.melvin.ongandroid.model.entities.us.MembersResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class WhatWeDoViewModelTest {

    @RelaxedMockK
    private lateinit var api:  OngApi

    private lateinit var repository: ApiClient
    private lateinit var whatWeDoViewModel: WhatWeDoViewModel

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = ApiClient(api)
        whatWeDoViewModel = WhatWeDoViewModel(repository)
    }


    @Test
    fun `when setWhatWeDo is called response is successful and whatWeDoList isn't empty`() = runTest {
        coEvery { repository.getMembers() } returns memberResponseOnSuccess()
        whatWeDoViewModel.setWhatWeDo()
        assert(whatWeDoViewModel.whatWeDoList.value == listOfMembers())
        assert(setSuccess() == whatWeDoViewModel.status.value)
    }

    @Test
    fun `when setWhatWeDo is called response is successful and whatWeDoList is empty`() = runTest {
        coEvery { repository.getMembers() } returns memberResponseOnError()
        whatWeDoViewModel.setWhatWeDo()
        assert(whatWeDoViewModel.whatWeDoList.value.isNullOrEmpty())
        assert(whatWeDoViewModel.status.value == setFailure())
    }

    @Test
    fun `when setWhatWeDo is called response is error and whatWeDoList isn't empty`() = runTest {
        coEvery { repository.getMembers() } returns memberResponseApiFailure()
        whatWeDoViewModel.setWhatWeDo()
        assert(whatWeDoViewModel.whatWeDoList.value.isNullOrEmpty())
        assert(whatWeDoViewModel.status.value == setFailure())
    }

    //Members
    private fun memberResponseOnSuccess(): MembersResponse = MembersResponse(true, listOfMembers(), "")
    private fun memberResponseOnError(): MembersResponse = MembersResponse(false, emptyList(), "")
    private fun memberResponseApiFailure(): MembersResponse = MembersResponse(false, listOfMembers(), "")
    private fun listOfMembers(): List<Member> = listOf(Member(
        1, "Jose", "link", "", "", "", "", "", ""))
    //Api Status
    private fun setSuccess(): ApiStatus = ApiStatus.SUCCESS
    private fun setFailure(): ApiStatus = ApiStatus.FAILURE
}