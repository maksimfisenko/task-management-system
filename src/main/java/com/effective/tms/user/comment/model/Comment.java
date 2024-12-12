package com.effective.tms.user.comment.model;

import com.effective.tms.user.profile.model.UserProfile;
import com.effective.tms.user.task.model.Task;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(schema = "tms", name = "comments")
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String message;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdTimestamp;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant modifiedTimestamp;

    @ManyToOne(optional = false)
    private Task task;

    @ManyToOne(optional = false)
    private UserProfile author;
}
