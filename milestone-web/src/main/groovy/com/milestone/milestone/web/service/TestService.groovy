package com.milestone.milestone.web.service

import com.milestone.milestone.proto.Amount
import org.springframework.stereotype.Service

@Service
class TestService {
    void test() {
        Amount.newBuilder().build()
    }
}
