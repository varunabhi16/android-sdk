package io.hasura.sdk.auth.service;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import io.hasura.sdk.core.HasuraException;
import io.hasura.sdk.auth.request.ChangeEmailRequest;
import io.hasura.sdk.auth.request.ChangeMobileRequest;
import io.hasura.sdk.auth.request.ChangePasswordRequest;
import io.hasura.sdk.auth.request.ChangeUserNameRequest;
import io.hasura.sdk.auth.request.CheckPasswordRequest;
import io.hasura.sdk.auth.request.DeleteAccountRequest;
import io.hasura.sdk.auth.response.ChangeEmailResponse;
import io.hasura.sdk.auth.response.ChangeMobileResponse;
import io.hasura.sdk.auth.response.ChangePasswordResponse;
import io.hasura.sdk.auth.response.ChangeUserNameResponse;
import io.hasura.sdk.auth.response.CheckPasswordResponse;
import io.hasura.sdk.auth.response.DeleteAccountResponse;
import io.hasura.sdk.auth.response.GetCredentialsResponse;
import io.hasura.sdk.auth.response.LogoutResponse;
import io.hasura.sdk.core.Call;
import io.hasura.sdk.core.HasuraConfig;
import io.hasura.sdk.core.HasuraTokenInterceptor;

/**
 * Created by jaison on 31/05/17.
 */

public class HasuraUserService extends HasuraHttpService {

    public HasuraUserService(HasuraTokenInterceptor tokenInterceptor) {
        super(HasuraConfig.BASE_URL.AUTH, tokenInterceptor);
    }

    /**
     * Returns credentials of the logged in user
     * <p>
     *     This method can be used to retrieve Hasura credentials for the current logged in user.
     *     Hasura credentials include "Hasura Id", "Hausura Role" and "Session Id". This method can
     *     also be used to check if the user has an existing session (or logged in basically). If
     *     not logged in, it will throw an {@link HasuraException}.
     * </p>
     *
     * @return {@link GetCredentialsResponse}
     * @throws HasuraException
     */
    public Call<GetCredentialsResponse, HasuraException> getCredentials() {
        Type respType = new TypeToken<GetCredentialsResponse>() {
        }.getType();
        return makeGetCall("/user/account/info", respType);
    }

    /**
     * Change user's password
     * <p>
     *     This method takes a {@link ChangePasswordRequest} object, which should contain the
     *     current password and the new password.
     * </p>
     *
     * @param r {@link ChangePasswordRequest}
     * @return  {@link ChangePasswordResponse}
     * @throws HasuraException
     */
    public Call<ChangePasswordResponse, HasuraException> changePassword(ChangePasswordRequest r) {
        String jsonBody = gson.toJson(r);
        Type respType = new TypeToken<ChangePasswordResponse>() {
        }.getType();
        return makePostCall("/user/password/change", jsonBody, respType);
    }

    /**
     * Change user's email address.
     * <p>
     *     Initialize {@link ChangeEmailRequest} with the new email address of the user. This method
     *     will send a verification email to the new email address of the user.
     * </p>
     *
     * @param r {@link ChangeEmailRequest}
     * @return  {@link ChangeEmailResponse}
     * @throws HasuraException
     */
    public Call<ChangeEmailResponse, HasuraException> changeEmail(ChangeEmailRequest r) {
        String jsonBody = gson.toJson(r);
        Type respType = new TypeToken<ChangeEmailResponse>() {
        }.getType();
        return makePostCall("/user/email/change", jsonBody, respType);
    }

    /**
     * Change user's mobile number. This method will send an OTP to the new number of the user.
     *
     * @param r {@link ChangeMobileRequest}
     * @return  {@link ChangeMobileResponse}
     * @throws HasuraException
     */
    public Call<ChangeMobileResponse, HasuraException> changeMobile(ChangeMobileRequest r) {
        String jsonBody = gson.toJson(r);
        Type respType = new TypeToken<ChangeMobileResponse>() {
        }.getType();
        return makePostCall("/user/mobile/change", jsonBody, respType);
    }


    /**
     *
     * @param r
     * @return
     */
    public Call<CheckPasswordResponse, HasuraException> verifyPassword(CheckPasswordRequest r) {
        String jsonBody = gson.toJson(r);
        Type respType = new TypeToken<CheckPasswordResponse>() {
        }.getType();
        return makePostCall("/user/password/verify", jsonBody, respType);
    }


    /**
     * Delete account of the current user
     *
     * @param r {@link DeleteAccountRequest}
     * @return  {@link DeleteAccountResponse}
     * @throws HasuraException
     */
    public Call<DeleteAccountResponse, HasuraException> deleteAccount(DeleteAccountRequest r) {
        String jsonBody = gson.toJson(r);
        Type respType = new TypeToken<DeleteAccountResponse>() {
        }.getType();
        return makePostCall("/user/account/delete", jsonBody, respType);
    }

    /**
     * Change user's username.
     *
     * @param r {@link ChangeUserNameRequest}
     * @return  {@link ChangeUserNameResponse}
     * @throws HasuraException
     */
    public Call<ChangeUserNameResponse, HasuraException> changeUserName(ChangeUserNameRequest r) {
        String jsonBody = gson.toJson(r);
        Type respType = new TypeToken<ChangeUserNameResponse>() {
        }.getType();
        return makePostCall("/user/account/change-username", jsonBody, respType);
    }


    /**
     * Logout a logged-in user.
     *
     * @return a {@link LogoutResponse} type
     * @throws HasuraException
     */
    public Call<LogoutResponse, HasuraException> logout() {
        Type respType = new TypeToken<LogoutResponse>() {
        }.getType();
        return makeGetCall("/user/logout", respType);
    }

}
