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

    // 3 hashmap diferente, 1 stack - pilha de execução com as declarações de procedimentos
    //                      1 stack - as variáveis globais
    //                      1 stack - com as declarações de procedimentos e funções
    // considerar um comando return
    // adicionar um campo stack dentro da declaração de um procedimento ou função
    // declaração de variaveis - argumentos
    // em procedure o return.run() ja resolve
    // na function deve retornar uma expressao
    //    while (o comando atual nao for um return) {
    //
    //    }
    //    resolver o return de forma individual
    // toda vez que chamar uma funçao ou procedimento deve ser necessario empilhar na pilha de execuçao
    // associar os argumentos da chamada com os argumentos declarados

    // programa em oberon herdaria de comandos, possui variaveis globais
    // procedimentos e um comando principal, lá seta tds as variaveis globais e sao definidos todos os procedimentos
    // depois executa o run do comando
  }

}
