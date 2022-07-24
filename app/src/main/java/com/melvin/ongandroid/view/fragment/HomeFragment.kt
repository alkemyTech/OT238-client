package com.melvin.ongandroid.view.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.FragmentHomeBinding
import com.melvin.ongandroid.model.entities.slides.Slide
import com.melvin.ongandroid.view.adapters.HomeViewPagerAdapter
import com.melvin.ongandroid.model.entities.news.News
import com.melvin.ongandroid.model.entities.testimonials.Testimonials
import com.melvin.ongandroid.view.adapters.NewsAdapter
import com.melvin.ongandroid.view.adapters.TestimonialsAdapter
import com.melvin.ongandroid.viewmodel.ApiStatus
import com.melvin.ongandroid.viewmodel.ErrorStatus
import com.melvin.ongandroid.viewmodel.HomeViewModel
import com.melvin.ongandroid.viewmodel.TestimonialsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private val vmTestimonial : TestimonialsViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: HomeViewPagerAdapter
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var testimonialsAdapter: TestimonialsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentHomeBinding.bind(view)
        initHome()
    }

    private fun initHome(){
        setupSlide()
        setUpNews()
        setUpTestimonials()
        showProgressBarCharging()
    }

    private fun setupSlide() {
        viewModel.getSlides()
        setupSlideObserver()
    }

    private fun setUpNews() {
        viewModel.getNews()
        setUpNewsObserver()
    }

    private fun setUpTestimonials(){
        vmTestimonial.getTestimonial()
        setUpTestimonialsObserver()
    }

    private fun loadSlidePager(data: List<Slide>) {
        adapter = HomeViewPagerAdapter(data)
        binding.pager.adapter = adapter
    }

    private fun loadNewsPager(data: List<News>){
        newsAdapter = NewsAdapter(data, true)
        binding.vpNews.adapter = newsAdapter
    }

    private fun loadTestimonialsPager(data: List<Testimonials>){
        testimonialsAdapter = TestimonialsAdapter(data,true)
        binding.vpTestimonials.adapter = testimonialsAdapter
    }

    private fun setupSlideObserver() {
        viewModel.slideList.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                errorHandler(ErrorStatus.SLIDES)
            } else {
                loadSlidePager(it)
            }
        }
    }

    private fun setUpNewsObserver() {
        viewModel.newsList.observe(viewLifecycleOwner){
            if (it.isNullOrEmpty()){
                errorHandler(ErrorStatus.NEWS)
            }else{
                loadNewsPager(it)
            }
        }
    }

    private fun setUpTestimonialsObserver(){
        vmTestimonial.testimonialsList.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()){
                errorHandler(ErrorStatus.TESTIMONIALS)
            } else {
                loadTestimonialsPager(it)
            }
        }
    }

    private fun errorHandler(apiCall: ErrorStatus){
        when(apiCall){
            ErrorStatus.SLIDES -> {
                showFailureDialogSlides()
            }
            ErrorStatus.NEWS -> {
                showFailureDialogNews()
            }
            ErrorStatus.TESTIMONIALS -> {
                showFailureDialogTestimonials()
            }
        }
    }

    private fun showFailureDialogSlides() {
        val dialog = AlertDialog.Builder(context)
            .setTitle("Error")
            .setMessage("No slides found")
            .setPositiveButton("OK") { _, _ ->
                setupSlide()
            }
            .setCancelable(false)
            .create()
        dialog.show()
    }

    private fun showFailureDialogNews(){
        val dialog = AlertDialog.Builder(context)
            .setTitle(R.string.dialog_news_error_title)
            .setMessage(R.string.dialog_news_error_message)
            .setPositiveButton(R.string.dialog_news_error_positive_btn) { _, _ ->
                setUpNewsObserver()
            }
            .setCancelable(false)
            .create()
        dialog.show()
    }

    private fun showFailureDialogTestimonials() {
        val dialog = AlertDialog.Builder(context)
            .setTitle(R.string.testimonials)
            .setMessage(R.string.dialog_new_error_testimonials)
            .setPositiveButton(R.string.dialog_news_error_positive_btn) { _, _ ->
                setUpTestimonials()
            }
            .setCancelable(false)
            .create()
        dialog.show()
    }

    private fun showProgressBarCharging(){
        viewModel.status.observe(viewLifecycleOwner) { currentStatus ->
            when (currentStatus) {
                ApiStatus.SUCCESS, ApiStatus.FAILURE -> binding.pbHome.hideProgressBar()
                ApiStatus.LOADING -> binding.pbHome.showProgressBar()
            }
        }
    }

}
