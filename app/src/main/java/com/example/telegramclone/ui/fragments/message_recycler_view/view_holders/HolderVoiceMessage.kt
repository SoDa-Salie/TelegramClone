package com.example.telegramclone.ui.fragments.message_recycler_view.view_holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.telegramclone.database.CURRENT_UID
import com.example.telegramclone.ui.fragments.message_recycler_view.views.MessageView
import com.example.telegramclone.utilities.asTime
import kotlinx.android.synthetic.main.message_item_image.view.*
import kotlinx.android.synthetic.main.message_item_voice.view.*

class HolderVoiceMessage(view: View) : RecyclerView.ViewHolder(view) {
    val blockRecievedVoiceMessage: ConstraintLayout = view.block_recieved_voice_message
    val chatRecievedVoiceMessageTime: TextView = view.chat_recieved_voice_message_time

    val blockUserVoiceMessage: ConstraintLayout = view.block_user_voice_message
    val chatUserVoiceMessageTime: TextView = view.chat_user_voice_message_time

    val chatRecievedBtnPlay: ImageView = view.chat_recieved_btn_play
    val chatRecievedBtnStop: ImageView = view.chat_recieved_btn_stop

    val chatUserBtnPlay: ImageView = view.chat_user_btn_play
    val chatUserBtnStop: ImageView = view.chat_user_btn_stop

    fun drawMessageVoice(view: MessageView) {
        if (view.from == CURRENT_UID) {
            blockUserVoiceMessage.visibility = View.VISIBLE
            blockRecievedVoiceMessage.visibility = View.GONE
            chatUserVoiceMessageTime.text = view.timeStamp.asTime()
        } else {
            blockUserVoiceMessage.visibility = View.GONE
            blockRecievedVoiceMessage.visibility = View.VISIBLE
            chatRecievedVoiceMessageTime.text = view.timeStamp.asTime()
        }
    }
}