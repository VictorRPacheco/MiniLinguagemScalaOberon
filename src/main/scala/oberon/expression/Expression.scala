package oberon.expression

trait Expression {
  def eval(): Value
}

trait Value extends Expression {
  def eval() = this
}

case class IntValue(value: Integer) extends Value
case class BoolValue(value: Boolean) extends Value
