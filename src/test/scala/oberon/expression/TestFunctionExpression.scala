package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter
import oberon.Environment._
import oberon.callable._
import oberon.command._

class TestFunctionExpression extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {
  before {
    clearDeclarations()
    clearExecutionStack()
    clearSymbolsTable()
  }

  behavior of "a function expression"

  // function sumPlus5 (x, y: int, z: int) {
  //    soma = x + y
  //    soma = soma + 5
  //    z = soma
  //    return z
  // }
  //
  // sumPlus5(2, 5, z)
  it should "lookup should return value 10" in {

    // creating the procedure
    val somaXY = new Assignment("soma",new AddExpression(new VarRef("x"), new VarRef("y")))
    val soma5 = new Assignment("soma", new AddExpression(new VarRef("soma"), IntValue(5)))
    val zSoma= new Assignment("z", new VarRef("soma"))
    val retorno = new Return("z", new VarRef("z"))

    val blockCmds = new BlockCommand(List(somaXY, soma5, zSoma, retorno))
    var function = new Function("sumPlus5", List(("x", IntValue(0)), ("y", IntValue(0))), blockCmds, new VarRef("z"))
    mapTable("sumPlus5", function)

    // new reference to a callable
    // y = sumPlus5(x = 2, y = 3)
    val f = new CallableRef("sumPlus5")
    val f1 = new FunctionExpression(f.eval(), List(("x", IntValue(2)), ("y", IntValue(3))))
    var vAssign = new Assignment("v", f1.eval())
    vAssign.run()

    val res = lookup("z")
    res match {
      case Some(v) => v should be (IntValue(10))
      case _       => print("Error")
    }

    val res2 = lookup("v")
    res2 match {
      case Some(v) => v should be (IntValue(10))
      case _       => print("Error")
    }

  }

}