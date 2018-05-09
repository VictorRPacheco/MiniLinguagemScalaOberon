package oberon.expression

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


class TestAndExpression extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "an and expression"

  it should "return value true in and(BoolValue(true), BoolValue(true))" in {
    val valt = BoolValue(true)
    val valf = BoolValue(false)
    val res   = new AndExpression(valt, valt)

    res.eval() should be (BoolValue(true))
  }

  it should "return value false in and(BoolValue(true), BoolValue(false))" in {
    val valt = BoolValue(true)
    val valf = BoolValue(false)
    val res   = new AndExpression(valt, valf)

    res.eval() should be (BoolValue(false))
  }

  it should "return value false in and(BoolValue(false), BoolValue(true))" in {
    val valt = BoolValue(true)
    val valf = BoolValue(false)
    val res   = new AndExpression(valf, valt)

    res.eval() should be (BoolValue(false))
  }

  it should "return value false in and(BoolValue(false), BoolValue(false))" in {
    val valt = BoolValue(true)
    val valf = BoolValue(false)
    val res   = new AndExpression(valf, valf)

    res.eval() should be (BoolValue(false))
  }
}
