package ru.otus.cryptosample.coins.feature.adapter

import androidx.recyclerview.widget.DiffUtil

object CoinDiff : DiffUtil.ItemCallback<CoinsAdapterItem>() {

    override fun areItemsTheSame(oldItem: CoinsAdapterItem, newItem: CoinsAdapterItem): Boolean =
        when {
            oldItem is CoinsAdapterItem.CategoryHeader &&
                    newItem is CoinsAdapterItem.CategoryHeader ->
                oldItem.categoryName == newItem.categoryName

            oldItem is CoinsAdapterItem.CoinItem &&
                    newItem is CoinsAdapterItem.CoinItem ->
                oldItem.coin.id == newItem.coin.id

            oldItem is CoinsAdapterItem.Carousel &&
                    newItem is CoinsAdapterItem.Carousel ->
                oldItem.coins.firstOrNull()?.id == newItem.coins.firstOrNull()?.id

            else -> false
        }

    override fun areContentsTheSame(oldItem: CoinsAdapterItem, newItem: CoinsAdapterItem): Boolean =
        oldItem == newItem

    override fun getChangePayload(oldItem: CoinsAdapterItem, newItem: CoinsAdapterItem): Any? {
        return when (oldItem) {
            is CoinsAdapterItem.CoinItem -> {
                val oldCoin = oldItem.coin
                val newCoin = (newItem as CoinsAdapterItem.CoinItem).coin
                if (oldCoin.highlight != newCoin.highlight) {
                    Payload.HOT_MOVER_CHANGED
                } else {
                    null
                }
            }

            else -> null
        }
    }

    object Payload {
        const val HOT_MOVER_CHANGED = "HOT_MOVER_CHANGED"
    }
}