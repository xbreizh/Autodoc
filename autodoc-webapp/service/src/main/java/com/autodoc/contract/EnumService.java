package com.autodoc.contract;

import java.util.List;

public interface EnumService {

    List<String> getAll(String token, String enumValue);
}
