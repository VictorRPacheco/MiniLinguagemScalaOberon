package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


class TestNotExpression extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "an Not expression"

  it should "return value true in not(BoolValue(false))" in {
    val valt = BoolValue(true)
    val valf = BoolValue(false)
    val res   = new NotExpression(valf)

    res.eval() should be (BoolValue(true))
  }

  it should "return value false in not(BoolValue(true))" in {
    val valt = BoolValue(true)
    val valf = BoolValue(false)
    val res   = new NotExpression(valt)

    res.eval() should be (BoolValue(false))
  }

}
