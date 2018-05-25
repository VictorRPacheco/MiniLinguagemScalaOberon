package oberon.callable

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter
import oberon.Environment._
import oberon.command.{Assignment, BlockCommand, While}
import oberon.expression.{AddExpression, IntValue, SmallerEqExpression, VarRef}

class TestDeclareFunction extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "a function declaration"

  before {
    clearDeclarations()
    clearExecutionStack()
    clearSymbolsTable()
  }

  it should "lookup should return the function declaration" in {

    val somaXY = new Assignment("soma",new AddExpression(new VarRef("x"), new VarRef("y")))
    val soma5 = new Assignment("soma", new AddExpression(new VarRef("soma"), IntValue(5)))
    val retorno = new Assignment("z", new VarRef("soma"))

    val blockCmds = new BlockCommand(List(somaXY, soma5, retorno))
    var function = new Function("sumPlus5", List(("x", IntValue(0)), ("y", IntValue(0))), blockCmds, IntValue(1))
    mapTable("sumPlus5", function)

    val res = lookupTable("sumPlus5")
    res match {
      case Some(f) => f should be (function)
      case _       => print("Error")
    }

    val res2 = lookup("x")
    res2 match {
      case Some(c) => c should be (IntValue(0))
      case _ => print("Error")
    }
  }
}

