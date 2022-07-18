package com.melvin.ongandroid.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.melvin.ongandroid.MainCoroutinesRule
import com.melvin.ongandroid.data.ApiClient
import com.melvin.ongandroid.data.OngApi
import com.melvin.ongandroid.model.entities.us.Member
import com.melvin.ongandroid.model.entities.us.MembersResponse
import com.melvin.ongandroid.model.entities.whatWeDo.WhatWeDo
import com.melvin.ongandroid.model.entities.whatWeDo.WhatWeDoResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.assertEquals
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

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @RelaxedMockK
    private lateinit var api:  OngApi

    private lateinit var repository: ApiClient
    private lateinit var whatWeDoViewModel: WhatWeDoViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = ApiClient(api)
        whatWeDoViewModel = WhatWeDoViewModel(repository)
    }


    @Test
    fun `when setWhatWeDo is called response is successful and whatWeDoList isn't empty`() =
        runTest {
            coEvery {
                repository.getActivities()
            } returns memberResponseOnSuccess()
        whatWeDoViewModel.setWhatWeDo()
        assertEquals(setSuccess(), whatWeDoViewModel._status.value)
        assertEquals(listOfMembers(), whatWeDoViewModel.whatWeDoList.value)
    }

    @Test
    fun `when setWhatWeDo is called response is successful and whatWeDoList is empty`() = runTest {
        coEvery { repository.getActivities() } returns memberResponseOnError()
        whatWeDoViewModel.setWhatWeDo()
        assert(whatWeDoViewModel.whatWeDoList.value.isNullOrEmpty())
        assert(whatWeDoViewModel.status.value == setFailure())
    }

    @Test
    fun `when setWhatWeDo is called response is error and whatWeDoList isn't empty`() = runTest {
        coEvery { repository.getActivities() } returns memberResponseApiFailure()
        whatWeDoViewModel.setWhatWeDo()
        assert(whatWeDoViewModel.whatWeDoList.value.isNullOrEmpty())
        assert(whatWeDoViewModel.status.value == setFailure())
    }

    //Members
    private fun memberResponseOnSuccess(): WhatWeDoResponse = WhatWeDoResponse(true, listOfMembers())
    private fun memberResponseOnError(): WhatWeDoResponse = WhatWeDoResponse(false, emptyList())
    private fun memberResponseApiFailure(): WhatWeDoResponse = WhatWeDoResponse(false, listOfMembers())
    private fun listOfMembers(): List<WhatWeDo> = listOf(WhatWeDo(
        1, "Jose", "link", "", ""))
    //Api Status
    private fun setSuccess(): ApiStatus = ApiStatus.SUCCESS
    private fun setFailure(): ApiStatus = ApiStatus.FAILURE
}