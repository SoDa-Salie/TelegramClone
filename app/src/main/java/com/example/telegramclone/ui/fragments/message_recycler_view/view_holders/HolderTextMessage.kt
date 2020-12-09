package com.example.telegramclone.ui.fragments.message_recycler_view.view_holders

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.telegramclone.database.CURRENT_UID
import com.example.telegramclone.ui.fragments.message_recycler_view.views.MessageView
import com.example.telegramclone.utilities.asTime
import kotlinx.android.synthetic.main.message_item_text.view.*

class HolderTextMessage(view: View) : RecyclerView.ViewHolder(view){
    val blockUserMessage: ConstraintLayout = view.block_user_message
    val chatUserMessage: TextView = view.chat_user_message
    val chatUserMessageTime: TextView = view.chat_user_message_time

    val blockRecievedMessage: ConstraintLayout = view.block_recieved_message
    val chatRecievedMessage: TextView = view.chat_recieved_message
    val chatRecievedMessageTime: TextView = view.chat_recieved_message_time

    fun drawMessageText(view: MessageView) {
        if (view.from == CURRENT_UID) {
            blockUserMessage.visibility = View.VISIBLE
            blockRecievedMessage.visibility = View.GONE
            chatUserMessage.text = view.text
            chatUserMessageTime.text = view.timeStamp.asTime()
        } else {
            blockUserMessage.visibility = View.GONE
            blockRecievedMessage.visibility = View.VISIBLE
            chatRecievedMessage.text = view.text
            chatRecievedMessageTime.text = view.timeStamp.asTime()
        }
    }
}