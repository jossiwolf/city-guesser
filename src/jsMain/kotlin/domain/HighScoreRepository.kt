package domain

import kotlinx.browser.window
import org.w3c.dom.Storage
import org.w3c.dom.get
import org.w3c.dom.set

actual fun HighScoreRepository(): HighScoreRepository = LocalStorageBasedHighScoreRepository(window.localStorage)

class LocalStorageBasedHighScoreRepository(private val storage: Storage): HighScoreRepository {
    override suspend fun getHighScore(): Int = storage[HIGH_SCORE_KEY]?.toInt() ?: 0

    override suspend fun saveHighScore(score: Int) {
        storage.set(key = HIGH_SCORE_KEY, value = score.toString())
    }

    companion object {
        const val HIGH_SCORE_KEY = "cg_highscore"
    }
}