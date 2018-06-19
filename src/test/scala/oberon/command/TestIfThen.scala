package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

import oberon.Environment._
import oberon.expression.{IntValue, DifExpression, TInt}
import oberon.callable.Variable

class TestIfThen extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "an if-then command"

  // y := 5
  // if (10 != 1) y := 5
  it should "lookup(y) should be 7 after if successful" in {
    var y = new VariableDefinition(new Variable("y", TInt(), IntValue(5)))
    y.run()

    var ifAssignment = new Assignment("y", IntValue(7))
    var ifExpr = new DifExpression(IntValue(10), IntValue(1))

    var _ifthen = new IfThen(ifExpr, ifAssignment)
    _ifthen.run()

    val res = lookup("y")
    res match {
      case Some(v) => v.asInstanceOf[Variable].dataValue.eval() should be (IntValue(7))
      case _       => println("Erros")
    }

  }

}