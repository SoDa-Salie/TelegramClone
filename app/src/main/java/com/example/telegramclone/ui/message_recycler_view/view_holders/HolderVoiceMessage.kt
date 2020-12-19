package com.example.telegramclone.ui.message_recycler_view.view_holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.telegramclone.database.CURRENT_UID
import com.example.telegramclone.ui.message_recycler_view.views.MessageView
import com.example.telegramclone.utilities.AppVoicePlayer
import com.example.telegramclone.utilities.asTime
import kotlinx.android.synthetic.main.message_item_voice.view.*

class HolderVoiceMessage(view: View) : RecyclerView.ViewHolder(view), MessageHolder {
    private val mAppVoicePlayer = AppVoicePlayer()

    private val blockRecievedVoiceMessage: ConstraintLayout = view.block_recieved_voice_message
    private val chatRecievedVoiceMessageTime: TextView = view.chat_recieved_voice_message_time

    private val blockUserVoiceMessage: ConstraintLayout = view.block_user_voice_message
    private val chatUserVoiceMessageTime: TextView = view.chat_user_voice_message_time

    private val chatRecievedBtnPlay: ImageView = view.chat_recieved_btn_play
    private val chatRecievedBtnStop: ImageView = view.chat_recieved_btn_stop

    private val chatUserBtnPlay: ImageView = view.chat_user_btn_play
    private val chatUserBtnStop: ImageView = view.chat_user_btn_stop

    override fun drawMessage(view: MessageView) {
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

    override fun onAttach(view: MessageView) {
        mAppVoicePlayer.init()
        if (view.from == CURRENT_UID) {
            chatUserBtnPlay.setOnClickListener {
                chatUserBtnPlay.visibility = View.GONE
                chatUserBtnStop.visibility = View.VISIBLE
                chatUserBtnStop.setOnClickListener {
                    stop {
                        chatUserBtnStop.setOnClickListener(null)
                        chatUserBtnPlay.visibility = View.VISIBLE
                        chatUserBtnStop.visibility = View.GONE
                    }
                }
                play(view) {
                    chatUserBtnPlay.visibility = View.VISIBLE
                    chatUserBtnStop.visibility = View.GONE
                }
            }
        } else {
            chatRecievedBtnPlay.setOnClickListener {
                chatRecievedBtnPlay.visibility = View.GONE
                chatRecievedBtnStop.visibility = View.VISIBLE
                chatRecievedBtnStop.setOnClickListener {
                    stop {
                        chatRecievedBtnStop.setOnClickListener(null)
                        chatRecievedBtnPlay.visibility = View.VISIBLE
                        chatRecievedBtnStop.visibility = View.GONE
                    }
                }
                play(view) {
                    chatRecievedBtnPlay.visibility = View.VISIBLE
                    chatRecievedBtnStop.visibility = View.GONE
                }
            }
        }
    }

    private fun stop(function: () -> Unit) {
        mAppVoicePlayer.stop {
            function()
        }
    }

    private fun play(
        view: MessageView,
        function: () -> Unit
    ) {
        mAppVoicePlayer.play(view.id, view.fileUrl) {
            function()
        }
    }

    override fun onDetach() {
        chatRecievedBtnPlay.setOnClickListener(null)
        chatUserBtnPlay.setOnClickListener(null)
        mAppVoicePlayer.release()
    }
}