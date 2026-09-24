package com.URI.URL_Shortner.Service.Util;

import com.URI.URL_Shortner.Service.Util.Base62encode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Base62EncoderTest {

    @Test
    void encode_zero_returnsFirstCharacterOfAlphabet() {
        assertEquals("0", Base62encode.encode(0));
    }

    @Test
    void encode_smallNumber_producesExpectedCode() {
        // 61 is the last single-digit value in Base62
        assertEquals("Z", Base62encode.encode(61));
    }

    @Test
    void encode_baseValue_returns10() {
        // 62 in Base62 = 10
        assertEquals("10", Base62encode.encode(62));
    }

    @Test
    void encode_baseValuePlusOne_returns11() {
        // 63 in Base62 = 11
        assertEquals("11", Base62encode.encode(63));
    }

    @Test
    void encode_largeNumber_producesMultiCharacterCode() {
        String result = Base62encode.encode(1_000_000_004L);

        assertNotNull(result);
        assertTrue(result.length() > 1);
    }

    @Test
    void encode_sameInputTwice_producesSameOutput() {
        String first = Base62encode.encode(123456789L);
        String second = Base62encode.encode(123456789L);
        assertEquals(first, second);
    }

    @Test
    void encode_differentInputs_produceDifferentOutputs() {
        String a = Base62encode.encode(100L);
        String b = Base62encode.encode(101L);
        assertNotEquals(a, b);
    }

    @Test
    void encode_negativeNumber_throwsException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Base62encode.encode(-5)
        );
    }

    @Test
    void encode_resultContainsOnlyBase62Characters() {
        String result = Base62encode.encode(123456789L);
        assertTrue(result.matches("[0-9a-zA-Z]+"));
    }
}