package com.milestone.milestone.web.component.user

import com.milestone.milestone.web.dao.entity.User
import com.milestone.milestone.web.dao.repository.UserRepository
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.OAuth2AuthenticationException
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils

@Component
@CompileStatic
class UserRegistryComponent extends DefaultOAuth2UserService {
    @Autowired
    private UserRepository userRepository

    @Override
    OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest)

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User)
        } catch (AuthenticationException ex) {
            throw ex
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause())
        }
    }

    // TODO: Use OAuth2UserInfo custom class for retrieving properties from various providers
    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        Optional<User> userOptional = userRepository.findByProviderId(oAuth2User.getAttributes().get('email') as String)

        if(userOptional.isPresent()) {
            updateUser(userOptional.get(), oAuth2User)
        } else {
            registerUser(oAuth2UserRequest, oAuth2User)
        }

        oAuth2User
    }

    private User registerUser(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2UserInfo) {
       /* User user = new User();

        user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        user.setProviderId(oAuth2UserInfo.getId());
        user.setName(oAuth2UserInfo.getName());
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(user);*/
        null
    }

    private User updateUser(User existingUser, OAuth2User oAuth2UserInfo) {
        /*existingUser.setName(oAuth2UserInfo.getName());
        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(existingUser);*/
        null
    }
}
