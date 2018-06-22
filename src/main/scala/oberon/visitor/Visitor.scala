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

  def visit(c: Assignment)  : Unit
  def visit(c: VariableDefinition)  : Unit
  def visit(c: Return)  : Unit
  def visit(c: While) : Unit
  def visit(c: IfElse)  : Unit
  def visit(c: IfThen)  : Unit
  def visit(c: For) : Unit
  def visit(c: Print) : Unit
  def visit(c: BlockCommand) : Unit
  def visit(c: ProcedureCall) : Unit
  def visit(c: CallableDeclaration) : Unit
  def visit(c: ReadInt) : Unit
  def visit(c: ReadBool) : Unit

}