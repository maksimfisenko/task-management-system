package com.effective.tms.user.task.api.model;

import com.effective.tms.user.profile.model.UserProfile;

public record TaskApiModel(
        Long id,
        UserProfile author,
        UserProfile executor
) {
}
