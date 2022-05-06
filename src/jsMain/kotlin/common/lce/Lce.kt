package common.lce

sealed interface Lce<out T> {
    object Loading: Lce<Nothing>
    data class Content<T>(val data: T): Lce<T>
}