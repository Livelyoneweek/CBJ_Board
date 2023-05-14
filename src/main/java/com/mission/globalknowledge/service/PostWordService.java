package com.mission.globalknowledge.service;

import com.mission.globalknowledge.dto.PostDto;
import com.mission.globalknowledge.entity.PostWord;
import com.mission.globalknowledge.repository.PostWordRepository;
import com.mission.globalknowledge.util.TxId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class PostWordService {

    private final PostWordRepository postWordRepository;
    private final TxId txId;

    /**
     * Save
     */
    public void save(PostDto.Request.Save postDto, Long postId) {
        log.info("###_{} PostWordService save",txId);
        List<String> words = parsingWord(postDto.getContent());

        List<PostWord> postWords = words.stream().map(word -> new PostWord(word, postId)).toList();
        postWordRepository.saveAll(postWords);
    }

    /**
     * findALl postWord
     */
    public HashMap<Long, List<String>> findAllPostWord() {
        List<Long> postIdList = postWordRepository.findAllPostId();
        HashMap<Long, List<String>> postWordList = new HashMap<>();
        for (Long postId : postIdList) {
            List<String> words = postWordRepository.findAllWordByPostId(postId);
            postWordList.put(postId, words);
        }
        return postWordList;
    }

    /**
     * findAll word
     */
    public List<String> findAllWord(Long postId) {
        return postWordRepository.findAllWordByPostId(postId);
    }

    public List<Long> findRelationPost(Long postId) {

        // 전체 게시글에 WordList 가져옴
        HashMap<Long, List<String>> postWordList = findAllPostWord();

        // 전제 게시글 40% 이상 중복된 String 가져옴
        Set<String> duplicateWord40Up = calculateDuplicateWord(postWordList);

        // 본문의 String[] 가져옴
        List<String> allWord = postWordList.get(postId);

        // 본문의 String[] 에서 40% 이상 중복된 String 계산된 [] 제거함
        List<String> duplicateWord40Down = new ArrayList<>();
        for (String word : allWord) {
            if (!duplicateWord40Up.contains(word)) {
                duplicateWord40Down.add(word);
            }
        }

        // 남은 본문의 String[] 로 postWord 돌면서 단어 2개 이상 중복 한 게시글 id return
        Map<Long, Integer> overlappingWordCount = getOverlappingWordCount(duplicateWord40Down, postWordList);

        // 중복 단어가 높은 순서대로 정렬
        List<Map.Entry<Long, Integer>> sortedMapEntries = new ArrayList<>(overlappingWordCount.entrySet());
        Collections.sort(sortedMapEntries, (o1, o2) -> o2.getValue() - o1.getValue());
        List<Long> postIdList = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : sortedMapEntries) {
            postIdList.add(entry.getKey());
        }

        return postIdList;

    }

    private Map<Long, Integer> getOverlappingWordCount(List<String> duplicateWord40Down, HashMap<Long, List<String>> postWordList) {
        Map<Long, Integer> overlappingWordCountMap = new HashMap<>();
        for (Map.Entry<Long, List<String>> entry : postWordList.entrySet()) {
            List<String> words = entry.getValue();
            int overlappingWordCount = 0;
            for (String word : duplicateWord40Down) {
                if (words.contains(word)) {
                    overlappingWordCount++;
                }
            }
            if (overlappingWordCount >= 2) {
                overlappingWordCountMap.put(entry.getKey(), overlappingWordCount);
            }
        }
        return overlappingWordCountMap;
    }

    private Set<String> calculateDuplicateWord(HashMap<Long, List<String>> postWordList) {

        double duplication_radio = 0.4; // 중복 비율 값 설정

        Set<Long> keySet = postWordList.keySet();

        int keySize = keySet.size();
        double duplicate_value = keySize * duplication_radio; // 40%가 되는 값

        // 전체 게시글을 돌면서 게시글 마다 갖고 있는 단어가 중복되어 나올 경우 +1씩 진행
        Map<String, Integer> countMap = new HashMap<>(); // 중복 횟수를 저장할 Map
        for (Long key : keySet) {
            List<String> wordList = postWordList.get(key); //게시글이 갖고 있는 단어 배열
            for (String word : wordList) {
                countMap.put(word, countMap.getOrDefault(word, 0) + 1);
            }
        }
        
        Set<String> duplicates = new HashSet<>(); // 중복된 문자열을 저장할 Set
        
        // 중복 비율 40% 이상 일 경우만 duplicates 에 단어를 추가함
        Set<String> strings = countMap.keySet();
        for (String str : strings) {
            if (countMap.get(str) > duplicate_value) {
                duplicates.add(str);
            }
        }
        return duplicates;
    }

    /**
     * content -> List<String> words
     */
    private List<String> parsingWord(String content) {
        List<String> words = new ArrayList<>();
        if (content != null && !content.isEmpty()) {
            String[] strings = content.replaceAll("\\s+", " ").trim().split(" ");
            Set<String> uniqueWords = new HashSet<>(Arrays.asList(strings));
            words.addAll(uniqueWords);
        }
        return words;
    }
}
