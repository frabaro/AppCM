package estg.ipvc.appcm

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import estg.ipvc.appcm.adapters.NotaApplication
import estg.ipvc.appcm.adapters.NotaListAdapter
import estg.ipvc.appcm.entities.Nota
import estg.ipvc.appcm.viewModel.NotaViewModel
import estg.ipvc.appcm.viewModel.WordViewModelFactory

class MainActivity : AppCompatActivity() {

    private val newNotaActivityRequestCode = 1
    private val notaViewModel: NotaViewModel by viewModels {
        WordViewModelFactory((application as NotaApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = NotaListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        notaViewModel.allNotes.observe(this) { notas ->
            // Update the cached copy of the words in the adapter.
            notas?.let { adapter.submitList(it) }
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewNotaActivity::class.java)
            startActivityForResult(intent, newNotaActivityRequestCode)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newNotaActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewNotaActivity.EXTRA_REPLY_TITULO)?.let {
                val nota = Nota(titulo = it, descricao = "Descricao da nota")
                notaViewModel.insert(nota)
            }
        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }

    /*
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.
        }
    }*/

}