package com.melvin.ongandroid.view.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
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
        setupSlide()
        setUpNews()
        setUpTestimonials()
    }

    private fun setupSlide() {
        viewModel.getSlides()
        setupObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadViewPager(data: List<Slide>) {
        adapter = HomeViewPagerAdapter(data)
        binding.pager.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.observerSlideList().observe(viewLifecycleOwner) {
            if (viewModel.observerSlideList() != null) {
                loadViewPager(it)
            } else {
                snackBar()

            }
        }
    }

    private fun snackBar() {
        Snackbar.make(binding.constraint, R.string.textError, Snackbar.LENGTH_LONG)
            .setAction(R.string.actionText) {
                setupObserver()
            }
            .show()
    }

    private fun setUpNews() {
        viewModel.getNews()
        setUpNewsObserver()
    }

    private fun loadNewsPager(data: List<News>){
        newsAdapter = NewsAdapter(data)
        binding.vpNews.adapter = newsAdapter
    }

    private fun setUpNewsObserver() {
        viewModel.observeNewsList().observe(viewLifecycleOwner){
            if (viewModel.observeNewsList() != null){
                loadNewsPager(it)
            }else{
                dialogNews()
            }
        }
    }

    private fun dialogNews(){
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

    private fun setUpTestimonials(){
        vmTestimonial.getTestimonial()
        setUpObserver()
    }

    private fun setUpObserver(){
        vmTestimonial.observerTestimonialsList().observe(viewLifecycleOwner) {
            if (it != null){
                loadPagerTestimonials(it)
            } else {
                snackBar()
            }
        }
    }

    private fun loadPagerTestimonials(data: List<Testimonials>){
        testimonialsAdapter = TestimonialsAdapter(data,true)
        binding.vpTestimonials.adapter = testimonialsAdapter
    }

    private fun dialogTestimonials() {
        //TODO: Ticket #41
    }

}
