package oberon

import oberon.callable._
import oberon.command.{BlockCommand, CallableDeclaration, Return, VariableDefinition}
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter
import oberon.expression._
import oberon.visitor.PrettyPrinter
import oberon.Environment._

class TestPrettyPrinter extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "a pretty printer"

  it should "print \"(5 + 10)\" when we call accept in such an AddExpression" in {
    val val5  = IntValue(5)
    val val10 = IntValue(10)
    val add   = new AddExpression(val5, val10)

    val pp = new PrettyPrinter()

    add.accept(pp)
    pp.str should be ("(5 + 10)")
  }

  it should "print \"(5 - 10)\" when we call accept in such a MinusExpression" in {
    val val5  = IntValue(5)
    val val10 = IntValue(10)
    val minus = new MinusExpression(val5, val10)

    val pp = new PrettyPrinter()

    minus.accept(pp)
    pp.str should be ("(5 - 10)")
  }

  it should "print \"(5 * 10)\" when we call accept in such a MultExpression" in {
    val val5  = IntValue(5)
    val val10 = IntValue(10)
    val mult  = new MultExpression(val5, val10)

    val pp = new PrettyPrinter()

    mult.accept(pp)
    pp.str should be ("(5 * 10)")
  }

  it should "print \"(5 / 10)\" when we call accept in such a DivideExpression" in {
    val val5  = IntValue(5)
    val val10 = IntValue(10)
    val div   = new DivideExpression(val5, val10)

    val pp = new PrettyPrinter()

    div.accept(pp)
    pp.str should be ("(5 / 10)")
  }

  it should "print \"(5 <= 10)\" when we call accept in such a SmallerEqExpression" in {
    val val5  = IntValue(5)
    val val10 = IntValue(10)
    val SmallerEqEx   = new SmallerEqExpression(val5, val10)

    val pp = new PrettyPrinter()

    SmallerEqEx.accept(pp)
    pp.str should be ("(5 <= 10)")
  }

  it should "print \"(5 < 10)\" when we call accept in such a SmallerExpression" in {
    val val5  = IntValue(5)
    val val10 = IntValue(10)
    val SmallerEx   = new SmallerExpression(val5, val10)

    val pp = new PrettyPrinter()

    SmallerEx.accept(pp)
    pp.str should be ("(5 < 10)")
  }

  it should "print \"(5 > 10)\" when we call accept in such a BiggerExpression" in {
    val val5  = IntValue(5)
    val val10 = IntValue(10)
    val BiggerEx   = new BiggerExpression(val5, val10)

    val pp = new PrettyPrinter()

    BiggerEx.accept(pp)
    pp.str should be ("(5 > 10)")
  }

  it should "print \"(5 >= 10)\" when we call accept in such a BiggerEqExpression" in {
    val val5  = IntValue(5)
    val val10 = IntValue(10)
    val BiggerEqEx   = new BiggerEqExpression(val5, val10)

    val pp = new PrettyPrinter()

    BiggerEqEx.accept(pp)
    pp.str should be ("(5 >= 10)")
  }

  it should "print \"(5 == 10)\" when we call accept in such an EqExpression" in {
    val val5  = IntValue(5)
    val val10 = IntValue(10)
    val EqEx   = new EqExpression(val5, val10)

    val pp = new PrettyPrinter()

    EqEx.accept(pp)
    pp.str should be ("(5 == 10)")
  }

  it should "print \"(5 != 10)\" when we call accept in such an DifExpression" in {
    val val5  = IntValue(5)
    val val10 = IntValue(10)
    val DifEx   = new DifExpression(val5, val10)

    val pp = new PrettyPrinter()

    DifEx.accept(pp)
    pp.str should be ("(5 != 10)")
  }

  it should "print \"(5 || 10)\" when we call accept in such an OrExpression" in {
    val val5  = IntValue(5)
    val val10 = IntValue(10)
    val OrEx   = new OrExpression(val5, val10)

    val pp = new PrettyPrinter()

    OrEx.accept(pp)
    pp.str should be ("(5 || 10)")
  }

  it should "print \"(5 & 10)\" when we call accept in such an AndExpression" in {
    val val5 = IntValue(5)
    val val10 = IntValue(10)
    val AndEx = new AndExpression(val5, val10)

    val pp = new PrettyPrinter()

    AndEx.accept(pp)
    pp.str should be("(5 & 10)")
  }


  it should "print \"(!true)\" when we call accept in such an NotExpression" in {
    val valTrue  = BoolValue(true)
    val NotEx   = new NotExpression(valTrue)

    val pp = new PrettyPrinter()

    NotEx.accept(pp)
    pp.str should be ("(!true)")
  }

  it should "print \"(5 != 10)\" when we call accept in such an NotExpression" in {
    val valTrue  = BoolValue(true)
    val NotEx   = new NotExpression(valTrue)

    val pp = new PrettyPrinter()

    NotEx.accept(pp)
    pp.str should be ("(!true)")
  }

  it should "print false when we call accept in such a value" in {
    clear()
    val boolValue = BoolValue(false)
    val variableDefinition = new VariableDefinition(new Variable ("bol", TBool(), boolValue))
    val pp = new PrettyPrinter()
    boolValue.accept(pp)

    pp.str should be("false")

    val newPP = new PrettyPrinter()
    variableDefinition.accept(newPP)
    newPP.str should be ("Boolean bol = BoolValue(false)")

  }

  it should "print our function when we call accept in such a value" in {

    val somaXY = new Return(new AddExpression(new VarRef("x"), new VarRef("y")))
    val cmds = new BlockCommand(List(somaXY))

    var function = new Function("soma", TInt(), List(("x", TInt()), ("y", TInt())), cmds)
    val functionDeclaration = new CallableDeclaration(function.id, function)
    functionDeclaration.run()

    val functionCall = new FunctionExpression("soma", List(IntValue(2), IntValue(5)))
    val pp = new PrettyPrinter()
    functionCall.accept(pp)
    pp.str should be ("soma(2, 5)")

  }

}