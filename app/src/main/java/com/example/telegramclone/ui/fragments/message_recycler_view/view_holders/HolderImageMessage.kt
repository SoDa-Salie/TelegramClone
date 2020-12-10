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

class HolderImageMessage(view: View) : RecyclerView.ViewHolder(view), MessageHolder {
    private val blockRecievedImageMessage: ConstraintLayout = view.block_recieved_image_message
    private val chatRecievedImageMessage: ImageView = view.chat_recieved_image
    private val chatRecievedImageMessageTime: TextView = view.chat_recieved_image_message_time

    private val blockUserImageMessage: ConstraintLayout = view.block_user_image_message
    private val chatUserImageMessage: ImageView = view.chat_user_image
    private val chatUserImageMessageTime: TextView = view.chat_user_image_message_time

    override fun drawMessage(view: MessageView) {
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

    override fun onAttach(view: MessageView) {

    }

    override fun onDetach() {

    }
}