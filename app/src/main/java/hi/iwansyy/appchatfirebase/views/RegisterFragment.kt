package hi.iwansyy.appchatfirebase.views

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import hi.iwansyy.appchatfirebase.R
import hi.iwansyy.appchatfirebase.databinding.FragmentRegisterBinding
import hi.iwansyy.appchatfirebase.utils.LocalSession
import hi.iwansyy.appchatfirebase.utils.ShowLoading
import hi.iwansyy.appchatfirebase.utils.showToast
import kotlinx.android.synthetic.main.fragment_register.*
import java.util.*

class RegisterFragment : Fragment(), ShowLoading {
    private lateinit var binding: FragmentRegisterBinding
    private val auth by lazy { Firebase.auth }
    private val db by lazy { Firebase.firestore }
    private val localSession by lazy { LocalSession(requireContext()) }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false).apply {

            ivUser.setOnClickListener {
                Log.d("Register Fragment", "Try to choose foto !!!")

                val i = Intent(Intent.ACTION_PICK)
                i.type = "image/*"
                startActivityForResult(i, 0)
            }

            btnReg.setOnClickListener {/*
                if (etEmail.text.toString().isNotEmpty() && etPassword.text.toString()
                        .isNotEmpty()
                ) {
                    isLoading()
                    auth.createUserWithEmailAndPassword(
                        etEmail.text.toString(),
                        etPassword.text.toString()
                    )
                        .addOnSuccessListener {
                            it.user?.uid?.let { uid ->
                                localSession.uid = uid
                                db.collection(ConstantUtil.COLLECTION).document(uid)
                                    .set(UserModel(uid, etEmail.text.toString()))
                                    .addOnSuccessListener {
                                        Log.d("Register", "Successfully created with uid: ${uid}")
                                        showToast("Register Successfully")

                                        uploadImageToFirebaseStorage()

                                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                                    }.addOnFailureListener { exc ->
                                        exc.printStackTrace()
                                        hideLoading()
                                        showToast("Failed to create user")
                                        Log.d("Register", "Failed created user: ${exc.message}")
                                    }
                            }
                            hideLoading()
                        }
                    Log.d("Register", "Email is ${etEmail} & Password${etPassword}")
                } else {
                    showToast("Email and Password cannot be empty")
                }*/
                performButtonRegister()
            }
            tvLogin.setOnClickListener {
                isLoading()
                Log.d("Fragment Register", "Try navigate to Login Fragment")
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }
        return binding.root
    }

    private fun performButtonRegister() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (email.isEmpty() && password.isEmpty()) {
            showToast("Please Fill out all fields on this form !!")
            Log.d("Register", "Email : $email, \n Password: $password")

            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (!it.isSuccessful) return@addOnCompleteListener
                        Log.d("Register", "Successfully create user : ${it.result?.user?.uid}")

                        uploadImageToFirebaseStorage()
                    }
                    .addOnFailureListener {
                        Log.d("Register", "Failed create user : ${it.message}")
                        showToast("Failed to create user: ${it.message}")
                    }

        }

    }

    private fun uploadImageToFirebaseStorage() {
        if (selectedPhotoUri == null) return
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("images/$filename")
        ref.putFile(selectedPhotoUri!!)
                .addOnSuccessListener {
                    Log.d("Register", "Successfully create user : ${it.metadata?.path}")
                }
    }

    private fun saveUserToFirebaseDatabase(profileImageUrl: String) {
    }

    var selectedPhotoUri : Uri? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            Log.d("Fragment Register", "Photo was selected")
            selectedPhotoUri = data.data

            val bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, selectedPhotoUri)
            val bitmapDrawable = BitmapDrawable(bitmap)
            binding.ivUser.setBackgroundDrawable(bitmapDrawable)

        }
    }

    override fun isLoading() {
        binding.pgBar.visibility = View.VISIBLE
        binding.btnReg.visibility = View.INVISIBLE
        binding.tvLogin.visibility = View.INVISIBLE
    }

    override fun hideLoading() {
        binding.pgBar.visibility = View.GONE
        binding.btnReg.visibility = View.VISIBLE
        binding.tvLogin.visibility = View.VISIBLE
    }
}