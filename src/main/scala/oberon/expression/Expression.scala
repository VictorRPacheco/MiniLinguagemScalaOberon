package oberon.expression

import oberon.visitor.Visitable
import oberon.visitor.Visitor

sealed trait Type

case class TBool() extends Type
case class TInt() extends Type
case class TUndefined() extends Type

trait Expression extends Visitable {
  def eval(): Value
  def calculateType() : Type
  def typeCheck() : Boolean = calculateType() != TUndefined()
}

trait Value extends Expression {
  def eval() = this
}

case class Undefined() extends Value {
  override def calculateType(): Type = TUndefined()
  override def  accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}
case class IntValue(value: Integer) extends Value {
  override def calculateType(): Type = TInt()
  override def accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}
case class BoolValue(value: Boolean) extends Value {
  override def calculateType(): Type = TBool()
  override def accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}
