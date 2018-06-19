package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter
import oberon.Environment._
import oberon.callable.Variable
import oberon.expression.{TInt, IntValue, DifExpression}

class TestIfThenElse extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "an if-then-else command"

  // y := 5;
  // if (10 != 10) {
  //   y := 7
  // else {
  //   y := 0
  // }
  // end
  it should "lookup(y) should be equal to 0 after entering else instead of if" in {
    var y = new VariableDefinition(new Variable("y", TInt(), IntValue(5)))
    y.run()

    var ifAssignment = new Assignment("y", IntValue(7))
    var elseAssignment = new Assignment("y", IntValue(0))
    var ifExpr = new DifExpression(IntValue(10), IntValue(10))

    var _ifthen = new IfElse(ifExpr, ifAssignment, elseAssignment)
    _ifthen.run()

    val res = lookup("y")
    res match {
      case Some(v) => v.asInstanceOf[Variable].dataValue.eval() should be (IntValue(0))
      case _       => println("Erros")
    }

  }

}
