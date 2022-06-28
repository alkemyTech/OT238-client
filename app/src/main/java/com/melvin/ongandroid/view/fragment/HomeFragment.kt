package com.melvin.ongandroid.view.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.FragmentHomeBinding
import com.melvin.ongandroid.model.entities.News
import com.melvin.ongandroid.view.adapters.NewsAdapter
import com.melvin.ongandroid.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private lateinit var newsAdapter: NewsAdapter
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

        setUpObserver()
    }

    private fun loadNewsPager(data: List<News>){
        newsAdapter = NewsAdapter(data)
        binding.vpNews.adapter = newsAdapter
    }

    private fun setUpObserver() {
        viewModel.observeNewsList().observe(viewLifecycleOwner){
            if (viewModel.observeNewsList() != null){
                loadNewsPager(it)
            }else{
                dialogNews()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun dialogNews(){
        val dialog = AlertDialog.Builder(context)
            .setTitle(R.string.dialog_news_error_title)
            .setMessage(R.string.dialog_news_error_message)
            .setPositiveButton(R.string.dialog_news_error_positive_btn) { view, _ ->
                setUpObserver()
            }
            .setCancelable(false)
            .create()
        dialog.show()
    }
}