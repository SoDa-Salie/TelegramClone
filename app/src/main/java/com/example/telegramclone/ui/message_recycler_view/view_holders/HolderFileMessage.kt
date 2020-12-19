package com.example.telegramclone.ui.message_recycler_view.view_holders

import android.os.Environment
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.telegramclone.database.CURRENT_UID
import com.example.telegramclone.database.getFileFromStorage
import com.example.telegramclone.ui.message_recycler_view.views.MessageView
import com.example.telegramclone.utilities.WRITE_FILES
import com.example.telegramclone.utilities.asTime
import com.example.telegramclone.utilities.checkPermission
import com.example.telegramclone.utilities.showToast
import kotlinx.android.synthetic.main.message_item_file.view.*
import java.io.File
import java.lang.Exception

class HolderFileMessage(view: View) : RecyclerView.ViewHolder(view), MessageHolder {
    private val blockRecievedFileMessage: ConstraintLayout = view.block_recieved_file_message
    private val chatRecievedFileMessageTime: TextView = view.chat_recieved_file_message_time

    private val blockUserFileMessage: ConstraintLayout = view.block_user_file_message
    private val chatUserFileMessageTime: TextView = view.chat_user_file_message_time

    private val chatRecievedBtnDownload: ImageView = view.chat_recieved_btn_download
    private val chatUserBtnDownload: ImageView = view.chat_user_btn_download

    private val chatRecievedFilename: TextView = view.chat_recieved_filename
    private val chatUserFilename: TextView = view.chat_user_filename

    private val chatRecievedProgressBar: ProgressBar = view.chat_recieved_progress_bar
    private val chatUserProgressBar: ProgressBar = view.chat_user_progress_bar

    override fun drawMessage(view: MessageView) {
        if (view.from == CURRENT_UID) {
            blockUserFileMessage.visibility = View.VISIBLE
            blockRecievedFileMessage.visibility = View.GONE
            chatUserFileMessageTime.text = view.timeStamp.asTime()
            chatUserFilename.text = view.text
        } else {
            blockUserFileMessage.visibility = View.GONE
            blockRecievedFileMessage.visibility = View.VISIBLE
            chatRecievedFileMessageTime.text = view.timeStamp.asTime()
            chatRecievedFilename.text = view.text
        }
    }

    override fun onAttach(view: MessageView) {
        if (view.from == CURRENT_UID) chatUserBtnDownload.setOnClickListener { clickToBtnFile(view) }
        else chatRecievedBtnDownload.setOnClickListener { clickToBtnFile(view) }
    }

    private fun clickToBtnFile(view: MessageView) {
        if (view.from == CURRENT_UID) {
            chatUserBtnDownload.visibility = View.INVISIBLE
            chatUserProgressBar.visibility = View.VISIBLE
        } else {
            chatRecievedBtnDownload.visibility = View.INVISIBLE
            chatRecievedProgressBar.visibility = View.VISIBLE
        }

        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            view.text
        )

        try {
            if (checkPermission(WRITE_FILES)) {
                file.createNewFile()
                getFileFromStorage(file, view.fileUrl) {
                    if (view.from == CURRENT_UID) {
                        chatUserBtnDownload.visibility = View.VISIBLE
                        chatUserProgressBar.visibility = View.INVISIBLE
                    } else {
                        chatRecievedBtnDownload.visibility = View.VISIBLE
                        chatRecievedProgressBar.visibility = View.INVISIBLE
                    }
                }
            }
        } catch (e: Exception) {
            showToast(e.message.toString())
        }
    }


    override fun onDetach() {
        chatUserBtnDownload.setOnClickListener(null)
        chatRecievedBtnDownload.setOnClickListener(null)
    }
}