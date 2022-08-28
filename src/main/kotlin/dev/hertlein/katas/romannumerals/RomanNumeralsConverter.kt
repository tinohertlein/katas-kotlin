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

        companion object {

            fun fromPrefix(input: String): RomanNumeral? {
                return values()
                    .reversed()
                    .firstOrNull { input.startsWith(it.name) }
            }
        }
    }

    fun toDecimals(numeral: String) = ToDecimals().convert(numeral)

    fun fromDecimals(decimal: Int) = FromDecimals().convert(decimal)

    inner class ToDecimals {

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

        private fun convertHeadRecursively(numeral: String): Int = RomanNumeral
            .fromPrefix(numeral)
            ?.let { convertHeadRecursively(numeral.removePrefix(it.name)) + it.decimalValue }
            ?: 0

        private fun convertTailRecursively(numeral: String, decimal: Int = 0): Int = RomanNumeral
            .fromPrefix(numeral)
            ?.let { convertTailRecursively(numeral.removePrefix(it.name), decimal + it.decimalValue) }
            ?: decimal

        private fun convertByLooping(numeral: String): Int {
            var decimal = 0
            var numeralForLooping = numeral

            while (numeralForLooping.isNotEmpty()) {
                RomanNumeral
                    .fromPrefix(numeralForLooping)
                    ?.let {
                        decimal += it.decimalValue
                        numeralForLooping = numeralForLooping.removePrefix(it.name)
                    }
            }
            return decimal
        }
    }

    inner class FromDecimals {

        @Suppress("TooGenericExceptionThrown")
        fun convert(decimal: Int): String {
            val numeralHeadRecursively = convertHeadRecursively(decimal)
            val numeralTailRecursively = convertTailRecursively(decimal)
            val numeralByLooping = convertByLooping(decimal)

            if (!allConversionResultsAreEqual(numeralHeadRecursively, numeralTailRecursively, numeralByLooping)) {
                throw RuntimeException(
                    """There is a bug, as the conversion results differ:
                | numeralHeadRecursively: $numeralHeadRecursively
                | numeralTailRecursively: $numeralTailRecursively
                | numeralByLooping: $numeralByLooping""".trimMargin()
                )
            }

            return numeralHeadRecursively
        }

        private fun convertHeadRecursively(decimal: Int): String =
            RomanNumeral
                .values()
                .reversed()
                .firstOrNull { decimal - it.decimalValue >= 0 }
                ?.let { it.name + convertHeadRecursively(decimal - it.decimalValue) }
                ?: ""

        private fun convertTailRecursively(decimal: Int, numeral: String = ""): String =
            RomanNumeral
                .values()
                .reversed()
                .firstOrNull { decimal - it.decimalValue >= 0 }
                ?.let { convertTailRecursively(decimal - it.decimalValue, numeral + it.name) }
                ?: numeral


        private fun convertByLooping(decimal: Int): String {
            var numeral = ""
            var decimalForLooping = decimal

            RomanNumeral.values().reversed().forEach {
                while (decimalForLooping - it.decimalValue >= 0) {
                    decimalForLooping -= it.decimalValue
                    numeral += it.name
                }
            }
            return numeral
        }
    }

    private fun allConversionResultsAreEqual(vararg results: Any) = results.toSet().size == 1
}
