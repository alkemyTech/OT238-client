package com.melvin.ongandroid.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.UsMemberDetailBinding
import com.melvin.ongandroid.model.entities.us.Member
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MembersFragment : Fragment() {
    private var _binding: UsMemberDetailBinding? = null
    private val binding get() = _binding!!
    private val args: MembersFragmentArgs by navArgs()
    private lateinit var member: Member

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UsMemberDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title =
            resources.getString(R.string.member_detail_title)
        member = args.member
        fillMemberData()
    }

    private fun fillMemberData() {
        binding.apply {
            tvMemberName.text = member.name
            tvMemberDescription.text = member.rawDescription
            Picasso.get().load(member.image).into(binding.ivMember)
            ibFacebook.setOnClickListener {
                val fbURL = member.facebookURL.toString()
                val url = urlReplace(fbURL)
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(urlReplace(url))
                startActivity(intent)
            }
            ibLinkedin.setOnClickListener {
                val liURL = member.linkedInURL.toString()
                val url = urlReplace(liURL)
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(urlReplace(url))
                startActivity(intent)
            }
        }
    }

    private fun urlReplace(url: String): String {
        val https = "https://"
        return if (!url.contains("https://")) {
            https + url
        } else
            url
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}