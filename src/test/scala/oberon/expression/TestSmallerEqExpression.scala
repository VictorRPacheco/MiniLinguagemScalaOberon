package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


class TestSmallerEqExpression extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "an SmallerEq expressions"

  it should "return value false in SmallerEq(IntValue(20), IntValue(10))" in {
    val val20  = IntValue(20)
    val val10 = IntValue(10)
    val add   = new SmallerEqExpression(val20, val10)

    add.eval() should be (BoolValue(false))
  }

  it should "return value false in SmallerEq(IntValue(10), IntValue(20))" in {
    val val10  = IntValue(10)
    val val20 = IntValue(20)
    val add   = new SmallerEqExpression(val10, val20)

    add.eval() should be (BoolValue(true))
  }

  it should "return value true in SmallerEq(IntValue(10), IntValue(10))" in {
    val val10 = IntValue(10)
    val add   = new SmallerEqExpression(val10, val10)

    add.eval() should be (BoolValue(true))
  }

  it should "return value true in SmallerEq(IntValue(10), IntValue(-20))" in {
    val val10  = IntValue(10)
    val val20 = IntValue(-20)
    val add   = new SmallerEqExpression(val10, val20)

    add.eval() should be (BoolValue(false))
  }

  it should "lead to an exception in SmallerEq(IntValue(5), BoolValue(False))" in {
    val val5 = IntValue(5)
    val valf = BoolValue(false)
    val add = new SmallerEqExpression(val5, valf)

    // Mult.eval() should be (IntValue(5))
  }
}
