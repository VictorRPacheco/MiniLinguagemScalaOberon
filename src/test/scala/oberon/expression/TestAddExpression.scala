package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


class TestAddExpression extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "an add expressions"

  it should "return value 15 in Add(IntValue(5), IntValue(10))" in {
    val val5  = IntValue(5)
    val val10 = IntValue(10)
    val res   = new AddExpression(val5, val10)

    res.eval() should be (IntValue(15))
  }

  it should "lead to an exception in Add(IntValue(5), BoolValue(False))" in {
    val val5 = IntValue(5)
    val valf = BoolValue(false)
    val res = new AddExpression(val5, valf)

   // res.eval() should be (IntValue(5))
  }
}
