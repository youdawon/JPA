package jpa.controller;

import jpa.domain.req.CreateAdminUserRequest;
import jpa.domain.req.DeleteAdminUsersRequest;
import jpa.domain.req.GetAdminUsersRequest;
import jpa.domain.req.UpdateAdminUserRequest;
import jpa.exception.list.CustomException;
import jpa.domain.common.ResultCode;
import jpa.domain.res.common.CommonResponse;
import jpa.exception.list.BadRequestException;
import jpa.exception.list.OkException;
import jpa.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    /**
     * 관리자 목록 조회
     * @param request
     * @param getAdminUsersRequest
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/admin")
    public CommonResponse getAdminUsers(HttpServletRequest request,
                                      GetAdminUsersRequest getAdminUsersRequest) throws Exception {

        CommonResponse commonResponse = new CommonResponse();

        try {
            commonResponse.setResult(authService.getAdminUsers(getAdminUsersRequest));
        }catch (Exception e) {
            e.printStackTrace();
            commonResponse.setCode(ResultCode.INTERNAL.getCode());
            throw e;
        }

        return commonResponse;
    }

    /**
     * 관리자 정보 등록
     * @param request
     * @param createAdminUserRequest
     * @param bodyBindingResult
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/admin")
    public CommonResponse createAdminUser(HttpServletRequest request,
                                     @RequestBody @Valid CreateAdminUserRequest createAdminUserRequest,
                                     BindingResult bodyBindingResult) throws Exception {

        CommonResponse commonResponse = new CommonResponse();

        try {

            if (bodyBindingResult.hasErrors()) {
                throw new BadRequestException(ResultCode.BAD_REQUEST_INVALID_PARAM);
            }

            authService.createAdminUser(createAdminUserRequest);

        } catch (OkException oe) {
            commonResponse.setException(oe);
            throw oe;
        } catch (CustomException ce) {
            commonResponse.setException(ce);
            throw ce;
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.setCode(ResultCode.INTERNAL.getCode());
            throw e;
        }
        return commonResponse;
    }

    /**
     * 관리자 정보 삭제 (POST)
     * @param request
     * @param deleteAdminUsersRequest
     * @param bodyBindingResult
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/admin/delete")
    public CommonResponse deleteAdminUsers(HttpServletRequest request,
                                         @RequestBody @Valid DeleteAdminUsersRequest deleteAdminUsersRequest,
                                         BindingResult bodyBindingResult) throws Exception {

        CommonResponse commonResponse = new CommonResponse();

        try {

            if (bodyBindingResult.hasErrors()) {
                throw new BadRequestException(ResultCode.BAD_REQUEST_INVALID_PARAM);
            }

            authService.deleteAdminUsers(deleteAdminUsersRequest);

        } catch (CustomException ce) {
            commonResponse.setException(ce);
            throw ce;
        } catch (Exception e) {
            commonResponse.setCode(ResultCode.INTERNAL.getCode());
            throw e;
        }

        return commonResponse;
    }

    /**
     * 관리자 정보 수정 (POST)
     * @param request
     * @param updateAdminUserRequest
     * @param bodyBindingResult
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/admin/update")
    public CommonResponse updateAdminUser(HttpServletRequest request,
                                        @RequestBody @Valid UpdateAdminUserRequest updateAdminUserRequest,
                                        BindingResult bodyBindingResult) throws Exception {

        CommonResponse commonResponse = new CommonResponse();

        try {

            if (bodyBindingResult.hasErrors()) {
                throw new BadRequestException(ResultCode.BAD_REQUEST_INVALID_PARAM);
            }

            authService.updateAdminUser(updateAdminUserRequest);

        } catch (CustomException ce) {
            commonResponse.setException(ce);
            throw ce;
        } catch (Exception e) {
            commonResponse.setCode(ResultCode.INTERNAL.getCode());
            throw e;
        }

        return commonResponse;
    }
}
