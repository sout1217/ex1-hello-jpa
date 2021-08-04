package jpabasic.ex1hellojpa;

import jpabasic.ex1hellojpa.domain.Board;
import jpabasic.ex1hellojpa.domain.BoardRepository;
import jpabasic.ex1hellojpa.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitRunner implements ApplicationRunner {


    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {


        Board board = new Board();

        board.setTitle("asdsad");
        board.setContent("casdasd");

        Board save = boardRepository.save(board);

        System.out.println(save);

    }
}
