package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


import oberon.Environment._
import oberon.callable.Variable
import oberon.expression.IntValue

class TestAssignment extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "an assignment command"

  before {
    clear()
  }

  it should "the environment must have an assignment x -> 5" in {
    val variableX = new VariableDefinition(new Variable("x", "Integer", IntValue(5)))
    val assignment = new Assignment("x", IntValue(10))

    variableX.run()
    assignment.run()

    val res = lookup("x")
    res match {
      case Some(v) => v.asInstanceOf[Variable].dataValue.eval() should be (IntValue(10))
      case _       => println("Erros")
    }
  }
}
