package rationals

import org.w3c.dom.ranges.Range
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import java.math.BigInteger
import kotlin.math.roundToInt


class Rational(var numerator : BigInteger, var denominator : BigInteger) : Comparable<Rational>{
    init {
        //numerator = if(numerator > 0.toBigInteger() || numerator < 0.toBigInteger()) numerator else throw IllegalArgumentException("Numerator can't be Zero")
        denominator = if(denominator > 0.toBigInteger() || denominator <0.toBigInteger()) denominator else throw IllegalArgumentException("Denominator can't be zero")
    }

    override fun equals(other: Any?): Boolean {
        val norm : Pair<Rational, Rational>

        if(other is Rational){
            norm = Pair(this.normalize(), other.normalize())
            //println("""${norm.first} ${norm.second}""")
            return norm.first.numerator == norm.second.numerator && norm.first.denominator == norm.second.denominator

        }
        throw IllegalArgumentException("Parameter Object isn't a Rational object")
    }

    fun normalize(): Rational{
        val gcd = denominator.gcd(numerator)
        //println(gcd)

        var nume= numerator
        var denom= denominator

        return if(denominator < 0.toBigInteger())Rational((numerator/gcd).unaryMinus(), (denominator/gcd).times(-1.toBigInteger())) else Rational(numerator/gcd, denominator/gcd)
    }


    operator fun plus(other: Rational) : Rational{
        var rational = Rational(1.toBigInteger(), 1.toBigInteger());
        when(true){
            this.denominator == other.denominator -> rational.numerator = other.numerator + this.numerator
            this.denominator != other.denominator -> {
                rational.numerator = (numerator * other.denominator) + (other.numerator * denominator)
                rational.denominator = denominator * other.denominator
            }
        }
        return rational
    }

    operator fun minus(other : Rational) : Rational{
        var rational = Rational(1.toBigInteger(), 1.toBigInteger());
        //println("""${this.numerator} ${this.denominator} - ${other.numerator} ${other.denominator}""")
        when(true){
            this.denominator == other.denominator -> rational.numerator = this.numerator - other.denominator
            this.denominator != other.denominator -> {
                rational.numerator = (numerator * other.denominator) - (other.numerator * denominator)
                rational.denominator = denominator * other.denominator
            }
        }
        //println("""${other.numerator} ${other.denominator}""")
        return rational
    }

    operator fun div(other: Rational) : Rational{
        var rational = Rational(1.toBigInteger(), 1.toBigInteger());
        rational.numerator = numerator * other.denominator
        rational.denominator = other.numerator * denominator
        return rational
    }

    operator fun times(other : Rational) : Rational{
        val first = normalize()
        val second = other.normalize()

        return Rational(first.numerator * second.numerator, first.denominator * second.denominator)
    }

    override fun compareTo(other: Rational): Int {
        //println("Compare to Double -> "+((normalize().numerator.toDouble() / normalize().denominator.toDouble()) - (other.normalize().numerator.toDouble() / other.normalize().denominator.toDouble())))
        val minus = (normalize().numerator.toDouble() / normalize().denominator.toDouble()) - (other.normalize().numerator.toDouble() / other.normalize().denominator.toDouble())
        return when(true){
            minus > 0 -> 1
            minus < 0 -> -1
            else -> 0
        }
    }

    operator fun unaryMinus() : Rational {
        //println("""$numerator/$denominator""")
        numerator = -numerator
        //println("""$numerator/$denominator""")
        return this
    }

    override fun toString(): String {
        return when(true){
            normalize().denominator != 1.toBigInteger() &&  !(normalize().denominator < 0.toBigInteger()) -> """${normalize().numerator}/${normalize().denominator}"""
            normalize().numerator.rem(normalize().denominator).equals(0.toBigInteger())  -> """${normalize().numerator/normalize().denominator}"""
            normalize().denominator < 0.toBigInteger() -> """${normalize().numerator.unaryMinus()}/${normalize().denominator * -1.toBigInteger()}"""
            else -> """${normalize().numerator}"""
        }
    }
}

infix fun Number.divBy(i: Number): Rational {
    //println("""Div By -> ${this.toInt().toBigInteger()} $i""")
    val rational = Rational(this.toLong().toBigInteger(), i.toLong().toBigInteger())
    return rational
}

fun String.toRational() : Rational{
    val string = this.split("/", limit=3)
    when(true){
        string.size == 1 -> return Rational(this.toBigInteger(), 1.toBigInteger())
        else -> return Rational(string.get(0).toBigInteger(), string.get(1).toBigInteger())
    }
}

fun Int.toRational() : Rational{
    return Rational(this.toBigInteger(), 1.toBigInteger())
}

