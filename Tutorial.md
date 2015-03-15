# Introdução #

O GP Comm é um _framework_ escrito em Java que auxilia o desenvolvimento de aplicações _host_ que necessitam se comunicar com um cartão inteligente. Esse tutorial mostrará como configurar e usar o GP Comm, através de exemplos de código.

# Configuração do ambiente de desenvolvimento #

## Diretórios ##

Os diretórios usados nos exemplos são os seguintes:

  * `src`: contém o código-fonte dos exemplos;
  * `lib`: contém os arquivos do GP Comm, que devem ser adicionados no CLASSPATH;
  * `classes`: contém as classes dos exemplos, já compiladas;

O diretório onde estes diretórios estão contidos será chamado de `$HOME`, e, a não ser que seja especificado o contrário, todos os comandos citados deverão ser executados dentro deste diretório.

## Sistema operacional ##

Por ser escrito em Java, o GP Comm pode ser utilizado em qualquer sistema operacional que tenha uma máquina virtual Java compatível (versão 5 ou superior). Porém, os exemplos serão descritos como se estivessem sendo executados no Linux. Por exemplo, no comando:

```
$ ant compilar
```

o símbolo de cifrão indica o prompt do shell de um usuário, no Linux.

## Arquivos do GP Comm ##

Para desenvolver utilizando o GP Comm, é necessário que o arquivo `gpcomm-x.y.z.jar` (onde x.y.z é a versão do arquivo) esteja no CLASSPATH da aplicação, tanto em tempo de compilação quanto em tempo de execução. Além dessas classes, é necessário que pelo menos um provedor de comunicação também esteja no CLASSPATH, porém, ele só é necessário durante a execução da aplicação. Para baixar o GP Comm e o provedor padrão (`gpcomm-jscio-x.y.z.jar`), acesse a aba "Downloads" deste site.

Além das classes, o arquivo `gpcomm.properties` deve estar no CLASSPATH da aplicação em tempo de execução. Os detalhes deste arquivo estão em GpCommProperties.

# Exemplos de código #

## Exemplo 1: Hello World ##

O primeiro exemplo é um "Hello world" com o GP Comm. Se essa classe executar sem mostrar nenhuma exceção, significa que o GP Comm está configurado com sucesso.

A classe principal do GP Comm se chama `GpComm`. É através dela que se obtém terminais (leitores) disponíveis, cartões, e outros objetos relacionados. Durante a criação de um objeto `GpComm`, as propriedades do arquivo `gpcomm.properties` são lidas (ver GpCommProperties para mais detalhes). A propriedade mais importante é a `gpcomm.provider`, que indica qual ProvedorDeComunicacao será utilizado. Se essa propriedade não for encontrada, uma exceção será lançada.

Todo objeto `GpComm` deve ser finalizado ao final do seu uso, através do método `close()`. Por isso, o uso desse objeto deve estar em um bloco try/catch/finally. E como o construtor pode lançar uma exceção, esse objeto precisa ser declarado antes do bloco try/catch/finally, com valor inicial `null`. O trecho de código da criação de um objeto da classe `GpComm` é:

```
GpComm gpcomm = null;
try {
  gpcomm = new GpComm();
  ...
}
catch (GpCommException e) {
  ...
}
finally {
  if (gpcomm != null) {
    try {
      gpcomm.close();
    }
    catch (GpCommException e) {
      ...
    }
  }
}
```

O método `close()` também pode lançar uma exceção, por isso é necessário outro bloco try/catch ao redor dele.

O código completo do exemplo está mostrado abaixo:

```
import br.cefetrn.smartproject.gpcomm.GpComm;
import br.cefetrn.smartproject.gpcomm.GpCommException;

public class Exemplo1 {
  public static void main(String[] args) {
    GpComm gpcomm = null;
    try {
      System.out.println("Iniciando o GP Comm...");
      gpcomm = new GpComm();
      System.out.println("O GP Comm foi inicializado com sucesso!");
    }
    catch (GpCommException e) {
      System.err.println("Houve um erro ao inicializar o GP Comm.");
      e.printStackTrace();
    }
    finally {
      if (gpcomm != null) {
        try {
          System.out.println("Finalizando o GP Comm...");
          gpcomm.close();
          System.out.println("O GP Comm foi finalizado com sucesso!");
        }
        catch (GpCommException e) {
          System.err.println("Houve um erro ao finalizar o GP Comm.");
          e.printStackTrace();
        }
      }
    }
  }
}
```

Para compilar e executar esse exemplo, basta digitar os seguintes comandos:

```
$ javac -cp "lib/*.jar" -d classes src/Exemplo1.java
$ java -cp "lib/*.jar:classes" Exemplo1
```

A saída esperada é:

```
Iniciando o GP Comm...
O GP Comm foi inicializado com sucesso!
Finalizando o GP Comm...
O GP Comm foi finalizado com sucesso!
```

Caso essa não seja a saída, a descrição da exceção já deve indicar qual o problema. Mas os possíveis problemas são:

  * A propriedade `gpcomm.provider` não foi encontrada.
  * A classe indicada pela propriedade `gpcomm.provider` não existe.
  * A classe indicada pela propriedade `gpcomm.provider` não implementa a interface `GpCommProvider`.
  * A classe indicada pela propriedade `gpcomm.provider` não possui um construtor padrão.

Se tudo estiver OK, o GP Comm está configurado e pronto pra usar!

## Exemplo 2: listar todos os terminais ##

O código completo desse exemplo está listado abaixo.

```
import br.cefetrn.smartproject.gpcomm.GpComm;
import br.cefetrn.smartproject.gpcomm.GpCommException;
import br.cefetrn.smartproject.gpcomm.GpCommTerminal;

import java.util.List;

public class ListaTerminais {
  public static void main(String[] args) {
    GpComm gpcomm = null;
    try {
      gpcomm = new GpComm();
      List<GpCommTerminal> terminais = gpcomm.getAvailableTerminals();
      if (terminais.isEmpty()) {
        System.out.println("Não existe nenhum terminal conectado ao seu computador.");
      }
      else {
        System.out.println(terminais.size() + " terminais encontrados:");
        for (GpCommTerminal t : terminais) {
          System.out.println(t.getName());
        }
      }
    }
    catch (GpCommException e) {
      System.err.println("Erro no GP Comm: " + e.getMessage());
    }
    finally {
      if (gpcomm != null) {
        try {
          gpcomm.close();
        }
        catch (GpCommException e) {
          System.err.println("Erro ao finalizar o GP Comm: " + e.getMessage());
        }
      }
    }
  }
}
```
