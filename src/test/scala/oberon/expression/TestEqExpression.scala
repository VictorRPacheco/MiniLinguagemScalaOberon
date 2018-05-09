package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


class TestEqExpression extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "an eq expression"

  it should "return value true in Eq(IntValue(5), Add(IntValue(3), IntValue(2))))" in {
    val val5 = IntValue(5)
    val val3 = IntValue(3)
    val val2 = IntValue(2)  
    val add  = new AddExpression(val3, val2) 
    val eq   = new EqExpression(val5, add)

    eq.eval() should be (BoolValue(true)) 
  }

  it should "return value false in Eq(IntValue(5), Add(IntValue(3), IntValue(3))))" in {
    val val5 = IntValue(5)
    val val3 = IntValue(3)
    val add  = new AddExpression(val3, val3) 
    val eq   = new EqExpression(val5, add)

    eq.eval() should be (BoolValue(false)) 
  }


}
