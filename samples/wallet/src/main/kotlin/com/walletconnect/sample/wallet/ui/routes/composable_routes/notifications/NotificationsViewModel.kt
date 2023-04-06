package com.walletconnect.sample.wallet.ui.routes.composable_routes.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walletconnect.push.common.Push
import com.walletconnect.push.wallet.client.PushWalletClient
import com.walletconnect.sample.wallet.domain.PushWalletDelegate
import com.walletconnect.sample.wallet.domain.model.PushNotification
import com.walletconnect.sample.wallet.domain.model.toPushNotification
import kotlinx.coroutines.flow.*

class NotificationsViewModel : ViewModel() {

    private val notifications = MutableStateFlow<List<PushNotification>>(listOf())

    val pushEvents = PushWalletDelegate.wcPushEventModels.map { pushEvent ->
        when (pushEvent) {
            is Push.Wallet.Event.MessageRecord -> notifications.addNewNotification(pushEvent)
            is Push.Wallet.Event.Delete -> getMessageHistory()
            else -> Unit
        }
    }.shareIn(viewModelScope, started = SharingStarted.Eagerly)

    val notificationsState = notifications.map {
        if (it.isEmpty()) {
            NotificationsState.Empty
        } else {
            NotificationsState.Success(it)
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, NotificationsState.Empty)

    fun getMessageHistory() {
        val messages: MutableList<Push.Model.MessageRecord> = mutableListOf()
        PushWalletClient.getActiveSubscriptions().forEach { subscriptions ->
            PushWalletClient.getMessageHistory(Push.Wallet.Params.MessageHistory(subscriptions.key))
                .let {
                    messages.addAll(it.values)
                }
        }
        notifications.value = messages.map { it.toPushNotification() }
    }

    fun deleteNotification(push: PushNotification) {
        PushWalletClient.deletePushMessage(
            Push.Wallet.Params.DeleteMessage(push.id.toLong()),
            onSuccess = {
                notifications.deleteNotification(push)
            },
            onError = {
                getMessageHistory()
            })
    }

    private fun MutableStateFlow<List<PushNotification>>.addNewNotification(push: Push.Wallet.Event.MessageRecord) {
        value = listOf(push.toPushNotification()) + value
    }

    private fun MutableStateFlow<List<PushNotification>>.deleteNotification(push: PushNotification) {
        value = value - push
    }
}

sealed class NotificationsState {
    object Empty : NotificationsState()
    data class Success(val notifications: List<PushNotification>) : NotificationsState()
}