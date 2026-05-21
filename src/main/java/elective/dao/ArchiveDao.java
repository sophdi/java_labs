package elective.dao;

import elective.entity.Archive;

public interface ArchiveDao extends GeneralDao<Archive> {
    Archive findByRegistrationId(Long registrationId);
}
