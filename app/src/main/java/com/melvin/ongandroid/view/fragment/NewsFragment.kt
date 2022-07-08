package com.melvin.ongandroid.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.FragmentNewsBinding
import com.melvin.ongandroid.model.entities.news.News
import com.melvin.ongandroid.view.adapters.NewsAdapter
import com.melvin.ongandroid.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment: Fragment() {

    private val viewModel: NewsViewModel by viewModels()
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title =
            resources.getString(R.string.menu_news)
        //initRecyclerView(newsDummyData)
        setUpNews()
    }

    private var newsDummyData = listOf(
        News(
            1,
            "Tokyo",
            "",
            "Konnichiwa, sushi, naruto",
            "https://media-cdn.tripadvisor.com/media/photo-s/15/e6/9b/47/tokyo-nightlife.jpg",
            1,
            1,
            "",
            ""
        ),
        News(
            2,
            "Los Angeles",
            "",
            "Home of Hollywood and great beaches",
            "https://media-cdn.tripadvisor.com/media/photo-s/15/e6/9b/47/tokyo-nightlife.jpg",
            1,
            1,
            "",
            ""
        ),
        News(
            3,
            "Mar del Plata",
            "",
            "Home of great churros",
            "https://media-cdn.tripadvisor.com/media/photo-s/15/e6/9b/47/tokyo-nightlife.jpg",
            1,
            1,
            "",
            ""
        )
    )

    private fun initRecyclerView(data: List<News>) {
        binding.rvNews.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvNews.adapter = NewsAdapter(data, false)
    }

    private fun setUpObserver() {
        viewModel.observerNewsList().observe(viewLifecycleOwner) {
            if (viewModel.observerNewsList() != null) {
                initRecyclerView(it)
            } else {
                dialogNews()
            }
        }
    }

    private fun setUpNews() {
        viewModel.getNews()
        setUpObserver()
    }

    private fun dialogNews() {
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(R.string.menu_news)
                .setMessage(R.string.dialog_news_error_message)
                .setPositiveButton(R.string.dialog_news_error_positive_btn) { _, _ ->
                    setUpNews()
                }
                .show()
        }
    }

    private fun showProgressBarCharging() {
        viewModel.observerNewsList().observe(viewLifecycleOwner) {
            if (viewModel.observerNewsList() == null) {
                binding.rvNews.showProgressBar()
            } else {
                binding.rvNews.hideProgressBar()
            }
        }
    }
}