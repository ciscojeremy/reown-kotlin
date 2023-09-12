package com.walletconnect.web3.modal.domain

import com.walletconnect.web3.modal.data.network.Web3ModalService
import com.walletconnect.web3.modal.data.network.model.WalletDTO
import com.walletconnect.web3.modal.domain.model.Wallet

internal class Web3ModalApiRepository(
    private val web3ModalApiUrl: String,
    private val web3ModalService: Web3ModalService
) {
    suspend fun fetchAllWallets(
        excludeIds: List<String> = listOf(),
        recommendedWalletsIds: List<String> = listOf()
    ): List<Wallet> {
        val exclude = excludeIds.joinToString(",")
        val wallets = mutableListOf<Wallet>()
        var page = 1
        var count: Int
        do {
            val response = web3ModalService.getWallets(page = page, exclude = exclude)
            if (response.isSuccessful && response.body() != null) {
                response.body()!!.let {
                    count = it.count
                    page++
                    wallets.addAll(it.data.toWallets(recommendedWalletsIds))
                }
            } else {
                throw Throwable(response.errorBody()?.string())
            }
        } while (wallets.size != count)

        return wallets
    }

    private fun List<WalletDTO>.toWallets(
        recommendedWallets: List<String>
    ): List<Wallet> = map { walletDTO ->
        Wallet(
            id = walletDTO.id,
            name = walletDTO.name,
            homePage = walletDTO.homePage,
            imageUrl = web3ModalApiUrl + "getWalletImage/${walletDTO.imageId}",
            order = walletDTO.order,
            mobileLink = walletDTO.mobileLink,
            playStore = walletDTO.playStore,
            isRecommended = recommendedWallets.any { walletDTO.id == it }
        )
    }
}
