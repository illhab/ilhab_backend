package com.illhab.illhabServer.jpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.illhab.illhabServer.entity.User;
import com.illhab.illhabServer.repository.UserRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    @DisplayName("정상 유저 회원가입 및 조회 테스트 케이스")
    public void 유저_생성() {
        //given
        User insertUser = userRepository.save(
            User.builder()
                .email("test@test.com")
                .name("TEST")
                .sns_role("kakao")
                .build());

        //when
        User saveUser = userRepository.findById(insertUser.getId())
            .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        //then
        assertThat(insertUser).isEqualTo(saveUser);
    }

    @Test
    @Transactional
    @DisplayName("정상 유저 회원가입 회원 삭제 테스트")
    public void 유저_삭제() {
        //given
        User insertUser = userRepository.save(
            User.builder()
                .email("test@test.com")
                .name("TEST")
                .sns_role("kakao")
                .build());

        //when
        userRepository.deleteById(insertUser.getId());

        //then (예외가 발생해야 성공)
        assertThrows(IllegalArgumentException.class, () -> {
            userRepository.findById(insertUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        });
    }

    @Test
    @Transactional
    @DisplayName("유저 조회 성공")
    public void 유저_조회_성공() {
        //given
        User insertUser = userRepository.save(
            User.builder()
                .email("test@test.com")
                .name("TEST")
                .sns_role("kakao")
                .build());

        //when
        boolean isExistsUser = userRepository.existsByEmail("test@test.com");

        //then
        assertThat(isExistsUser).isTrue();

    }

    @Test
    @Transactional
    @DisplayName("유저 조회 실패")
    public void 유저_조회_실패() {
        //given
        User insertUser = userRepository.save(
            User.builder()
                .email("test@test.com")
                .name("TEST")
                .sns_role("kakao")
                .build());

        //when
        boolean isExistsUser = userRepository.existsByEmail("test111@test.com");

        //then
        assertThat(isExistsUser).isFalse();

    }

    @Test
    @Transactional
    @DisplayName("전체 유저를 조회 성공 테스트 케이스")
    public void 유저_목록_조회_성공() {
        //given
        User user1 = User.builder()
            .email("test@test.com")
            .name("TEST")
            .sns_role("kakao")
            .build();

        User user2 = User.builder()
            .email("test2@test.com")
            .name("TEST")
            .sns_role("kakao")
            .build();

        userRepository.save(user1);
        userRepository.save(user2);

        //when
        List<User> users = userRepository.findAll();

        //then
        assertThat(users.size()).isEqualTo(2);

    }

    @Test
    @Transactional
    @DisplayName("유저 정보 변경 성공 테스트 케이스")
    public void 유저_정보_변경_성공(){
        //given
        User user1 = User.builder()
                .email("test@test.com")
                .name("TEST")
                .sns_role("kakao")
                .build();

        //when
        user1.changeName("change");

        assertThat(user1.getName()).isEqualTo("change");
    }

    @Test
    @Transactional
    @DisplayName("유저 정보 변경 실패 테스트 케이스")
    public void 유저_정보_변경_실패(){
        //given
        User user1 = User.builder()
                .email("test@test.com")
                .name("TEST")
                .sns_role("kakao")
                .build();

        //when
        user1.changeName("change");

        assertThat(user1.getName()).isEqualTo("change22");
    }

}
