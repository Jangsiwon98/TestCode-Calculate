package org.example;

import org.assertj.core.util.Streams;
import org.example.calculate.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/*
* 요구사항
* 간단한 사칙연산을 할 수 있다.
* 양수로만 계산할 수 있다.
* 나눗셈에서 0을 나누는경우 illegalArugment 예외를 발생시킨다.
* MVC패턴 기반으로 구현
* */
public class CalculatorTest {

    // 1+2 --------> Calculator
    // 3 <----------
    @DisplayName("덧셈 연산을 수행한다.")
    @Test
    void addtionTest() {
        int result = Calculator.calculator(1, "+", 2);

        assertThat(result).isEqualTo(3);

    }

    @DisplayName("뺄셈 연산을 수행한다.")
    @Test
    void subtractionTest() {
        int result = Calculator.calculator(1, "-", 2);

        assertThat(result).isEqualTo(-1);

    }

    @DisplayName("뺄셈 연산을 수행한다.")
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    void calculateTest(int operand1, String operator, int operand2, int result) {
        int calculatorResult = Calculator.calculator(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));

        assertThat(calculatorResult).isEqualTo(result);

    }

    private static Stream<Arguments> formulaAndResult(){
        return Stream.of(
            arguments(1, "+", 2, 3),
            arguments(1, "-", 2, -1),
            arguments(4, "*", 2, 8),
            arguments(4, "/", 2, 2)
        );
    }

    @DisplayName("나눗셈에서 0을 나누는 경우 예외 발생")
    @Test
    void calculateExceptionTest() {
        assertThatCode(()-> Calculator.calculator(new PositiveNumber(10), "/", new PositiveNumber(0)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("0으로는 나눌 수 없습니다.");

    }
}
