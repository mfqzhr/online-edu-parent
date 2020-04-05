package com.mfq.edu.service;

import java.util.Map;

public interface MsmService {
    Boolean send(Map<Object, Object> map, String phone);
}
