package com.effective.tms.security.mapper;

import com.effective.tms.common.mapper.Mapper;
import com.effective.tms.security.model.UserAccount;
import org.springframework.security.core.userdetails.User;

public interface UserAccountToUserMapper extends Mapper<User, UserAccount> {
}
