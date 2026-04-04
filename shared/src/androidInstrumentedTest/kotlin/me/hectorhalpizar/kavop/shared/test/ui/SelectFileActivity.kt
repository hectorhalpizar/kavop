package me.hectorhalpizar.kavop.shared.test.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.documentfile.provider.DocumentFile
import me.hectorhalpizar.kavop.shared.test.R
import me.hectorhalpizar.kavop.util.IdlingUtil

class SelectFileActivity : AppCompatActivity() {

    internal var idlingUtil: IdlingUtil? = null
    internal var filePickerCallback: (DocumentFile?) -> Unit = {}
    internal var folderPickerCallback: (DocumentFile?) -> Unit = {}
    internal var fileSelected: DocumentFile? = null
    internal var folderSelected: DocumentFile? = null

    private val filePicker = registerForActivityResult(
        ActivityResultContracts.OpenDocument()) { result ->


        result?.let { uriFileSelected ->

            val fileSelected = DocumentFile.fromSingleUri(this, uriFileSelected)

            findViewById<TextView>(R.id.folder_selected).text =
                String.format(getString(R.string.folder_selected), fileSelected?.name)

            filePickerCallback(fileSelected)
            this.fileSelected = fileSelected
        }
    }

    private val folderPicker = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->

        if (result.resultCode == RESULT_OK) {
            result.data?.data?.let { treeUri ->
                contentResolver.takePersistableUriPermission(
                    treeUri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION or
                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                )

                val dir = DocumentFile.fromTreeUri(this, treeUri)

                val selectedFolder = dir?.name

                findViewById<TextView>(R.id.folder_selected).text =
                    String.format(getString(R.string.folder_selected), selectedFolder)

                dir?.listFiles()?.forEach { file ->
                    Log.d(TAG, ":::: File ${file.name}, is directory: ${file.isDirectory}")
                }

                folderPickerCallback(dir)
                folderSelected = dir
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_select_file)

        findViewById<TextView>(R.id.folder_selected).text = String.format(getString(R.string.file_selected), "")
    }

    fun click(view: View) {
        Log.d(TAG, ":::: View clicked $view")

        when (view.id) {
            R.id.select_folder -> folderPicker.launch(Intent(Intent.ACTION_OPEN_DOCUMENT_TREE))
            R.id.select_file -> filePicker.launch(arrayOf("*/*"))
            else -> Log.d(TAG, "No action set for $view")
        }

        idlingUtil?.increment()
    }

    companion object {
        private const val TAG = "DocumentFileTestActivity"
    }
}