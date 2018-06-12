package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

import oberon.Environment._
import oberon.command._
import oberon.expression._

class TestReadBool extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "a read bool command"

  it should "the environment must have a readBool() and show it on the screen" in {
    //val readBool = new ReadBool()
    //println(readBool.run())
  }
}