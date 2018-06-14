package oberon.visitor

import oberon.expression._
import oberon.command._

trait Visitable {
  def accept(v: Visitor) : Unit
}

trait Visitor {
  def visit(e: Undefined) : Unit
  def visit(e: IntValue)  : Unit
  def visit(e: BoolValue) : Unit
  def visit(e: AddExpression) : Unit
  def visit(e: MinusExpression) : Unit
  def visit(e: MultExpression)  : Unit
  def visit(e: DivideExpression)  : Unit
  def visit(e: EqExpression)  : Unit
  def visit(e: DifExpression) : Unit
  def visit(e: BiggerExpression)  : Unit
  def visit(e: BiggerEqExpression)  : Unit
  def visit(e: SmallerExpression) : Unit
  def visit(e: SmallerEqExpression) : Unit
  def visit(e: AndExpression) : Unit
  def visit(e: OrExpression)  : Unit
  def visit(e: VarRef)  : Unit
  def visit(e: FunctionExpression)  : Unit
  def visit(e: UnitExpression)  : Unit

  def visit(e: Assignment)  : Unit
  def visit(e: VariableDefinition)  : Unit
  def visit(e: Return)  : Unit
  def visit(e: While) : Unit
  def visit(e: IfElse)  : Unit
  def visit(e: IfThen)  : Unit
  def visit(e: For) : Unit
  def visit(e: Print) : Unit

}