package com.walletconnect.chat.client

import androidx.annotation.Keep
import com.walletconnect.android.CoreClient
import com.walletconnect.android.cacao.SignatureInterface

object Chat {
    sealed interface Listeners {
        fun onError(error: Model.Error)

        interface PublicKeyOnSuccess : Listeners {
            fun onSuccess(publicKey: String)
        }

        interface Resolve : PublicKeyOnSuccess
        interface Register : PublicKeyOnSuccess

        interface RegisterIdentity: PublicKeyOnSuccess {
            fun onSign(message: String): Model.Cacao.Signature
        }
    }


    sealed class Model {
        data class Error(val throwable: Throwable) : Model()

        data class ConnectionState(val isAvailable: Boolean) : Model()

        @JvmInline
        value class AccountId(val value: String)

        data class Invite(
            val account: AccountId,
            val message: String,
            val publicKey: String,
            val signature: String? = null,
        ) : Model()

        data class Media(
            val type: String,
            val data: String,
        ) : Model()

        data class Thread(
            val topic: String,
            val selfAccount: AccountId,
            val peerAccount: AccountId,
        ) : Model()

        data class Message(
            val message: String,
            val authorAccount: AccountId,
            val timestamp: Long,
            val media: Media?,
        ) : Model()

        sealed class Events : Model() {
            data class OnInvite(val id: Long, val invite: Invite) : Events()

            data class OnJoined(val topic: String) : Events()

            data class OnReject(val topic: String) : Events()

            data class OnMessage(val topic: String, val message: Message) : Events()

            data class OnLeft(val topic: String) : Events()
        }

        data class Cacao(
            val header: Header,
            val payload: Payload,
            val signature: Signature,
        ) : Model() {
            @Keep
            data class Signature(override val t: String, override val s: String, override val m: String? = null) : Model(), SignatureInterface
            data class Header(val t: String) : Model()
            data class Payload(
                val iss: String,
                val domain: String,
                val aud: String,
                val version: String,
                val nonce: String,
                val iat: String,
                val nbf: String?,
                val exp: String?,
                val statement: String?,
                val requestId: String?,
                val resources: List<String>?,
            ) : Model()
        }
    }

    sealed class Params {


        data class Init(val core: CoreClient) : Params()

        data class Register(val account: Model.AccountId, val private: Boolean? = false) : Params()

        data class Resolve(val account: Model.AccountId) : Params()

        data class Invite(val account: Model.AccountId, val invite: Model.Invite) : Params()

        data class Accept(val inviteId: Long) : Params()

        data class Reject(val inviteId: Long) : Params()

        data class Message(val topic: String, val author: Model.AccountId, val message: String, val media: Model.Media? = null) : Params()

        data class Ping(val topic: String) : Params()

        data class Leave(val topic: String) : Params()

        data class AddContact(val account: Model.AccountId, val publicKey: String) : Params()

        data class GetInvites(val account: Model.AccountId) : Params()

        data class GetThreads(val account: Model.AccountId) : Params()

        data class GetMessages(val topic: String) : Params()

        // Added with new keyserver
        data class RegisterIdentity(val account: Model.AccountId, val private: Boolean? = false) : Params()
    }
}