import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import races.*

class PlayerBuilderTest {

    private lateinit var playerBuilder: PlayerBuilder
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        playerBuilder = PlayerBuilder()
        player = Player()
    }

    @Test
    fun `test setRaceModifiers Tiefling`() {
        player.abilities["Strength"] = 10
        player.abilities["Dexterity"] = 12
        player.race = Tiefling()

        playerBuilder.setRaceModifiers(player)

        assertEquals(10, player.abilities["Charisma"]) //+2
        assertEquals(9, player.abilities["Intelligence"]) //+1
        assertEquals(12, player.abilities["Dexterity"]) //Same
        assertEquals(10, player.abilities["Strength"]) //Same
    }

    @Test
    fun `test setRaceModifiers Dragonborn`() {
        player.abilities["Strength"] = 8
        player.abilities["Dexterity"] = 10
        player.race = Dragonborn()

        playerBuilder.setRaceModifiers(player)

        assertEquals(10, player.abilities["Strength"]) //+2
        assertEquals(9, player.abilities["Charisma"]) //+1
        assertEquals(10, player.abilities["Dexterity"]) //Same
    }


    @Test
    fun `test setHealthPoints`() {
        player.abilities["Constitution"] = 9
        player.hitDie = 10

        playerBuilder.setHealthPoints(player)

        assertEquals(9, player.healthPoints)
    }

    @Test
    fun `test constitutionModifier`() {
        assertEquals(-1, playerBuilder.constitutionModifier(8))
        assertEquals(-1, playerBuilder.constitutionModifier(9))
        assertEquals(0, playerBuilder.constitutionModifier(10))
        assertEquals(1, playerBuilder.constitutionModifier(12))
        assertEquals(4, playerBuilder.constitutionModifier(18))
    }
}
