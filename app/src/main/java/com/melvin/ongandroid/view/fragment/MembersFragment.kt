package com.melvin.ongandroid.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.melvin.ongandroid.databinding.UsMemberDetailBinding
import com.melvin.ongandroid.model.entities.we.Member
import com.squareup.picasso.Picasso

class MembersFragment: Fragment() {
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

        member = args.member
        fillMemberData()
    }

    private fun fillMemberData() {
        binding.apply{
            tvMemberName.text = member.name
            tvMemberDescription.text = member.description
            Picasso.get().load(member.image).into(binding.ivMember)
            ibFacebook.setOnClickListener {
                val url = member.facebookURL
                val intent = Intent(Intent.ACTION_VIEW)
                //TODO CHECK API URL
                intent.data = Uri.parse("https://$url")
                startActivity(intent)
            }
            //TODO CHECK API URL
            ibLinkedin.setOnClickListener {
                val url = member.linkedinURL
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://$url")
                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}