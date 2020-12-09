package com.example.telegramclone.ui.fragments.single_chat

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.telegramclone.database.CURRENT_UID
import com.example.telegramclone.ui.fragments.message_recycler_view.view_holders.AppHolderFactory
import com.example.telegramclone.ui.fragments.message_recycler_view.view_holders.HolderImageMessage
import com.example.telegramclone.ui.fragments.message_recycler_view.view_holders.HolderTextMessage
import com.example.telegramclone.ui.fragments.message_recycler_view.view_holders.HolderVoiceMessage
import com.example.telegramclone.ui.fragments.message_recycler_view.views.MessageView
import com.example.telegramclone.utilities.asTime
import com.example.telegramclone.utilities.downloadAndSetImage

class SingleChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mListMessagesCache = mutableListOf<MessageView>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AppHolderFactory.getHolder(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return mListMessagesCache[position].getTypeView()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is HolderImageMessage -> holder.drawMessageImage(mListMessagesCache[position])
            is HolderTextMessage -> holder.drawMessageText(mListMessagesCache[position])
            is HolderVoiceMessage -> holder.drawMessageVoice(mListMessagesCache[position])
            else -> {}
        }
    }







    override fun getItemCount(): Int = mListMessagesCache.size

    fun addItemToTop(
        item: MessageView,
        onSuccess: () -> Unit
    ) {
        if (!mListMessagesCache.contains(item)) {
            mListMessagesCache.add(item)
            mListMessagesCache.sortBy { it.timeStamp }
            notifyItemInserted(0)
        }
        onSuccess()
    }

    fun addItemToBottom(
        item: MessageView,
        onSuccess: () -> Unit
    ) {
        if (!mListMessagesCache.contains(item)) {
            mListMessagesCache.add(item)
            notifyItemInserted(mListMessagesCache.size)
        }
        onSuccess()
    }

}


