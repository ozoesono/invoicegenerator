package com.gci.invoice;

import com.gci.invoice.model.SmsCdr;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class BaseTestData {
    public List<SmsCdr> createSmsCdrList() {
        SmsCdr smsCdr1 = SmsCdr.builder()
                .msgId(23)
                .msgLength(2)
                .number("3223")
                .sentDate(LocalDateTime.now())
                .senderId("2323")
                .status("SUCCESS")
                .tenantName("Tenant 1")
                .build();

        SmsCdr smsCdr2 = SmsCdr.builder()
                .msgId(24)
                .msgLength(2)
                .number("3224")
                .sentDate(LocalDateTime.now())
                .senderId("2324")
                .status("SUCCESS")
                .tenantName("Tenant 1")
                .build();

        SmsCdr smsCdr3 = SmsCdr.builder()
                .msgId(25)
                .msgLength(3)
                .number("3225")
                .sentDate(LocalDateTime.now())
                .senderId("2325")
                .status("SUCCESS")
                .tenantName("Tenant 1")
                .build();

        return Arrays.asList(smsCdr1, smsCdr2, smsCdr3);
    }
}
