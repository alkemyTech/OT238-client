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
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

internal class UsViewModelTest{

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @RelaxedMockK
    private lateinit var ongApiMock: OngApi
    private lateinit var repository : ApiClient
    private lateinit var viewModel: UsViewModel

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        repository = ApiClient(ongApiMock)
        viewModel = UsViewModel(repository)
    }


        @Test
    fun `when getMembers is called, response is successful and memberList isn't empty`() =
        runTest {
            coEvery {
                repository.getMembers()
            } returns members()
        viewModel.getMembers()
        assertEquals(statusSuccess(),viewModel.status.value)
        assertEquals(membersList(),viewModel.membersList.value)
        }

    @Test
    fun `when getMembers is called, response is successful and memberList is empty`() =
        runTest {
            coEvery {
                repository.getMembers()
            } returns membersError()
            viewModel.getMembers()
            assertEquals(statusError(),viewModel.status.value)
            assert(viewModel.membersList.value.isNullOrEmpty())
        }

    @Test
    fun `when getMembers is called, response is error and memberList is't empty`() =
        runTest {
            coEvery {
                repository.getMembers()
            } returns membersApiFailure()
            viewModel.getMembers()
            assertEquals(statusError(),viewModel.status.value)
            assert(viewModel.membersList.value.isNullOrEmpty())
        }

    //Data for testing
    //Api Status
    private fun statusSuccess(): ApiStatus = ApiStatus.SUCCESS
    private fun statusError(): ApiStatus = ApiStatus.FAILURE
    //Members
    private fun membersApiFailure(): MembersResponse = MembersResponse(false, membersList(),"")
    private fun membersError(): MembersResponse = MembersResponse(false, emptyList(),"")
    private fun members(): MembersResponse = MembersResponse(true, membersList(),"")
    private fun membersList(): List<Member> = listOf(
        Member(1, "test", "link", "", "", "", "","","")
    )



}