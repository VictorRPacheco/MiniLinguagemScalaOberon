package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter
import oberon.Environment._
import oberon.callable._
import oberon.command._
import oberon.expression.Value

class TestFunctionExpression extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {


  behavior of "a function expression"

  // function soma (x, y: int) {
  //    return x + y
  // }
  //
  // soma(2, 5)
  it should "lookup should return value 7" in {

    val undef = oberon.expression.Undefined()

    val somaXY = new Return(new AddExpression(new VarRef("x"), new VarRef("y")))
    val cmds = new BlockCommand(List(somaXY))

    var function = new Function("soma", List(("x", undef), ("y", undef)), cmds)
    val functionDeclaration = new CallableDeclaration(function.id, function)
    functionDeclaration.run()

    val functionCall = new FunctionExpression("soma", List(("x", IntValue(2)), ("y", IntValue(5))))

    functionCall.eval() should be(IntValue(7))

  }
}