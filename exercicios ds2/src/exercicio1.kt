open class Produto(
    val nome: String,
    precoInicial: Double,
    var quantidadeEstoque: Int
) {
    private var preco: Double = precoInicial

    fun getPreco(): Double {
        return preco
    }

    fun setPreco(valor: Double) {
        if (valor >= 0) {
            preco = valor
        } else {
            println("Erro: preço não pode ser negativo.")
        }
    }

    fun aplicarDesconto(percentual: Double) {
        if (percentual < 0 || percentual > 100) {
            println("Erro: percentual inválido.")
            return
        }

        val novoPreco = preco * (1 - percentual / 100)
        setPreco(novoPreco)
    }

    open fun exibir() {
        println("Produto: $nome | Preço: R$ %.2f | Estoque: $quantidadeEstoque"
            .format(preco))
    }
}

class ProdutoPerecivel(
    nome: String,
    preco: Double,
    quantidadeEstoque: Int,
    val dataValidade: String
) : Produto(nome, preco, quantidadeEstoque) {

    fun estaVencido(dataHoje: String): Boolean {
        return dataValidade < dataHoje
    }

    override fun exibir() {
        super.exibir()
        println("Validade: $dataValidade")
    }
}

abstract class FuncionarioBase(
    val nome: String,
    val salarioBase: Double
) {
    abstract fun calcularSalario(): Double
}

class Vendedor(
    nome: String,
    salarioBase: Double,
    var totalVendas: Double
) : FuncionarioBase(nome, salarioBase) {

    override fun calcularSalario(): Double {
        return salarioBase + (totalVendas * 0.05)
    }
}

class Gerente(
    nome: String,
    salarioBase: Double,
    val bonusFixo: Double
) : FuncionarioBase(nome, salarioBase) {

    override fun calcularSalario(): Double {
        return salarioBase + bonusFixo
    }
}

fun finalizarVenda(
    carrinho: List<Produto>,
    vendedor: Vendedor
) {
    var totalVenda = 0.0

    println("\n--- ITENS DA VENDA ---")

    for (produto in carrinho) {

        if (produto is ProdutoPerecivel) {
            if (produto.estaVencido("2026/06/18")) {
                println("AVISO: ${produto.nome} está vencido!")
            }
        }

        println("${produto.nome} - R$ %.2f"
            .format(produto.getPreco()))

        totalVenda += produto.getPreco()

        if (produto.quantidadeEstoque > 0) {
            produto.quantidadeEstoque--
        }
    }

    vendedor.totalVendas += totalVenda

    println("\nTotal da venda: R$ %.2f".format(totalVenda))
    println(
        "Salário atualizado do vendedor: R$ %.2f"
            .format(vendedor.calcularSalario())
    )
}

fun main() {

    val arroz = Produto("Arroz", 8.50, 100)
    val feijao = Produto("Feijão", 7.00, 50)
    val macarrao = Produto("Macarrão", 4.50, 200)

    arroz.exibir()
    feijao.exibir()
    macarrao.exibir()

    arroz.aplicarDesconto(10.0)
    arroz.exibir()

    arroz.aplicarDesconto(150.0)

    val leite = ProdutoPerecivel(
        "Leite",
        6.50,
        20,
        "2026/06/10"
    )

    val iogurte = ProdutoPerecivel(
        "Iogurte",
        4.00,
        15,
        "2026/07/01"
    )

    println("\nLeite vencido? ${leite.estaVencido("2026/06/18")}")
    println("Iogurte vencido? ${iogurte.estaVencido("2026/06/18")}")

    val vendedor1 = Vendedor("João", 3000.0, 5000.0)
    val gerente1 = Gerente("Maria", 5000.0, 1000.0)
    val vendedor2 = Vendedor("Carlos", 2500.0, 8000.0)

    val funcionarios: List<FuncionarioBase> =
        listOf(vendedor1, gerente1, vendedor2)

    var totalFolha = 0.0

    println("\n--- FOLHA DE PAGAMENTO ---")

    for (funcionario in funcionarios) {
        val salario = funcionario.calcularSalario()

        println(
            "${funcionario.nome} -> R$ %.2f"
                .format(salario)
        )

        totalFolha += salario
    }

    println("-------------------------")
    println("Total da folha: R$ %.2f".format(totalFolha))

    val carrinho = listOf(
        arroz,
        feijao,
        leite
    )

    finalizarVenda(carrinho, vendedor1)
}








