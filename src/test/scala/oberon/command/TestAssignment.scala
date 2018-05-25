package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


import oberon.Environment._
import oberon.expression.IntValue

class TestAssignment extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "an assignment command"

  before {
    clearDeclarations()
    clearExecutionStack()
    clearSymbolsTable()
  }

  it should "the environment must have an assignment x -> 5" in { 
    val assignment = new Assignment("x", IntValue(5))

    assignment.run()

    lookup("x") should be (Some(IntValue(5))) 
  }
}
