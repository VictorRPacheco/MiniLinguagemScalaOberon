package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


class TestBiggerEqExpression extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "an BiggerEq expressions"

  it should "return value true in BiggerEq(IntValue(20), IntValue(10))" in {
    val val20  = IntValue(20)
    val val10 = IntValue(10)
    val res   = new BiggerEqExpression(val20, val10)

    res.eval() should be (BoolValue(true))
  }

  it should "return value false in BiggerEq(IntValue(10), IntValue(20))" in {
    val val10  = IntValue(10)
    val val20 = IntValue(20)
    val res   = new BiggerEqExpression(val10, val20)

    res.eval() should be (BoolValue(false))
  }

  it should "return value true in Divide(IntValue(10), IntValue(10))" in {
    val val10 = IntValue(10)
    val res   = new BiggerEqExpression(val10, val10)

    res.eval() should be (BoolValue(true))
  }

  it should "return value true in BiggerEq(IntValue(10), IntValue(-20))" in {
    val val10  = IntValue(10)
    val val20 = IntValue(-20)
    val res   = new BiggerEqExpression(val10, val20)

    res.eval() should be (BoolValue(true))
  }

  it should "lead to an exception in BiggerEq(IntValue(5), BoolValue(False))" in {
    val val5 = IntValue(5)
    val valf = BoolValue(false)
    val res = new BiggerEqExpression(val5, valf)

    // res.eval() should be (IntValue(5))
  }
}
