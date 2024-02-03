package com.qfy.modules.city3d.util;

import java.util.UUID;

public class CharacterUtils {
	public String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
