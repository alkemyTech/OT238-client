package com.melvin.ongandroid.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.melvin.ongandroid.MainCoroutinesRule
import com.melvin.ongandroid.data.ApiClient
import com.melvin.ongandroid.data.OngApi
import com.melvin.ongandroid.model.entities.news.News
import com.melvin.ongandroid.model.entities.news.NewsResponse
import com.melvin.ongandroid.model.entities.slides.Slide
import com.melvin.ongandroid.model.entities.slides.SlidesResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.runners.model.MultipleFailureException.assertEmpty


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class HomeViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @RelaxedMockK
    private lateinit var ongApiMock: OngApi

    private lateinit var repository : ApiClient
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = ApiClient(ongApiMock)
        homeViewModel = HomeViewModel(repository)
    }

    //Slides

    @Test
    fun `when getSlides is called response is successful and slideList isn't empty or null`() = runTest{
        coEvery {
            repository.getSlide()
        } returns slide()
        homeViewModel.getSlides()
        assertEquals(statusSuccess(), homeViewModel._status.value)
        assertEquals(slideList(), homeViewModel._slideList.value)
    }

    @Test
    fun `when getSlides is called response is failed and slideList is empty`() = runTest{
        coEvery {
            repository.getSlide()
        } returns slideError()
        homeViewModel.getSlides()
        assertEquals(statusError(), homeViewModel._status.value)
        assert(homeViewModel._slideList.value.isNullOrEmpty())
    }

    @Test
    fun `when getSlides is called response is failed and slideList isn't empty or null`() = runTest{
        coEvery {
            repository.getSlide()
        } returns slideApiFailure()
        homeViewModel.getSlides()
        assertEquals(statusError(), homeViewModel._status.value)
        assert(homeViewModel._slideList.value.isNullOrEmpty())
    }

    //News
    @Test
    fun `when getNews is called response is successful and newsList isn't empty or null`() = runTest{
        coEvery {
            repository.getNews()
        } returns news()
        homeViewModel.getNews()
        assertEquals(statusSuccess(), homeViewModel._status.value)
        assertEquals(newsList(), homeViewModel._newsList.value)
    }

    @Test
    fun `when getNews is called response is failed and newsList is empty`() = runTest{
        coEvery {
            repository.getNews()
        } returns newsError()
        homeViewModel.getNews()
        assertEquals(statusError(), homeViewModel._status.value)
        assert(homeViewModel._newsList.value.isNullOrEmpty())
    }

    @Test
    fun `when getNews is called response is failed and newsList isn't empty or null`() = runTest{
        coEvery {
            repository.getNews()
        } returns newsApiFailure()
        homeViewModel.getNews()
        assertEquals(statusError(), homeViewModel._status.value)
        assert(homeViewModel._newsList.value.isNullOrEmpty())
    }

    //Data for tests
    //Api Status
    private fun statusSuccess(): ApiStatus = ApiStatus.SUCCESS
    private fun statusError(): ApiStatus = ApiStatus.FAILURE
    //Slides
    private fun slideApiFailure(): SlidesResponse = SlidesResponse(false, slideList())
    private fun slideError(): SlidesResponse = SlidesResponse(false, emptyList())
    private fun slide(): SlidesResponse  = SlidesResponse(true, slideList())
    private fun slideList() : List<Slide> = listOf(Slide(1,"lala", "asd", "https:"))
    //News
    private fun newsApiFailure(): NewsResponse = NewsResponse(false, newsList())
    private fun newsError(): NewsResponse = NewsResponse(false, emptyList())
    private fun news(): NewsResponse = NewsResponse(true, newsList())
    private fun newsList() : List<News> = listOf(
        News(1,"lala", "asd", "asda", "https:", 1,1,
        "asd", "asd", "asd"))
}