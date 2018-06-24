package oberon.expression

import oberon.visitor.Visitor

abstract class BinExpression(val lhs: Expression, val rhs: Expression) extends Expression

class AddExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 : IntValue = lhs.eval().asInstanceOf[IntValue]
    val v2 : IntValue = rhs.eval().asInstanceOf[IntValue]

    return IntValue(v1.value + v2.value)
  }

  override def calculateType(): Type = {
    val t1 = lhs.calculateType()
    val t2 = rhs.calculateType()

    return if(t1 == TInt() && t2 == TInt()) TInt() else TUndefined()
  }

  override def accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}

class MinusExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 : IntValue = lhs.eval().asInstanceOf[IntValue]
    val v2 : IntValue = rhs.eval().asInstanceOf[IntValue]

    return IntValue(v1.value - v2.value)
  }

  override def calculateType(): Type = {
    val t1 = lhs.calculateType()
    val t2 = rhs.calculateType()

    return if(t1 == TInt() && t2 == TInt()) TInt() else TUndefined()
  }

  override def accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}

class MultExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 : IntValue = lhs.eval().asInstanceOf[IntValue]
    val v2 : IntValue = rhs.eval().asInstanceOf[IntValue]

    return IntValue(v1.value * v2.value)
  }

  override def calculateType(): Type = {
    val t1 = lhs.calculateType()
    val t2 = rhs.calculateType()

    return if(t1 == TInt() && t2 == TInt()) TInt() else TUndefined()
  }

  override def accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}

class DivideExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 : IntValue = lhs.eval().asInstanceOf[IntValue]
    val v2 : IntValue = rhs.eval().asInstanceOf[IntValue]

    if (v2.equals(IntValue(0))) throw new RuntimeException("0 Division is undefined")

    return IntValue(v1.value / v2.value)
  }

  override def calculateType(): Type = {
    val t1 = lhs.calculateType()
    val t2 = rhs.calculateType()

    return if(t1 == TInt() && t2 == TInt()) TInt() else TUndefined()
  }

  override def accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}

class EqExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval: Value = {
    val v1 = lhs.eval()
    val v2 = rhs.eval()

    return BoolValue(v1 == v2)
  }

  override def calculateType(): Type = {
    val t1 = lhs.calculateType()
    val t2 = rhs.calculateType()

    return if(t1 == t2) TBool() else TUndefined()
  }

  override def accept(v: Visitor) : Unit = {
    v.visit(this)
  }

}

class DifExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 = lhs.eval()
    val v2 = rhs.eval()

    return BoolValue(v1 != v2)
  }

  override def calculateType(): Type = {
    val t1 = lhs.calculateType()
    val t2 = rhs.calculateType()

    return if(t1 == t2) TBool() else TUndefined()
  }

  override def accept(v: Visitor) : Unit = {
    v.visit(this)
  }

}

class BiggerExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 : IntValue = lhs.eval().asInstanceOf[IntValue]
    val v2 : IntValue = rhs.eval().asInstanceOf[IntValue]

    return BoolValue(v1.value > v2.value)
  }

  override def calculateType(): Type = {
    val t1 = lhs.calculateType()
    val t2 = rhs.calculateType()

    return if(t1 == t2) TBool() else TUndefined()
  }

  override def accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}

class SmallerExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 : IntValue = lhs.eval().asInstanceOf[IntValue]
    val v2 : IntValue = rhs.eval().asInstanceOf[IntValue]

    return BoolValue(v1.value < v2.value)
  }

  override def calculateType(): Type = {
    val t1 = lhs.calculateType()
    val t2 = rhs.calculateType()

    return if(t1 == t2) TBool() else TUndefined()
  }

  override def accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}

class BiggerEqExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 : IntValue = lhs.eval().asInstanceOf[IntValue]
    val v2 : IntValue = rhs.eval().asInstanceOf[IntValue]

    return BoolValue(v1.value >= v2.value)
  }

  override def calculateType(): Type = {
    val t1 = lhs.calculateType()
    val t2 = rhs.calculateType()

    return if(t1 == t2) TBool() else TUndefined()
  }

  override def accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}

class SmallerEqExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval() : Value = {
    val v1 : IntValue = lhs.eval().asInstanceOf[IntValue]
    val v2 : IntValue = rhs.eval().asInstanceOf[IntValue]

    return BoolValue(v1.value <= v2.value)
  }

  override def calculateType(): Type = {
    val t1 = lhs.calculateType()
    val t2 = rhs.calculateType()

    return if(t1 == t2) TBool() else TUndefined()
  }

  override def accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}

class AndExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval: Value = {
    val v1 : BoolValue = lhs.eval().asInstanceOf[BoolValue]
    val v2 : BoolValue = rhs.eval().asInstanceOf[BoolValue]

    if((v1 == BoolValue(false)) || (v2 == BoolValue(false))) return BoolValue(false)
    else return BoolValue(true)
  }

  override def calculateType(): Type = {
    val t1 = lhs.calculateType()
    val t2 = rhs.calculateType()

    return if(t1 == t2) TBool() else TUndefined()
  }

  override def accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}

class OrExpression(lhs: Expression, rhs: Expression) extends BinExpression(lhs, rhs) {

  override
  def eval: Value = {
    val v1 : BoolValue = lhs.eval().asInstanceOf[BoolValue]
    val v2 : BoolValue = rhs.eval().asInstanceOf[BoolValue]

    if((v1 == BoolValue(false)) && (v2 == BoolValue(false))) return BoolValue(false)
    else return BoolValue(true)
  }

  override def calculateType(): Type = {
    val t1 = lhs.calculateType()
    val t2 = rhs.calculateType()

    return if(t1 == t2) TBool() else TUndefined()
  }

  override def accept(v: Visitor) : Unit = {
    v.visit(this)
  }
}
