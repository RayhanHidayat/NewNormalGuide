package com.rayhan.newnormalguide.ui.hotline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rayhan.newnormalguide.databinding.FragmentHotlineBinding

class HotlineFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentHotlineBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHotlineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cvSatu.setOnClickListener(this)
        binding.cvDua.setOnClickListener(this)
        binding.cvTiga.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.cvSatu -> {

            }
        }
    }
}