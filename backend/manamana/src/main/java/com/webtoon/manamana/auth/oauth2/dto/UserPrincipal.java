//package com.webtoon.manamana.auth.oauth2.dto;
//
//import com.webtoon.manamana.entity.user.User;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//
//@Getter
//@Setter
//public class UserPrincipal implements OAuth2User, UserDetails {
//
//    private Long id;
//    private String email;
//    private String nickname;
//    private String profileThumbnailImage;
//    private String age;
//    private String gender;
//    private Map<String, Object> attribute;
//    private Collection<? extends GrantedAuthority> authorities;
//
//    public UserPrincipal(Long id,String email, Collection<? extends GrantedAuthority> authorities){
//        this.id = id;
//        this.email = email;
//        this.authorities = authorities;
//    }
//
//    public static UserPrincipal create(User user) {
//        List<GrantedAuthority> authorities =
//                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
//        return new UserPrincipal(user.getId(),user.getEmail(),authorities);
//
//    }
//    public static UserPrincipal create(User user, Map<String, Object> attributes) {
//        UserPrincipal userPrincipal = UserPrincipal.create(user);
//        userPrincipal.setAttribute(attributes);
//        return userPrincipal;
//
//    }
//
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//
//    @Override
//    public Map<String, Object> getAttributes() {
//        return this.getAttribute();
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.authorities;
//    }
//
//    @Override
//    public String getName() {
//        return this.nickname;
//    }
//}
