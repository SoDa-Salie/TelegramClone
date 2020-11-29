package com.example.telegramclone.ui.fragments.single_chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.telegramclone.R
import com.example.telegramclone.models.CommonModel
import com.example.telegramclone.database.CURRENT_UID
import com.example.telegramclone.utilities.asTime
import kotlinx.android.synthetic.main.message_item.view.*

class SingleChatAdapter : RecyclerView.Adapter<SingleChatAdapter.SingleChatHolder>() {

    var mListMessagesCache = emptyList<CommonModel>()

    class SingleChatHolder(view: View) : RecyclerView.ViewHolder(view) {
        val blockUserMessage: ConstraintLayout = view.block_user_message
        val chatUserMessage: TextView = view.chat_user_message
        val chatUserMessageTime: TextView = view.chat_user_message_time

        val blockRecievedMessage: ConstraintLayout = view.block_recieved_message
        val chatRecievedMessage: TextView = view.chat_recieved_message
        val chatRecievedMessageTime: TextView = view.chat_recieved_message_time
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleChatHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        return SingleChatHolder(view)
    }

    override fun onBindViewHolder(holder: SingleChatHolder, position: Int) {
        if (mListMessagesCache[position].from == CURRENT_UID) {
            holder.blockUserMessage.visibility = View.VISIBLE
            holder.blockRecievedMessage.visibility = View.GONE
            holder.chatUserMessage.text = mListMessagesCache[position].text
            holder.chatUserMessageTime.text =
                mListMessagesCache[position].timeStamp.toString().asTime()
        } else {
            holder.blockUserMessage.visibility = View.GONE
            holder.blockRecievedMessage.visibility = View.VISIBLE
            holder.chatRecievedMessage.text = mListMessagesCache[position].text
            holder.chatRecievedMessageTime.text =
                mListMessagesCache[position].timeStamp.toString().asTime()
        }
    }

    override fun getItemCount(): Int = mListMessagesCache.size

    fun setList(list: List<CommonModel>) {
        mListMessagesCache = list
        notifyDataSetChanged()
    }
}


