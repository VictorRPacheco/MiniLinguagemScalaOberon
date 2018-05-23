package oberon.expression

import oberon.callable.Callable

class FunctionExpression (var f: Callable) extends Expression {
  override
  def eval() : Value = {
    IntValue(5)
    // fazer todos os comandos
    // return (tem que ser um comando especial)
    //    dentro de turn só tem uma expressao
    //    def run () { }
    //    ou
    //    poderia ser return exp.eval
  }

}
