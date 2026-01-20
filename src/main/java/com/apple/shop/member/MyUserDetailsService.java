package com.apple.shop.member;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> result = memberRepository.findByUsername(username);
        if(result.isEmpty()){
            throw new UsernameNotFoundException("그런아이디 없음");
        }
        Member user = result.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("일반유저"));

        CustomUser customUser = new CustomUser(user.getUsername(), user.getPassword(), authorities);
        customUser.id = user.getId();
        customUser.displayName = user.getDisplayName();

        return customUser;
    }

}

class CustomUser extends User{

    public Long id;
    public String displayName;

    public CustomUser(String username,
                      @Nullable String password,
                      Collection<? extends GrantedAuthority> authorities
    ) {
        super(username, password, authorities);
    }
}
