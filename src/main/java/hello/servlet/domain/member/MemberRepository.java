package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용을 고려하여 개발해야 함
*/
public class MemberRepository {
    //싱글톤으로 개발하기 때문에 store, sequence에 static이 붙지 않아도 됨, 이미 하나만 생성되도록 막아놨기 때문
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0;

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }
    //싱글톤으로 개발을 할 때에는 private 로 생성자를 막아야 저장소가 여러개 생성되지 않도록 막을 수 있음
    private MemberRepository(){
    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
