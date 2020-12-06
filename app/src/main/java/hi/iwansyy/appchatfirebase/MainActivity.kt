package hi.iwansyy.appchatfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import hi.iwansyy.appchatfirebase.utils.LocalSession

class MainActivity : AppCompatActivity() {
    private val auth by lazy { Firebase.auth }
    private val localSession by lazy { LocalSession(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}