package lxchao

import java.io.BufferedReader
import java.io.StringReader
import java.lang.IllegalArgumentException
import java.lang.IndexOutOfBoundsException
import java.lang.NumberFormatException

/**
 *
 * @author lixiangchao
 * @date 2019/9/16
 * @version 1.0.0
 */
fun main() {
    println("hello")

    println(Rectangle(10, 10).isSquare)

    println(Color.BLUE.rgb())

    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
    println(eval1(Sum(Sum(Num(1), Num(2)), Num(4))))
    println(eval2(Sum(Sum(Num(1), Num(2)), Num(4))))

    println(readNum(BufferedReader(StringReader("hello world"))))

    println("1234".anonPhone())
}

class Rectangle(private val width: Int, private val height: Int) {
    val isSquare: Boolean get() = width == height
}

enum class Color(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0), BLUE(0, 0, 255);

    fun rgb() = (r * 255 + g) * 255 + b
}

interface Expr
class Num(val value: Int): Expr
class Sum(val left: Expr, val right: Expr): Expr

fun eval(e: Expr): Int {
    if (e is Num) {
        return e.value
    }
    if (e is Sum) {
        return eval(e.left) + eval(e.right)
    }
    throw IllegalArgumentException("Unknown expression")
}

fun eval1(e: Expr): Int = if (e is Num) e.value else if (e is Sum) eval1(e.left) + eval1(e.right) else throw IllegalArgumentException("Unknown expression")

fun eval2(e: Expr): Int = when(e) {
    is Num -> e.value
    is Sum -> eval2(e.left) + eval2(e.right)
    else -> throw IllegalArgumentException("Unknown expression")
}

fun readNum(reader: BufferedReader) {
    val num = try {
        reader.readLine().toInt()
    } catch (e: NumberFormatException) {
        -1
    }
    println(num)

    joinToString(listOf(1, 2, 3), sperator = "hello")
}

fun <T> joinToString(collection: Collection<T>, /* sperator */sperator: String) {

}

fun String.anonPhone(): String = try {
    "${substring(0, 3) + "****" + substring(7)}"
} catch (e: IndexOutOfBoundsException) {
    this
}