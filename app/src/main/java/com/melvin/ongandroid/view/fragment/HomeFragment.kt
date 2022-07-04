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
import com.melvin.ongandroid.model.entities.News
import com.melvin.ongandroid.model.entities.testimonials.Testimonials
import com.melvin.ongandroid.view.adapters.NewsAdapter
import com.melvin.ongandroid.view.adapters.TestimonialsAdapter
import com.melvin.ongandroid.viewmodel.HomeViewModel
import com.melvin.ongandroid.viewmodel.TestimonialsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private val vmTestimonial : TestimonialsViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: HomeViewPagerAdapter
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var testimonialsAdapter: TestimonialsAdapter
    private val binding get() = _binding!!


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

    private fun showProgressBarCharging(view: View){
        viewModel.observerSlideList().observe(viewLifecycleOwner){
            if (viewModel.observerSlideList() == null ||
                viewModel.observeNewsList() == null ||
                vmTestimonial.observerTestimonialsList() == null){
                showProgressBarHome(view)
            }else{
                hideProgressBarHome(view)
            }
        }
        viewModel.observeNewsList().observe(viewLifecycleOwner){
            if (viewModel.observerSlideList() == null ||
                viewModel.observeNewsList() == null ||
                vmTestimonial.observerTestimonialsList() == null){
                showProgressBarHome(view)
            }else{
                hideProgressBarHome(view)
            }
        }
        vmTestimonial.observerTestimonialsList().observe(viewLifecycleOwner) {
            if (viewModel.observerSlideList() == null ||
                viewModel.observeNewsList() == null ||
                vmTestimonial.observerTestimonialsList() == null){
                showProgressBarHome(view)
            }else{
                hideProgressBarHome(view)
            }
        }
    }

    private fun loadSlidePager(data: List<Slide>) {
        adapter = HomeViewPagerAdapter(data)
        binding.pager.adapter = adapter
    }

    private fun loadNewsPager(data: List<News>){
        newsAdapter = NewsAdapter(data)
        binding.vpNews.adapter = newsAdapter
    }

    private fun loadTestimonialsPager(data: List<Testimonials>){
        testimonialsAdapter = TestimonialsAdapter(data,true)
        binding.vpTestimonials.adapter = testimonialsAdapter
    }

    private fun setupSlideObserver() {
        viewModel.observerSlideList().observe(viewLifecycleOwner) {
            if (it != null) {
                loadSlidePager(it)
            } else {
                errorHandler("slides")

            }
        }
    }

    private fun setUpNewsObserver() {
        viewModel.observeNewsList().observe(viewLifecycleOwner){
            if (it != null){
                loadNewsPager(it)
            }else{
                errorHandler("news")
            }
        }
    }

    private fun setUpTestimonialsObserver(){
        vmTestimonial.observerTestimonialsList().observe(viewLifecycleOwner) {
            if (it != null){
                loadTestimonialsPager(it)
            } else {
                errorHandler("testimonials")
            }
        }
    }

    private fun errorHandler(apiCall: String){
             when (apiCall) {
                        "slides" -> showFailureDialogSlides()
                        "news" -> showFailureDialogNews()
                        "testimonials" -> showFailureDialogTestimonials()
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
        viewModel.observerSlideList().observe(viewLifecycleOwner){
            if (viewModel.observerSlideList() == null ||
                viewModel.observeNewsList() == null ||
                vmTestimonial.observerTestimonialsList() == null){
                binding.pbHome.showProgressBar()
            }else{
                binding.pbHome.hideProgressBar()
            }
        }
    }

}
