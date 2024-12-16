package com.effective.tms.security.mapper;

import com.effective.tms.common.mapper.Mapper;
import com.effective.tms.security.model.UserAccount;
import com.effective.tms.security.web.model.RegisterRequest;

public interface RegisterRequestToUserAccountMapper extends Mapper<UserAccount, RegisterRequest> {
}
