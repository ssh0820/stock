package com.zerobase.stock.service;


import com.zerobase.stock.exception.impl.AlreadyExistUserException;
import com.zerobase.stock.model.Auth;
import com.zerobase.stock.persist.MemberRepository;
import com.zerobase.stock.persist.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("couldn't find user -> " + username));
    }

    public MemberEntity register(Auth.SignUp member) {

        // 아이디가 존재하는 경우 exception 발생
        boolean exists = false; // not implemented yet
        exists = memberRepository.existsByUsername(member.getUsername());

        if (exists) {
            throw new AlreadyExistUserException();
        }else{
            String memberName = member.getUsername();
            String encodePw = passwordEncoder.encode(member.getPassword());

            return MemberEntity.builder()
                    .username(memberName)
                    .password(encodePw)
                    .build();
        }

    }

    public MemberEntity authenticate(Auth.SignIn member) {

        // id 로 멤버 조회
        MemberEntity memberEntity = memberRepository.findByUsername(member.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을수 없습니다."));

        // 패스워드 일치 여부 확인
        //      - 일치하지 않는 경우 400 status 코드와 적합한 에러 메시지 반환
        //      - 일치하는 경우, 해당 멤버 엔티티 반환

        if(memberEntity.getPassword().equals(member.getPassword())){
            //400 status 코드값

            // 에러코드
            throw new NotYetImplementedException();
        }else{
            return memberEntity;
        }
    }

}
