package oberon.expression

import oberon.visitor.Visitor

abstract class UnitExpression(val uhs: Expression) extends Expression {

}

class NotExpression(uhs: Expression) extends UnitExpression(uhs) {

  override
  def eval: Value = {
    val v1 : BoolValue = uhs.eval().asInstanceOf[BoolValue]

    if(v1 == BoolValue(false)) return BoolValue(true)
    else return BoolValue(false)
  }


  override def calculateType(): Type = {
    val t1 = uhs.calculateType()
    return if (t1 == TBool()) TBool() else TUndefined()
  }

  def accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}