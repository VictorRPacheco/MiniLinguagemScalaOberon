package oberon.callable

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter
import oberon.Environment._
import oberon.command.{Assignment, BlockCommand, While}
import oberon.expression.{AddExpression, IntValue, SmallerEqExpression, VarRef}

class TestDeclareProcedure extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "a procedure declaration"

  before {
    clearSymbolsTable()
    clearDeclarations()
    clearExecutionStack()
  }

  // procedure sumPlus5 (x, y: int, z: int) {
  //    soma = x + y
  //    soma = soma + 5
  //    z = soma
  // }
  it should "lookup should return the function declaration" in {

    val somaXY = new Assignment("soma",new AddExpression(new VarRef("x"), new VarRef("y")))
    val soma5 = new Assignment("soma", new AddExpression(new VarRef("soma"), IntValue(5)))
    val retorno = new Assignment("z", new VarRef("soma"))

    val blockCmds = new BlockCommand(List(somaXY, soma5, retorno))
    var procedure = new Procedure("sumPlus5", List(("x", IntValue(0)), ("y", IntValue(0))), blockCmds, ("z", IntValue(0)))
    mapTable("sumPlus5", procedure)

    val res = lookupTable("sumPlus5")
    res match {
      case Some(p) => p should be (procedure)
      case _       => print("Error")
    }

    val res2 = lookup("x")
    res2 match {
      case Some(c) => c should be (IntValue(0))
      case _ => print("Error")
    }

  }

}
