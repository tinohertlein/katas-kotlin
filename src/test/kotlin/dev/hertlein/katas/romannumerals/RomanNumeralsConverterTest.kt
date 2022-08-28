package dev.hertlein.katas.romannumerals

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

internal class RomanNumeralsConverterTest {

    class ArgumentsForUnitTest : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<Arguments> = Stream.of(
            Arguments.of("", "0"),
            Arguments.of("I", "1"),
            Arguments.of("II", "2"),
            Arguments.of("IV", "4"),
            Arguments.of("V", "5"),
            Arguments.of("X", "10"),
            Arguments.of("IX", "9"),
            Arguments.of("XL", "40"),
            Arguments.of("L", "50"),
            Arguments.of("XC", "90"),
            Arguments.of("C", "100"),
            Arguments.of("CD", "400"),
            Arguments.of("D", "500"),
            Arguments.of("CM", "900"),
            Arguments.of("M", "1000")
        )
    }

    class ArgumentsForAcceptanceTest : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<Arguments> = Stream.of(
            Arguments.of("I", "1"),
            Arguments.of("IV", "4"),
            Arguments.of("IX", "9"),
            Arguments.of("XXIX", "29"),
            Arguments.of("LXXX", "80"),
            Arguments.of("CCXCIV", "294"),
            Arguments.of("MMXIX", "2019")
        )
    }

    private val converter = RomanNumeralsConverter()

    @Nested
    inner class ToDecimals {

        @Nested
        inner class UnitTests {

            @ParameterizedTest(name = "from {0} to {1}")
            @ArgumentsSource(ArgumentsForUnitTest::class)
            fun `should convert`(numeral: String, decimal: Int) {
                assertThat(converter.toDecimals(numeral)).isEqualTo(decimal)
            }
        }

        @Nested
        inner class AcceptanceTests {

            @ParameterizedTest(name = "from {0} to {1}")
            @ArgumentsSource(ArgumentsForAcceptanceTest::class)
            fun `should convert arguments in examples`(numeral: String, decimal: Int) {
                assertThat(converter.toDecimals(numeral)).isEqualTo(decimal)
            }
        }
    }

    @Nested
    inner class FromDecimals {

        @Nested
        inner class UnitTests {

            @ParameterizedTest(name = "from {1} to {0}")
            @ArgumentsSource(ArgumentsForUnitTest::class)
            fun `should convert`(numeral: String, decimal: Int) {
                assertThat(converter.fromDecimals(decimal)).isEqualTo(numeral)
            }
        }

        @Nested
        inner class AcceptanceTests {

            @ParameterizedTest(name = "from {1} to {0}")
            @ArgumentsSource(ArgumentsForAcceptanceTest::class)
            fun `should convert arguments in examples`(numeral: String, decimal: Int) {
                assertThat(converter.fromDecimals(decimal)).isEqualTo(numeral)
            }
        }
    }
}
