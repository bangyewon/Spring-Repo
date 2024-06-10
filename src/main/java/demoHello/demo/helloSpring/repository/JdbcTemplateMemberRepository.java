package demoHello.demo.helloSpring.repository;


import demoHello.demo.helloSpring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    //@Autowired // 생성자가 딱 하나 있으면 스프링 bean으로 등록되면 Autowired 생략가능
    public JdbcTemplateMemberRepository(DataSource dataSource) {//스프링이 dataSource 자동으로 인젝션 해줌
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());
        //위의 코드까지 insert문을 만들어줌
        //executeAndReturnKey를 갖고 key받아 위에 넣게되면서 찾게됨 -> sql문 만들어주는 것
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override // id조회코드를 중복제거해 줄인것
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(),id); //결과를 RowMapper해줘야함
        return result.stream().findAny(); // List로 바꾼것
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(),name); //결과를 RowMapper해줘야함
        return result.stream().findAny(); // List로 바꾼것
    }

    @Override
    public List<Member> findAll() {
        //jdbc에서 쿼리 날리고 해당 값 RowMapper에서 받아서 바환함
        return jdbcTemplate.query("select * from member", memberRowMapper()); //결과를 RowMapper해줘야함

    }

//    @Override
    public void clearStore() {

    }

    //생성에 대한건 RowMapper에서 멤버가 생성이되어서 넘어옴 / 콜백해줌
    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> { // option 엔터로 lambda식으로 바꾸기
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}

