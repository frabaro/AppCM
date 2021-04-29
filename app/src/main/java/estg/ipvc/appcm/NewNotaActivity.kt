package estg.ipvc.appcm

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewNotaActivity : AppCompatActivity() {

    private lateinit var edit_titulo: EditText
    //private lateinit var edit_descricao: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_nota)
        edit_titulo = findViewById(R.id.edit_titulo)
        //edit_descricao = findViewById(R.id.edit_descricao)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(edit_titulo.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val nota_titulo = edit_titulo.text.toString()
                replyIntent.putExtra(EXTRA_REPLY_TITULO, nota_titulo)

                //val nota_descricao = edit_descricao.text.toString()
                //replyIntent.putExtra(EXTRA_REPLY_DESCRICAO, nota_descricao)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

    }

    companion object {
        const val EXTRA_REPLY_TITULO = "com.example.android.wordlistsql.REPLY"
        //const val EXTRA_REPLY_DESCRICAO = "com.example.android.wordlistsql.REPLY"
    }

}