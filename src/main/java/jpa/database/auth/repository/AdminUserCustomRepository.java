package jpa.database.auth.repository;

import jpa.database.common.SearchFilter;
import jpa.database.auth.entity.AdminUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AdminUserCustomRepository {

    public Page<AdminUser> findAllForPagination(SearchFilter searchFilter, Pageable pageable);

    @Transactional
    @Modifying
    public void updateAdminStatus(AdminUser adminUser);
}
