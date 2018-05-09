package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


class TestBiggerExpression extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "an Bigger expressions"

  it should "return value true in Bigger(IntValue(20), IntValue(10))" in {
    val val20  = IntValue(20)
    val val10 = IntValue(10)
    val add   = new BiggerExpression(val20, val10)

    add.eval() should be (BoolValue(true))
  }

  it should "return value false in Bigger(IntValue(10), IntValue(20))" in {
    val val10  = IntValue(10)
    val val20 = IntValue(20)
    val add   = new BiggerEqExpression(val10, val20)

    add.eval() should be (BoolValue(false))
  }

  it should "return value false in Divide(IntValue(10), IntValue(10))" in {
    val val10 = IntValue(10)
    val add   = new BiggerExpression(val10, val10)

    add.eval() should be (BoolValue(false))
  }

  it should "return value true in Bigger(IntValue(10), IntValue(-20))" in {
    val val10  = IntValue(10)
    val val20 = IntValue(-20)
    val add   = new BiggerExpression(val10, val20)

    add.eval() should be (BoolValue(true))
  }

  it should "lead to an exception in Bigger(IntValue(5), BoolValue(False))" in {
    val val5 = IntValue(5)
    val valf = BoolValue(false)
    val add = new BiggerExpression(val5, valf)

    // Mult.eval() should be (IntValue(5))
  }
}
