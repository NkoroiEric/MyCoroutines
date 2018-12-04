package com.scaleup.admin.mycoroutines

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.solver.widgets.Snapshot
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }


    private val repository = Repository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt.setOnClickListener {
            viewModel.showMessage(repository)
            viewModel.toast.observe(this, Observer {
                //Toast.makeText(this@MainActivity,it,Toast.LENGTH_SHORT).show()
                txt.text = it
            })
//            viewModel.showSnack()
//            viewModel.snackBar.observe(this, Observer {
//                if (it != null){
//                    Toast.makeText(this@MainActivity, "Snackbar: $it",Toast.LENGTH_SHORT).show()
//                }
//            })
        }
        viewModel.snackBar.observe(this, Observer {
            if (it != null){
                Toast.makeText(this@MainActivity, "Snackbar: $it",Toast.LENGTH_SHORT).show()
            }
        })
    }
}
