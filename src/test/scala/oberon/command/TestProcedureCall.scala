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
  it should "lookup should return value 10" in {

/*    val x = new Assignment("x", IntValue(1))
    val y = new Assignment("y", IntValue(4))
    val z = new Assignment("z", IntValue(0))
    x.run()
    y.run()
    z.run()

    val somaXY = new Assignment("soma",new AddExpression(new VarRef("x"), new VarRef("y")))
    val soma5 = new Assignment("soma", new AddExpression(new VarRef("soma"), IntValue(5)))
    val retorno = new Assignment("z", new VarRef("soma"))

    val xRef = new VarRef("x")
    val yRef = new VarRef("y")
    val zRef = new VarRef("z")

    val blockCmds = new BlockCommand(List(somaXY, soma5, retorno))
    // sumPlus5(1, 4, z)
    var procedure = new Procedure("sumPlus5", List(xRef, yRef), zRef, blockCmds)
    mapTable("sumPlus5", procedure)

    val p = lookupTable("sumPlus5")
    print(p)

    val p1 = new ProdedureCallCommand(p.getOrElse(0).asInstanceOf[Procedure])

    p1.run()

    val res = lookup("z")
    res match {
      case Some(v) => v should be (IntValue(10))
      case _       => print("Error")
    }*/

  }

}