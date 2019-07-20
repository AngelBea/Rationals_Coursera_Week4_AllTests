package rationals

fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    print("Plus - > ")
    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    print("Difference - > ")
    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    print("Product - > ")
    val product: Rational = half * third
    println(1 divBy 6 == product)

    print("Division - > ")
    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    print("Negation - > ")
    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    print("Check only number - > ")
    println((2 divBy 1).toString() == "2")

    print("Check negation - > ")
    println((-2 divBy 4).toString() == "-1/2")

    print("Check toRational - > ")
    println("117/1098".toRational().toString() == "13/122")

    print("Lesser than - > ")
    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    print("Range - > ")
    println(half in third..twoThirds)

    print("Long - > ")
    println(2000000000L divBy 4000000000L == 1 divBy 2)

    print("Checking normalization - > ")
    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}