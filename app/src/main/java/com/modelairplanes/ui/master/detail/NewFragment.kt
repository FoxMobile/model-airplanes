package com.modelairplanes.ui.master.detail

import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.modelairplanes.databinding.FragmentNewBinding
import com.modelairplanes.model.Payment
import com.modelairplanes.model.User
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class NewFragment : Fragment() {

    private var _binding: FragmentNewBinding? = null
    private val binding get() = _binding!!
    private var filePath: Uri? = null

    private val user: User by lazy { requireArguments().getSerializable("user") as User }
    private val payment: Payment? by lazy { requireArguments().getSerializable("payment") as Payment? }

    private val newViewModel: NewViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        setupUIData()

        binding.documentLayout.setOnClickListener {
            selectImage()
        }

        binding.delete.setOnClickListener {
            newViewModel.deleteData(user, payment!!)
        }
        binding.save.setOnClickListener {
            uploadImage()
        }
    }

    private fun setupUIData() {
        payment?.let {
            binding.delete.isVisible = true

        } ?: kotlin.run {
            binding.delete.isVisible = false
        }
    }

    private fun initUI() {
        viewLifecycleOwner.lifecycleScope.launch {
            newViewModel.createDoc.collect {
                if(it) findNavController().popBackStack()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            newViewModel.deleteDoc.collect {
                if(it) findNavController().popBackStack()
            }
        }
    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "application/pdf"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(
                intent,
                "Select Image from here..."
            ),
            PICK_IMAGE_REQUEST
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data?.data != null) {

            // Get the Uri of data
            filePath = data.data
        }
    }


    private fun uploadImage() {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            val progressDialog = ProgressDialog(requireContext())
            progressDialog.setTitle("Uploading...")
            progressDialog.show()

            // Defining the child of storageReference
            val ref: StorageReference = FirebaseStorage.getInstance().reference
                .child("docs/" + UUID.randomUUID().toString())

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath!!)
                .addOnSuccessListener { // Image uploaded successfully
                    // Dismiss dialog
                    progressDialog.dismiss()
                    Toast
                        .makeText(
                            requireContext(),
                            "Image Uploaded!!",
                            Toast.LENGTH_SHORT
                        )
                        .show()


                    val day: Int = binding.datePicker.dayOfMonth
                    val month: Int = binding.datePicker.month + 1
                    val year: Int = binding.datePicker.year

                    val calendar = Calendar.getInstance()
                    calendar.set(year, month, day)


                    newViewModel.saveData(user, calendar.time, it.storage.downloadUrl.toString())

                }
                .addOnFailureListener { e -> // Error, Image not uploaded
                    progressDialog.dismiss()
                    Toast
                        .makeText(
                            requireContext(),
                            "Failed " + e.message,
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
                .addOnProgressListener { taskSnapshot ->

                    val progress = ((100.0
                            * taskSnapshot.bytesTransferred
                            / taskSnapshot.totalByteCount))
                    progressDialog.setMessage(
                        ("Uploaded "
                                + progress.toInt() + "%")
                    )
                }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 123
    }
}