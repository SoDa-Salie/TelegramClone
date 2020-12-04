package com.example.telegramclone.ui.fragments.single_chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.telegramclone.R
import com.example.telegramclone.database.CURRENT_UID
import com.example.telegramclone.models.CommonModel
import com.example.telegramclone.utilities.TYPE_MESSAGE_IMAGE
import com.example.telegramclone.utilities.TYPE_MESSAGE_TEXT
import com.example.telegramclone.utilities.asTime
import com.example.telegramclone.utilities.downloadAndSetImage
import kotlinx.android.synthetic.main.message_item.view.*

class SingleChatAdapter : RecyclerView.Adapter<SingleChatAdapter.SingleChatHolder>() {

    private var mListMessagesCache = mutableListOf<CommonModel>()

    class SingleChatHolder(view: View) : RecyclerView.ViewHolder(view) {
        //Text
        val blockUserMessage: ConstraintLayout = view.block_user_message
        val chatUserMessage: TextView = view.chat_user_message
        val chatUserMessageTime: TextView = view.chat_user_message_time

        val blockRecievedMessage: ConstraintLayout = view.block_recieved_message
        val chatRecievedMessage: TextView = view.chat_recieved_message
        val chatRecievedMessageTime: TextView = view.chat_recieved_message_time

        //Image
        val blockRecievedImageMessage: ConstraintLayout = view.block_recieved_image_message
        val chatRecievedImageMessage: ImageView = view.chat_recieved_image
        val chatRecievedImageMessageTime: TextView = view.chat_recieved_image_message_time

        val blockUserImageMessage: ConstraintLayout = view.block_user_image_message
        val chatUserImageMessage: ImageView = view.chat_user_image
        val chatUserImageMessageTime: TextView = view.chat_user_image_message_time

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleChatHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        return SingleChatHolder(view)
    }

    override fun onBindViewHolder(holder: SingleChatHolder, position: Int) {
        when (mListMessagesCache[position].type) {
            TYPE_MESSAGE_TEXT -> drawMessageText(holder, position)
            TYPE_MESSAGE_IMAGE -> drawMessageImage(holder, position)
        }
    }

    private fun drawMessageImage(holder: SingleChatHolder, position: Int) {
        holder.blockUserMessage.visibility = View.GONE
        holder.blockRecievedMessage.visibility = View.GONE

        if (mListMessagesCache[position].from == CURRENT_UID) {
            holder.blockUserImageMessage.visibility = View.VISIBLE
            holder.blockRecievedImageMessage.visibility = View.GONE
            holder.chatUserImageMessage.downloadAndSetImage(mListMessagesCache[position].imageUrl)
            holder.chatUserImageMessageTime.text = mListMessagesCache[position].timeStamp.toString().asTime()
        } else {
            holder.blockUserImageMessage.visibility = View.GONE
            holder.blockRecievedImageMessage.visibility = View.VISIBLE
            holder.chatRecievedImageMessage.downloadAndSetImage(mListMessagesCache[position].imageUrl)
            holder.chatRecievedImageMessageTime.text = mListMessagesCache[position].timeStamp.toString().asTime()
        }
    }

    private fun drawMessageText(holder: SingleChatHolder, position: Int) {
        holder.blockUserImageMessage.visibility = View.GONE
        holder.blockRecievedImageMessage.visibility = View.GONE

        if (mListMessagesCache[position].from == CURRENT_UID) {
            holder.blockUserMessage.visibility = View.VISIBLE
            holder.blockRecievedMessage.visibility = View.GONE
            holder.chatUserMessage.text = mListMessagesCache[position].text
            holder.chatUserMessageTime.text = mListMessagesCache[position].timeStamp.toString().asTime()
        } else {
            holder.blockUserMessage.visibility = View.GONE
            holder.blockRecievedMessage.visibility = View.VISIBLE
            holder.chatRecievedMessage.text = mListMessagesCache[position].text
            holder.chatRecievedMessageTime.text = mListMessagesCache[position].timeStamp.toString().asTime()
        }
    }

    override fun getItemCount(): Int = mListMessagesCache.size

    fun addItemToTop(
        item: CommonModel,
        onSuccess: () -> Unit
    ) {
        if (!mListMessagesCache.contains(item)) {
            mListMessagesCache.add(item)
            mListMessagesCache.sortBy { it.timeStamp.toString() }
            notifyItemInserted(0)
        }
        onSuccess()
    }

    fun addItemToBottom(
        item: CommonModel,
        onSuccess: () -> Unit
    ) {
        if (!mListMessagesCache.contains(item)) {
            mListMessagesCache.add(item)
            notifyItemInserted(mListMessagesCache.size)
        }
        onSuccess()
    }

}


