package com.melvin.ongandroid.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.melvin.ongandroid.MainCoroutinesRule
import com.melvin.ongandroid.data.ApiClient
import com.melvin.ongandroid.data.OngApi
import com.melvin.ongandroid.model.entities.testimonials.Testimonials
import com.melvin.ongandroid.model.entities.testimonials.TestimonialsResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class TestimonialsViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @RelaxedMockK
    private lateinit var ongApiMock: OngApi

    private lateinit var repository : ApiClient
    private lateinit var viewModel: TestimonialsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = ApiClient(ongApiMock)
        viewModel = TestimonialsViewModel(repository)
    }
    @Test
    fun `when getTestimonials is called response is successful and testimonialsList isn't empty`() =
        runTest {
            coEvery {
                repository.getTestimonials()
            } returns testimonial()
        viewModel.getTestimonial()
        assertEquals(statusSuccess(),viewModel._status.value)
        assertEquals(testimonialsList(),viewModel._testimonialsList.value)
        }

    @Test
    fun `when getTestimonials is called response is successful and testimonialsList is empty`() =
        runTest {
            coEvery {
                repository.getTestimonials()
            } returns testimonialError()
        viewModel.getTestimonial()
        assertEquals(statusError(),viewModel._status.value)
        assert(viewModel._testimonialsList.value.isNullOrEmpty())
        }

    @Test
    fun `when getTestimonials is called response is error and testimonialsList is empty`() =
        runTest {
            coEvery {
                repository.getTestimonials()
            } returns testimonialApiFailure()
        viewModel.getTestimonial()
        assertEquals(statusError(),viewModel._status.value)
        assert(viewModel._testimonialsList.value.isNullOrEmpty())
        }

    //Data for testing
    //Api Status
    private fun statusSuccess(): ApiStatus = ApiStatus.SUCCESS
    private fun statusError(): ApiStatus = ApiStatus.FAILURE
    //Testimonials
    private fun testimonialApiFailure(): TestimonialsResponse = TestimonialsResponse(false, testimonialsList())
    private fun testimonialError(): TestimonialsResponse = TestimonialsResponse(false, emptyList())
    private fun testimonial(): TestimonialsResponse = TestimonialsResponse(true, testimonialsList())
    private fun testimonialsList(): List<Testimonials> = listOf(
        Testimonials(1, "test", "link", "", "", "", "")
    )
}