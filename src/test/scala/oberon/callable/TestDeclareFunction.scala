package oberon.callable

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter
import oberon.Environment._

import oberon.command.{BlockCommand, CallableDeclaration, Return}
import oberon.expression.{AddExpression, TInt}

class TestDeclareFunction extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "a function declaration"

  before {
    clear()
  }

  // function soma(int x, y){
  //    return x + y
  //  }
  it should "lookupCallable should return the function declaration" in {

    val undef = oberon.expression.Undefined()

    val retCommand = new Return(new AddExpression(undef, undef))
    val c = new BlockCommand(List(retCommand))

    var function = new Function("soma", TInt(), List(("x", TInt()), ("y", TInt())), c)
    val somaDeclaracao = new CallableDeclaration(function.id, function)

    somaDeclaracao.run()

    val res = lookupCallable("soma")
    res match {
      case Some(f) => f should be (function)
      case _       => print("Error")
    }
  }
}

