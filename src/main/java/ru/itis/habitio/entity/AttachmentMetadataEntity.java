package ru.itis.habitio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "attachment_metadata")
public class AttachmentMetadataEntity extends AbstractAuditableEntity {

    private String attachmentUri;
}
