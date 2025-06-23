package msa.board.hotarticle.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class TimeCalculatorUtilsTest {

    @Test
    @DisplayName("test")
    void test() {
        //given
        Duration duration = TimeCalculatorUtils.calculateDurationToMidnight();
        System.out.println("duration = " + duration.getSeconds() / 60);
        //when

        //then
    }
}