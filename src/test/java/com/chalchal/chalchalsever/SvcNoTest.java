package com.chalchal.chalchalsever;

import com.chalchal.chalchalsever.global.generate.SvcNo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class SvcNoTest {
    @Test
    void getSvcNo() {
        System.out.println(SvcNo.getSvcNo());
    }

    @Test
    void 중복_svc_no_테스트() {
        Map<String, Integer> svcNoMap = new HashMap<>();

        for (int i = 0; i < 100; i++) {
            String svcNo = SvcNo.getSvcNo();

            if(svcNoMap.get(svcNo) != null) {
                int temp = svcNoMap.get(svcNo) + 1;
                svcNoMap.put(svcNo, temp);
            }
            else {
                svcNoMap.put(svcNo, 0);
            }
        }

        for (int i = 0; i < svcNoMap.size(); i++) {
            System.out.println(svcNoMap.values());
        }
    }
}
