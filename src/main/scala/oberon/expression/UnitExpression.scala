package oberon.expression

abstract class UnitExpression(val uhs: Expression) extends Expression {

}

class NotExpression(uhs: Expression) extends UnitExpression(uhs) {

  override
  def eval: Value = {
    val v1 : BoolValue = uhs.eval().asInstanceOf[BoolValue]

    if(v1 == BoolValue(false)) return BoolValue(true)
    else return BoolValue(false)
  }
}