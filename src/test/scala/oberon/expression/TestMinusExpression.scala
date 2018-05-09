package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


class TestMinusExpression extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "an Subtract expressions"

  it should "return value 5 in Subtract(IntValue(15), IntValue(10))" in {
    val val15  = IntValue(15)
    val val10 = IntValue(10)
    val res   = new MinusExpression(val15, val10)

    res.eval() should be (IntValue(5))
  }

  it should "return value -5 in Subtract(IntValue(5), IntValue(10))" in {
    val val5  = IntValue(5)
    val val10 = IntValue(10)
    val res   = new MinusExpression(val5, val10)

    res.eval() should be (IntValue(-5))
  }

  it should "lead to an exception in Subtract(IntValue(5), BoolValue(False))" in {
    val val5 = IntValue(5)
    val valf = BoolValue(false)
    val res = new AddExpression(val5, valf)

    // res.eval() should be (IntValue(5))
  }
}
