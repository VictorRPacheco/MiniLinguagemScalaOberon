package oberon

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter
import oberon.expression.{AddExpression, BiggerEqExpression, BoolValue, IntValue}
import oberon.visitor.PrettyPrinter

class TestPrettyPrinter extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "a pretty printer"

  it should "print \"(5 + 10)\" when we call accept in such an expression" in {
    val val5  = IntValue(5)
    val val10 = IntValue(10)
    val add   = new AddExpression(val5, val10)

    val pp = new PrettyPrinter()

    add.accept(pp)

    pp.str should be ("(5 + 10)")
  }

  it should "print false when we call accept in such a value" in {
    val boolValue = BoolValue(false)
    val pp = new PrettyPrinter()

    boolValue.accept(pp)

    pp.str should be("False")
  }

}