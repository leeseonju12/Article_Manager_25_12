package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController {
    Scanner sc;

    int lastMemberId = 3;
    List<Member> members;

    public MemberController(Scanner sc) {
        this.sc = sc;
        members = new ArrayList<>();
    }

    public void doJoin() {
        System.out.println("==회원 가입==");
        int id = lastMemberId + 1;
        String loginId = null;
        while (true) {
            System.out.print("로그인 아이디 : ");
            loginId = sc.nextLine().trim();
            if (isJoinableLoginId(loginId) == false) {
                System.out.println("이미 사용중인 loginId");
                continue;
            }
            break;
        }
        String password = null;
        while (true) {
            System.out.print("비밀번호 : ");
            password = sc.nextLine().trim();
            System.out.print("비밀번호 확인: ");
            String passwordConfirm = sc.nextLine().trim();
            if (password.equals(passwordConfirm) == false) {
                System.out.println("비번 확인해");
                continue;
            }
            break;
        }
        System.out.print("이름 : ");
        String name = sc.nextLine().trim();
        String regDate = Util.getNowStr();
        String updateDate = Util.getNowStr();

        Member member = new Member(id, regDate, updateDate, loginId, password, name);
        members.add(member);

        System.out.println(id + "번 회원이 가입 되었습니다.");
        lastMemberId++;
    }

    public void doLogin() {
        System.out.print("아이디: ");
        String loginId = sc.nextLine();
        System.out.print("비밀번호: ");
        String password = sc.nextLine();

        boolean loginSuccess = false;
        for (Member member : members) {
            if (member.getLoginId().equals(loginId) && member.getPassword().equals(password)) { //현재 검사중인 user객체의 아이디와 비밀번호가 맞는 경우
                System.out.println(member.getId() + "번 회원 로그인이 성공되었습니다.\n");
                loginSuccess = true;
                break;
            }
        }
        if (!loginSuccess) {
            // 로그인 실패 시 메시지를 한 번만 출력
            System.out.println("로그인 실패. 아이디나 비밀번호를 다시 입력하세요.\n");
        }
    }

    private boolean isJoinableLoginId(String loginId) {
        for (Member member : members) {
            if (member.getLoginId().equals(loginId)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 회원정보 테스트 데이터 생성
     **/
    public void makeTestUserData() {
        System.out.println("==회원정보 테스트 데이터 생성==");
        members.add(new Member(1, Util.getNowStr(), Util.getNowStr(), "Test1", "test1", "테스트1"));
        members.add(new Member(2, Util.getNowStr(), Util.getNowStr(), "Test2", "test2", "테스트2"));
        members.add(new Member(3, Util.getNowStr(), Util.getNowStr(), "Test3", "test3", "테스트3"));
    }
}
