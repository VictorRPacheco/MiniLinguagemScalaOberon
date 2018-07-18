# MiniLinguagemScalaOberon
Desenvolvimento de uma mini linguagem de programação para a disciplina de Técnicas De Programação. Utilizando Scala para interpretar a linguagem Oberon.

## Feito com

* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - IDEA utilizada
* [Scala Plugin para IntelliK](http://confluence.jetbrains.com/display/SCA/Scala+Plugin+for+IntelliJ+IDEA) - Plugin para o uso de escala na IDEA

## Versão

* **Intellij** - *2018.1.2*
* **Scala** - *2.12.3*
* **SBT** - *1.1.1*

## Autores

* **Rodrigo Bonifácio** - *Projeto base* - [rbonifacio](https://github.com/rbonifacio)
* **Víctor Rodrigues Pacheco (17/0063879)** - *Autor* - [VictorRPacheco](https://github.com/VictorRPacheco)
* **Paulo Victor Gonçalves Farias (13/0144754)** - *Autor* - [paulovgf](https://github.com/paulovgf)

Veja também a lista de [contributors](https://github.com/granix3/MiniLinguagemScalaOberon/graphs/contributors).

## Estrutura do Projeto

O projeto do interpretador da linguagem oberon funciona da seguinte maneira. O ambiente contém três variáveis importantes:

* **Pilha de Execução:** Contém threads de procedimentos ou funções em execução.
* **Variáveis Globais:** Contém as definições de variáveis globais, ou seja, que foram declaradas fora do escopo de uma função.
* **Declaração de Programas:** Contém as declarações de procedimentos e funções que podem ser executados.

As estruturas utilizadas foram as seguintes:

* **Callable:** É um trait que representa as estruturas que podem ser chamadas durante o programa. Dentre elas temos: variáveis (id, tipo e valor), funções (id, tipo, parâmetros formais, bloco de comandos), procedimentos (id, tipo, parâmetros formais, bloco de comandos e uma variável de retorno).

* **Thread:** Uma thread é criada sempre quando há uma nova chamada de função ou procedimento. As threads podem ser de dois tipos: threads de procedimento ou de funções. Uma thread consiste em um id, a declaração da função ou procedimento e suas variáveis locais.

* **Comandos:** Um comando é um trait que tem apenas um método 'run()' que não retorna nenhum valor. Dentre os comandos implementados: Assignment, BlockCommand, Return, IfElse, IfThen, For, While, Print, VariableDefinition, CallableDeclaration, ProcedureCall.

* **Expressões:** Uma expressão é um trait que tem alguns métodos como 'eval()' que avalia a expressão e retorna um Value que pode ser (Int, Bool ou Undefined), um método 'calculateType' que retorna o tipo daquela expressão (TBool(), TInt()) e um método 'typeCheck()' que garante que aquela expressão é de um tipo específico diferente de 'TUndefined()'. Dentre as expressões implementadas: Add, Minus, Multiply, Divide, Eq, Dif, Bigger, Smaller, BiggerEq, SmallerEq, And, Not, Or e FunctionCall.

Algumas recomendações pedidas durante a realização do projeto e como elas foram solucionadas:

* **Sobre a primeira entrega:** **Declaração de variáveis não são comandos,** seguimos a lógica que a declaração de uma variável é uma case class do trait Callable, já a definição de uma variável é um comando, a definição de uma variável ocorre quando uma variável recebe um valor e é inserida no HashMap das variáveis globais ou no HashMap das variáveis locais dependendo do escopo de onde ela foi definida. **Declarações de procedimentos e funções não são comandos,** seguimos a mesma lógica anterior levando em conta que procedimentos e funções também fazem parte do trait Callable. Neste caso a declaração de um procedimento ou função é considerada um comando e é adicionada a uma tabela com as declarações existentes. **Variáveis globais e locais:** quando uma variável é referenciada primeiro é feita uma busca nas variáveis globais e depois nas locais. **Chamadas de função e procedimentos:** chamadas de função foram feitas criando uma nova expressão com este intuíto e o comando return para dar o valor recebido da expressão, testes foram feitos sendo que o programa funcionou para funções recursivas. 

* **Sobre a segunda entrega:** **Implementação da verificação de tipos,** foi feita utilizando a função de 'typeCheck()' e 'calculateType()' sendo implementada na checagem de parâmetros de funções e procedimentos, bem como na validação dos parâmetros de um comando como por exemplo a condição de um While retornando um valor booleano. **Implementação(completa) do visitor PrettyPrinter:** o visitor PrettyPrinter foi implementado para expressões e comandos. Ele é responsável por imprimir as estruturas do interpretador de forma que permita um bom entendimento. **Implementação do vistor (Refactor):** Não foi implementado.

## Licença

Este projeto está sob a licença do MIT - Veja em [LICENSE.md](LICENSE.md) arquivo para mais detalhes.


