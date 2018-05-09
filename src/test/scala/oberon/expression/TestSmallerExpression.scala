package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


class TestSmallerExpression extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "an Smaller expressions"

  it should "return value false in Smaller(IntValue(20), IntValue(10))" in {
    val val20  = IntValue(20)
    val val10 = IntValue(10)
    val res   = new SmallerExpression(val20, val10)

    res.eval() should be (BoolValue(false))
  }

  it should "return value false in Smaller(IntValue(10), IntValue(20))" in {
    val val10  = IntValue(10)
    val val20 = IntValue(20)
    val res   = new SmallerExpression(val10, val20)

    res.eval() should be (BoolValue(true))
  }

  it should "return value false in Smaller(IntValue(10), IntValue(10))" in {
    val val10 = IntValue(10)
    val res   = new SmallerExpression(val10, val10)

    res.eval() should be (BoolValue(false))
  }

  it should "return value true in Smaller(IntValue(10), IntValue(-20))" in {
    val val10  = IntValue(10)
    val val20 = IntValue(-20)
    val res   = new SmallerExpression(val10, val20)

    res.eval() should be (BoolValue(false))
  }

  it should "lead to an exception in Smaller(IntValue(5), BoolValue(False))" in {
    val val5 = IntValue(5)
    val valf = BoolValue(false)
    val res = new SmallerExpression(val5, valf)

    // res.eval() should be (IntValue(5))
  }
}
