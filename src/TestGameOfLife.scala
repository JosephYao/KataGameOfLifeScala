import org.scalatest.FunSuite

class TestGameOfLife extends FunSuite {
  
  test ("no cell is live if game starts with none") {
    val game = new GameOfLife
    assert(!game.isCellAlive(0, 0))
  }

}
