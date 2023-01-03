package com.chalchal.chalchalsever;

import com.chalchal.chalchalsever.global.util.SvcNoUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChalChalSeverApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("@@@@@@@ : " + SvcNoUtils.getSvcNo());
    }

}
