package com.example.telegramclone.ui.fragments.message_recycler_view.view_holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.telegramclone.database.CURRENT_UID
import com.example.telegramclone.ui.fragments.message_recycler_view.views.MessageView
import com.example.telegramclone.utilities.asTime
import com.example.telegramclone.utilities.downloadAndSetImage
import kotlinx.android.synthetic.main.message_item_image.view.*

class HolderImageMessage(view: View) : RecyclerView.ViewHolder(view) {
    val blockRecievedImageMessage: ConstraintLayout = view.block_recieved_image_message
    val chatRecievedImageMessage: ImageView = view.chat_recieved_image
    val chatRecievedImageMessageTime: TextView = view.chat_recieved_image_message_time

    val blockUserImageMessage: ConstraintLayout = view.block_user_image_message
    val chatUserImageMessage: ImageView = view.chat_user_image
    val chatUserImageMessageTime: TextView = view.chat_user_image_message_time

    fun drawMessageImage(view: MessageView) {
        if (view.from == CURRENT_UID) {
            blockUserImageMessage.visibility = View.VISIBLE
            blockRecievedImageMessage.visibility = View.GONE
            chatUserImageMessage.downloadAndSetImage(view.fileUrl)
            chatUserImageMessageTime.text = view.timeStamp.asTime()
        } else {
            blockUserImageMessage.visibility = View.GONE
            blockRecievedImageMessage.visibility = View.VISIBLE
            chatRecievedImageMessage.downloadAndSetImage(view.fileUrl)
            chatRecievedImageMessageTime.text = view.timeStamp.asTime()
        }
    }
}