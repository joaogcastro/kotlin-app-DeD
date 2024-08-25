fun main() {
    // Player builder instance
    val playerBuilder = PlayerBuilder()

    // Create your player
    val myPlayer = playerBuilder.create()

    // Show player information
    playerBuilder.status(myPlayer)
}