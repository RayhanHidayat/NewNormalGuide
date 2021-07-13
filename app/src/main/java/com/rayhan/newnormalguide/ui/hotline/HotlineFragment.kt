package com.rayhan.newnormalguide.ui.hotline

import android.content.Intent
import android.net.Uri
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
                val browser = Intent(Intent.ACTION_VIEW, Uri.parse("https://covid19.go.id/"))
                startActivity(browser)
            }
            binding.cvDua -> {
                val phone = Intent(Intent.ACTION_CALL, Uri.parse("119"))
                startActivity(phone)
            }
            binding.cvTiga -> {
                val browser = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.kompas.com/tren/read/2020/03/03/183500265/infografik-daftar-100-rumah-sakit-rujukan-penanganan-virus-corona")
                )
                startActivity(browser)
            }
        }
    }
}