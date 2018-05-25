package oberon.command

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter
import oberon.Environment._
import oberon.callable._
import oberon.expression.{AddExpression, IntValue, VarRef}

class TestProcedureCall extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  before {
    clearDeclarations()
    clearExecutionStack()
    clearSymbolsTable()
  }

  behavior of "a procedure call"


  // procedure sumPlus5 (x, y: int, z: int) {
  //    soma = x + y
  //    soma = soma + 5
  //    z = soma
  // }
  //
  // sumPlus5(2, 5, z)
  it should "lookup should return value 10" in {

    // creating the procedure
    val somaXY = new Assignment("soma",new AddExpression(new VarRef("x"), new VarRef("y")))
    val soma5 = new Assignment("soma", new AddExpression(new VarRef("soma"), IntValue(5)))
    val zSoma= new Assignment("z", new VarRef("soma"))

    val blockCmds = new BlockCommand(List(somaXY, soma5, zSoma))
    var procedure = new Procedure("sumPlus5", List(("x", IntValue(0)), ("y", IntValue(0))), blockCmds, ("z", IntValue(0)))
    mapTable("sumPlus5", procedure)

    // new reference to a callable
    // sumPlus5(x = 2, y = 3, z)
    val p = new CallableRef("sumPlus5")

    val p1 = new ProcedureCall(p.eval().asInstanceOf[Procedure], List(("x", IntValue(2)), ("y", IntValue(3))))
    p1.run()

    val res = lookup("z")
    res match {
      case Some(v) => v should be (IntValue(10))
      case _       => print("Error")
    }

    executionStack.pop()

  }

}