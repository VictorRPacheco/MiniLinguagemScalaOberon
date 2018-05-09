package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


class TestDivideExpression extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "an Divide expressions"

  it should "return value 2 in Divide(IntValue(20), IntValue(10))" in {
    val val20  = IntValue(20)
    val val10 = IntValue(10)
    val res   = new DivideExpression(val20, val10)

    res.eval() should be (IntValue(2))
  }

  it should "return value -5 in Divide(IntValue(-50), IntValue(10))" in {
    val val50  = IntValue(-50)
    val val10 = IntValue(10)
    val res   = new DivideExpression(val50, val10)

    res.eval() should be (IntValue(-5))
  }

  it should "return value -50 in Divide(IntValue(-500), IntValue(10))" in {
    val val500  = IntValue(-500)
    val val10 = IntValue(10)
    val res   = new DivideExpression(val500, val10)

    res.eval() should be (IntValue(-50))
  }

  it should "lead to an exception in Divide(IntValue(5), BoolValue(False))" in {
    val val5 = IntValue(5)
    val valf = BoolValue(false)
    val res = new DivideExpression(val5, valf)

    // res.eval() should be (IntValue(5))
  }
}
