package dev.hertlein.katas.romannumerals

class RomanNumeralsConverter {

    @Suppress("MagicNumber")
    private enum class RomanNumeral(val decimalValue: Int) {
        I(1),
        IV(4),
        V(5),
        IX(9),
        X(10),
        XL(40),
        L(50),
        XC(90),
        C(100),
        CD(400),
        D(500),
        CM(900),
        M(1000);
    }

    @Suppress("TooGenericExceptionThrown")
    fun convert(numeral: String): Int {
        val decimalHeadRecursively = convertHeadRecursively(numeral)
        val decimalTailRecursively = convertTailRecursively(numeral)
        val decimalByLooping = convertByLooping(numeral)

        if (!allConversionResultsAreEqual(decimalHeadRecursively, decimalTailRecursively, decimalByLooping)) {
            throw RuntimeException(
                """There is a bug, as the conversion results differ: 
                | headRecursively: $decimalHeadRecursively 
                | tailRecursively: $decimalTailRecursively 
                | byLooping: $decimalByLooping""".trimMargin()
            )
        }

        return decimalHeadRecursively
    }

    private fun convertHeadRecursively(numeral: String): Int {
        RomanNumeral
            .values()
            .reversed()
            .forEach {
                if (numeral.startsWith(it.name)) {
                    return it.decimalValue + convertHeadRecursively(numeral.removePrefix(it.name))
                }
            }
        return 0
    }

    private fun convertTailRecursively(numeral: String, decimal: Int = 0): Int {
        RomanNumeral
            .values()
            .reversed()
            .forEach {
                if (numeral.startsWith(it.name)) {
                    return convertTailRecursively(numeral.removePrefix(it.name), decimal + it.decimalValue)
                }
            }
        return decimal
    }

    private fun convertByLooping(numeral: String): Int {
        var decimal = 0
        var numeralForLooping = numeral

        while (numeralForLooping.isNotEmpty()) {
            RomanNumeral
                .values()
                .reversed()
                .forEach {
                    if (numeralForLooping.startsWith(it.name)) {
                        decimal += it.decimalValue
                        numeralForLooping = numeralForLooping.removePrefix(it.name)
                    }
                }
        }
        return decimal
    }

    private fun allConversionResultsAreEqual(vararg results: Int) = results.toSet().size == 1
}
