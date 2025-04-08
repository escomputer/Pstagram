package com.example.pstagram.domain;

import jakarta.persistence.*;
import lombok.Getter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Base {

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	@LastModifiedDate
	private LocalDateTime updatedAt = LocalDateTime.now();

	private LocalDateTime deletedAt = LocalDateTime.now();

	public void timeWhenDeleted() {
		this.deletedAt = LocalDateTime.now();
	} //삭제 시간 설정용 메서드입니당

}