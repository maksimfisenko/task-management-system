package com.effective.tms.user.profile.service.impl;

import com.effective.tms.common.exception.TmsException;
import com.effective.tms.user.profile.model.UserProfile;
import com.effective.tms.user.profile.repository.UserProfileRepository;
import com.effective.tms.user.profile.service.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.effective.tms.common.constants.ServiceConstants.USER_PROFILE_BY_ID_ALREADY_EXISTS;
import static com.effective.tms.common.constants.ServiceConstants.USER_PROFILE_BY_NAME_ALREADY_EXISTS;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public void createUserProfile(UserProfile userProfile) {
        if (userProfileRepository.existsById(userProfile.getId())) {
            throw new TmsException(String.format(USER_PROFILE_BY_ID_ALREADY_EXISTS, userProfile.getId()));
        }
        if (userProfileRepository.existsByUsername(userProfile.getUsername())) {
            throw new TmsException(String.format(USER_PROFILE_BY_NAME_ALREADY_EXISTS, userProfile.getUsername()));
        }
        userProfileRepository.save(userProfile);
    }

    @Override
    public Optional<UserProfile> findUserById(Long id) {
        return userProfileRepository.findById(id);
    }
}
