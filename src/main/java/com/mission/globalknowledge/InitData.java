package com.mission.globalknowledge;

import com.mission.globalknowledge.entity.Post;
import com.mission.globalknowledge.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
@Slf4j
public class InitData {

    private final PostRepository postRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        log.info("initData start");
        String title1 = "Next.js Server Action 원리가 궁금해요!";
        String content1 = "next에서 발표한 예시를 보니까 서버 함수를 한 파일에 선언해놓고 client 컴포넌트에서 import해서 호출 하던데 어떻게 client 컴포넌트안에서 서버 함수 호출을 할 수 있는건가요? 서버 함수 원리가 뭐에요?";

        String title2 = "톰캣 두개실행시 에러가 납니다";
        String content2 = "톰캣을 두개를 열기위해 기존에있는 톰캣폴더 그대로 복붙 하고 서버.xml에서 포트랑 프로젝트폴더명 변경 후 실행했는데 에러가납니다 왜그런걸까요?";

        String title3 = "안녕하세요 cors로 고통받고 있는 주니어 개발자입니다ㅜㅜ";
        String content3 = "현재 안드로이드 + 리액트로 하이브리드 앱을 개발하고 있습니다. 웹뷰 UI작업 이후에 서버와 Rest API 통신을 하려 합니다. axios를 이용해서 서버와 통신하려고 하는데 SOP로 인한 CORS 에러로 통신을 못하고 있습니다.. 현재 안드로이드에서 사용하고 있는 웹뷰 주소가 192.100.200.300:3000 이라고 가정하고 서버의 주소가 106.100.200.300:4000이라고 가정한다면 어떤 방법으로 통신할 수 있을까요..? 여기저기 알아보니까 서버에서 Access-Control-Allow-Origin을 ‘*’ 등으로 설정하고 웹뷰에서 axios를 실행할 때 header에 Access-Control-Allow-Origin을 추가하면 되는 것 같는데 맞나요?? 현재 서버는 수정할 수가 없어서 웹뷰에서만 수정이 가능한데 방법은 없는건가요ㅠㅜ";

        String title4 = "스프링에서 회사명으로 된 메이븐 라이브러리 사용 관련 질문";
        String content4 = "스프링에서 메이븐 라이브러리를 사용할 때, 일반적으로 pom.xml에 의존성을 추가하면 관련 라이브러리를 가져올 수 있는 것으로 알고 있습니다. 하지만 제가 현재 작업 중인 프로젝트에서는, 공용으로 사용되는 라이브러리 외에도 회사명으로 된 라이브러리가 메이븐에 나타나고, 해당 라이브러리를 import하는 자바 파일이 있습니다. 저는 이렇게 회사명으로 된 라이브러리가 회사에서 직접 만든 라이브러리인 것으로 추측하고 있는데, 제 추측이 맞을까요? 만약 맞다면, 메이븐 라이브러리에 개인이나 회사가 직접 라이브러리를 등록하는 방식이 있는지 궁금합니다.";

        String title5 = "자취방 월세 내는 대신에";
        String content5 = "자취방을 내년 1월에 정리할 예정인데요\n" +
                "자취방 보증금이 300만원입니다. 월세는 30만원이고요\n" +
                "그래서 4월부터 내년 1월까지 총 10개월치 월세를 보증금에서 깎는건 괜찮을까요? 나갈 때 보증금을 안돌려받는다는거죠.\n" +
                "불가능할까요.. 갑자기 큰 돈이 나가버린 상황이어서..";

        String title6 = "월세 자취방 계약 만료 전 퇴실 관련 헷갈리는 게 있어요";
        String content6 = "현재 자취방 계약은 2년전 1월 중순에 했습니다. 1년 계약인데 따로 퇴실 통보 없으면, 추가로 계약서는 작성하지 않고 자동 연장하기로 해서 현재 2년 가량 거주중입니다. 나갈거면 계약 종료 2개월전에 말해달라고 계약을 했습니다.\n" +
                "\n" +
                "여러 상황때문에 급하게 자취방을 옮기려고 하는데, 새로운 집은 전세로 들어가서 11월말에 입주하는거로 계약을 진행할 예정입니다. (가계약금만 입금한 상태)\n" +
                "\n" +
                "그리고 내일 부동산에 이제 퇴실하려고 한다고 말하려고 합니다. 질문은 이겁니다.\n" +
                "\n" +
                "1. 제 경우, 11월말에 이사를 가면 중도 퇴실이 되는 건가요?\n" +
                "2. 11월말까지 다음 세입자를 구하지 못하면, 중계비나 1월달까지의 월세를 부담해야하는 건가요?\n" +
                "3. 새로운 집 입주할때 현재 자취방 보증금이 필요한데, 새로운 세입자를 구하지 못하면 11월말에 보증금을 돌려받지 못하는 건가요?\n" +
                "\n" +
                "처음 자취방 빼보는 거라 모르는게 많습니다.. 도움 주시면 감사하겠습니다..";

        String title7 = "과자중에 맛있는 과자 추천해주세요^^";
        String content7 = "맛있는 과자 5가지 정도만 추천해주세요 ㅎㅎ\n" +
                "\n" +
                "편의점에서 파는거 위주로요\n" +
                "\n" +
                "너무 뻔한거 말구요\n" +
                "(양파링,새우깡, 바나나킥 등등 오래된거) ";

        String title8 = "괜찮은 과자 추천";
        String content8 = "핸드폰하거나 책 읽으면서 생각없이 먹을만한 과자 5개 정도 추천해주세요 맛이나 특징 같은거 있으면 적어주시면 더 도움이 될거 같아요! 먹을수록 계속 까먹을수 있는 과자 5개정도 추천해주세요!(별로 안질리는걸로)";

        postRepository.save(new Post(title1, content1));
        postRepository.save(new Post(title2, content2));
        postRepository.save(new Post(title3, content3));
        postRepository.save(new Post(title4, content4));
        postRepository.save(new Post(title5, content5));
        postRepository.save(new Post(title6, content6));
        postRepository.save(new Post(title7, content7));
        postRepository.save(new Post(title8, content8));

        log.info("initData end");
    }

}
