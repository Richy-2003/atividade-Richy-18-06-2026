import jdk.jfr.Percentage

open class Produto(
    val nome: String,
    var preco:  Double,
    var quantidadeEstoque: Int) {


    fun aplicarDesconto(percentage: Double)
        if (percentage > 100, < 0){
            println("valor invalido")
        }
        else if (percentage = percentage * 100 / 30)
            return porcentagen
        }


fun main () {
    val produto1 = Produto (
        nome= "Arroz",
        preco= 8.50,
        quantidadeEstoque= 10)

    val produto2 = Produto (
        nome= "Feijao",
        preco= 7.00,
        quantidadeEstoque= 50)

    val produto3 = Produto (
    nome= "Macarrao",
    preco= 4.50,
    quantidadeEstoque= 200
)
        println("${produto1.nome} | ${produto1.preco} | ${produto1.quantidadeEstoque}")
        println("${produto2.nome} | ${produto2.preco} | ${produto2.quantidadeEstoque}")
        println("${produto3.nome} | ${produto3.preco} | ${produto3.quantidadeEstoque}")

}








