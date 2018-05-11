package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

import oberon.Environment._

class TestReadInt extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "a read int command"

  before {
    clear()
  }

  it should "the environment must have a readInt() and show it on the screen" in {
    //val readInt = new ReadInt()
    //val value = readInt.run()
    //println(value.asInstanceOf[Integer])
  }
}
