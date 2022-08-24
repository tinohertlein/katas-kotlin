package dev.hertlein.katas.romannumerals

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class RomanNumeralsConverterTest {

    private val converter = RomanNumeralsConverter()

    @Nested
    inner class UnitTests {
        @Test
        fun `should convert empty String`() {
            assertThat(converter.convert("")).isEqualTo(0)
        }

        @ParameterizedTest(name = "should convert {0} to {1}")
        @CsvSource(
            "I,1",
            "II,2",
            "IV,4",
            "V,5",
            "X,10",
            "IX,9",
            "XL,40",
            "L,50",
            "XC,90",
            "C,100",
            "CD,400",
            "D,500",
            "CM,900",
            "M,1000"
        )
        fun `should convert`(numeral: String, decimal: Int) {
            assertThat(converter.convert(numeral)).isEqualTo(decimal)
        }
    }

    @Nested
    inner class AcceptanceTests {

        @ParameterizedTest(name = "should convert {0} to {1}")
        @CsvSource("I,1", "IV,4", "IX,9", "XXIX,29", "LXXX,80", "CCXCIV,294", "MMXIX,2019")
        fun `should convert roman numerals given in examples`(numeral: String, decimal: Int) {
            assertThat(converter.convert(numeral)).isEqualTo(decimal)
        }
    }
}
