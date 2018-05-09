package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


class TestExpression extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "simple expressions"

  it should "return value 5 in IntValue(5).eval" in {
    val val5 = new IntValue(5)

    val5.eval() should be (IntValue(5)) 
  }
}
