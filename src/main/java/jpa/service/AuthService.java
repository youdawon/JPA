package jpa.service;

import jpa.database.common.SearchFilter;
import jpa.database.auth.entity.AdminUser;
import jpa.domain.req.CreateAdminUserRequest;
import jpa.domain.req.DeleteAdminUsersRequest;
import jpa.domain.req.GetAdminUsersRequest;
import jpa.domain.req.UpdateAdminUserRequest;
import jpa.domain.res.GetAdminUserResponse;
import jpa.domain.res.GetAdminUsersResponse;
import jpa.exception.list.CustomException;
import jpa.database.auth.entity.QAdminUser;
import jpa.domain.common.ResultCode;
import jpa.exception.list.OkException;
import jpa.util.Consts;
import jpa.util.ModelMapperUtil;
import jpa.util.PaginationUtil;
import org.apache.commons.lang3.StringUtils;
import jpa.database.auth.repository.AdminUserRepository;
import jpa.exception.list.UnauthorizedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Value("${user.password.expiring-day}")
    private int PASSWORD_EXPIRING_DAY;

    @Value("${user.password.salt}")
    private String PASSWD_SALT;

    /**
     * 어드민 목록 조회
     * @param getAdminUsersRequest
     * @return
     */
    public GetAdminUsersResponse getAdminUsers(GetAdminUsersRequest getAdminUsersRequest) {

        SearchFilter searchFilter = new SearchFilter();

        if (Consts.SEARCH_TYPE_ST001.equals(getAdminUsersRequest.getSearchType())) {
            searchFilter.addFilter(QAdminUser.adminUser.userUid.getMetadata().getName(), getAdminUsersRequest.getSearchExtra());
        } else if (Consts.SEARCH_TYPE_ST002.equals(getAdminUsersRequest.getSearchType())) {
            searchFilter.addFilter(QAdminUser.adminUser.userName.getMetadata().getName(), getAdminUsersRequest.getSearchExtra());
        } else if (Consts.SEARCH_TYPE_ST003.equals(getAdminUsersRequest.getSearchType())) {
            searchFilter.addFilter(QAdminUser.adminUser.phoneNumber.getMetadata().getName(), getAdminUsersRequest.getSearchExtra());
        } else if (Consts.SEARCH_TYPE_ST004.equals(getAdminUsersRequest.getSearchType())) {
            searchFilter.addFilter(QAdminUser.adminUser.userType.getMetadata().getName(), getAdminUsersRequest.getSearchExtra());
        } else if (Consts.SEARCH_TYPE_ST005.equals(getAdminUsersRequest.getSearchType())) {
            searchFilter.addFilter(QAdminUser.adminUser.userStatus.getMetadata().getName(), getAdminUsersRequest.getSearchExtra());
        }

        Sort sort = null;

        if (StringUtils.isEmpty(getAdminUsersRequest.getSort()) || Consts.SEARCH_SORT_TYPE_REG_DT_DESC.equals(getAdminUsersRequest.getSort())) {
            sort = Sort.by(QAdminUser.adminUser.regDt.getMetadata().getName()).descending();
        } else if (Consts.SEARCH_SORT_TYPE_USER_UID_ASC.equals(getAdminUsersRequest.getSort())) {
            sort = Sort.by(QAdminUser.adminUser.userUid.getMetadata().getName()).ascending();
        }

        int offSet = PaginationUtil.getOffSet(getAdminUsersRequest.getCursor());

        PageRequest pageRequest = PageRequest.of(offSet, getAdminUsersRequest.getCount(), sort);

        Page<AdminUser> adminUserPage = adminUserRepository.findAllForPagination(searchFilter, pageRequest);

        GetAdminUsersResponse adminUsersResponse = new GetAdminUsersResponse();
        adminUsersResponse.setTotalCount(adminUserPage.getTotalElements());
        adminUsersResponse.setUsers(ModelMapperUtil.entityListToDto(adminUserPage.getContent(), GetAdminUserResponse.class));

        return adminUsersResponse;
    }

    /**
     * 어드민 정보 등록
     * @param createAdminUserRequest
     * @return
     * @throws CustomException
     */
    @Transactional
    public void createAdminUser(CreateAdminUserRequest createAdminUserRequest) throws CustomException {

        AdminUser adminUser = ModelMapperUtil.map(createAdminUserRequest, AdminUser.class);

        if (adminUserRepository.findById(adminUser.getUserUid()).isPresent()){
            throw new OkException(ResultCode.OK_EXISTS);
        }

        adminUser.setUserType(Consts.USER_TYPE_ADMIN);
        adminUser.setUserStatus(Consts.USER_STATUS_STAND_BY);
        //TODO : 패스워드 암호화 필요
        adminUser.setUserPasswd(createAdminUserRequest.getPasswd());
        adminUser.setPasswdExpireDt(getPasswdExpireDt());
        adminUser.setPasswdFailCnt(0L);
        adminUser.setLoginDt(Timestamp.valueOf(LocalDateTime.now()));

        adminUserRepository.save(adminUser);
    }

    /**
     * 어드민 정보 수정
     * @param updateAdminUserRequest
     * @return
     * @throws CustomException
     */
    @Transactional
    public void updateAdminUser(UpdateAdminUserRequest updateAdminUserRequest) throws CustomException {

        AdminUser existAdminUser = adminUserRepository.findById(updateAdminUserRequest.getUid())
                .orElseThrow(() -> new UnauthorizedException(ResultCode.UNAUTHORIZED_NOT_REGISTED_UID));


        //수정 내용이 없는 경우 성공 처리.
        if (StringUtils.isEmpty(updateAdminUserRequest.getType())
            && StringUtils.isEmpty(updateAdminUserRequest.getUserName())
            && StringUtils.isEmpty(updateAdminUserRequest.getNickname())
            && StringUtils.isEmpty(updateAdminUserRequest.getPhoneNumber())
            && StringUtils.isEmpty(updateAdminUserRequest.getCompanyName())
            && StringUtils.isEmpty(updateAdminUserRequest.getEmail())
            && StringUtils.isEmpty(updateAdminUserRequest.getStatus())) {
        } else {
            AdminUser adminUser = ModelMapperUtil.map(existAdminUser, AdminUser.class);
            adminUser.setUserUid(updateAdminUserRequest.getUid());
            adminUser.setUserName(updateAdminUserRequest.getUserName());
            adminUser.setUserNickname(updateAdminUserRequest.getNickname());
            adminUser.setPhoneNumber(updateAdminUserRequest.getPhoneNumber());
            adminUser.setCompanyName(updateAdminUserRequest.getCompanyName());
            adminUser.setUserEmail(updateAdminUserRequest.getEmail());
            adminUserRepository.save(adminUser);
        }
    }

    /**
     * 어드민 삭제 (복수 삭제)
     * @param deleteAdminUsersRequest
     * @throws CustomException
     */
    @Transactional
    public void deleteAdminUsers(DeleteAdminUsersRequest deleteAdminUsersRequest) throws CustomException {
        for (String uid : deleteAdminUsersRequest.getUids()) {
            deleteAdminUser(uid);
        }
    }

    /**
     * 어드민 삭제
     * @param adminId
     * @throws CustomException
     */
    public void deleteAdminUser(String adminId) throws CustomException{
        AdminUser adminUser = new AdminUser();
        adminUser.setUserUid(adminId);
        adminUser.setUserStatus(Consts.USER_STATUS_DELETE);
        adminUserRepository.updateAdminStatus(adminUser);
    }

    // private String encryptPasswd(String passwd) {
    //     return Sha512Cipher.setSHA512(passwd, PASSWD_SALT);
    // }

    private Timestamp getPasswdExpireDt() {
        LocalDateTime nowDt = LocalDateTime.now();
        LocalDateTime expireDt = nowDt.plusDays(PASSWORD_EXPIRING_DAY);
        return Timestamp.valueOf(expireDt);
    }
}
