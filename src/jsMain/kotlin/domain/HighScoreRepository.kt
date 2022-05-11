package domain

import kotlinx.browser.window
import org.w3c.dom.get
import org.w3c.dom.set

@JsName("IHighScoreRepository")
interface HighScoreRepository {
    suspend fun getHighScore(): Int
    suspend fun saveHighScore(score: Int)
}

fun HighScoreRepository() = LocalStorageBasedHighScoreRepository()

class LocalStorageBasedHighScoreRepository: HighScoreRepository {
    override suspend fun getHighScore(): Int = window.localStorage[HIGH_SCORE_KEY]?.toInt() ?: 0

    override suspend fun saveHighScore(score: Int) {
        window.localStorage.set(key = HIGH_SCORE_KEY, value = score.toString())
    }

    companion object {
        const val HIGH_SCORE_KEY = "cg_highscore"
    }
}