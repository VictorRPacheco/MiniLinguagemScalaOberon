package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


class TestDifExpression extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "an dif expression"

  it should "return value false in dif(IntValue(5), Add(IntValue(3), IntValue(2))))" in {
    val val5 = IntValue(5)
    val val3 = IntValue(3)
    val val2 = IntValue(2)
    val add  = new AddExpression(val3, val2)
    val res   = new DifExpression(val5, add)

    res.eval() should be (BoolValue(false))
  }

  it should "return value true in dif(IntValue(5), Add(IntValue(3), IntValue(3))))" in {
    val val5 = IntValue(5)
    val val3 = IntValue(3)
    val add  = new AddExpression(val3, val3)
    val res   = new DifExpression(val5, add)

    res.eval() should be (BoolValue(true))
  }

}
