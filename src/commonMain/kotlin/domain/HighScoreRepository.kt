package domain

interface HighScoreRepository {
    suspend fun getHighScore(): Int
    suspend fun saveHighScore(score: Int)
}

expect fun HighScoreRepository(): HighScoreRepository