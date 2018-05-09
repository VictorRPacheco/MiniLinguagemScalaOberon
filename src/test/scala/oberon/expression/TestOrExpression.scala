package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


class TestOrExpression extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "an or expression"

  it should "return value true in or(BoolValue(true), BoolValue(true))" in {
    val valt = BoolValue(true)
    val valf = BoolValue(false)
    val res   = new OrExpression(valt, valt)

    res.eval() should be (BoolValue(true))
  }

  it should "return value true in or(BoolValue(true), BoolValue(false))" in {
    val valt = BoolValue(true)
    val valf = BoolValue(false)
    val res   = new OrExpression(valt, valf)

    res.eval() should be (BoolValue(true))
  }

  it should "return value true in or(BoolValue(false), BoolValue(true))" in {
    val valt = BoolValue(true)
    val valf = BoolValue(false)
    val res   = new OrExpression(valf, valt)

    res.eval() should be (BoolValue(true))
  }

  it should "return value false in or(BoolValue(false), BoolValue(false))" in {
    val valt = BoolValue(true)
    val valf = BoolValue(false)
    val res   = new OrExpression(valf, valf)

    res.eval() should be (BoolValue(false))
  }
}
