package com.fighting.store.user.service;

import com.fighting.store.user.common.result.Result;
import com.fighting.store.user.dto.UserRoleMapDto;

public interface UserRoleMapService {
    Result setUserRoleMap(UserRoleMapDto userRoleMapDto);
}
