package demoHello.demo.helloSpring.repository;

//package hello.hellospring.repository;
//
//import hello.hellospring.domain.Member;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.util.List;
//import java.util.Optional;
//
//public class jdbcMemberRepository implements MemberRepository {
//
//    private DataSource dataSource; // spring한테 dataSource 주입 받아야함
//
//    public void JdbcMemberRepository(DataSource dataSource) {
//        this.dataSource = dataSource; //connection 받기가능
//    }
//
//    @Override
//    public Member save(Member member) {
//        String sql = "insert inot member(name) values(?)";
//        //db에서 get connection으로 db connection 가져옴
//        Connection conn = dataSource.getConnection();
//        //sql문으로 갖고오도록 - 현재는 슈도코드
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//        pstmt.setString(1,member.getName());
//
//        pstmt.executeUpdate();
//
//        return null;
//    }
//
//    @Override
//    public Optional<Member> findById(Long id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Member> findByname(String name) {
//        return Optional.empty();
//    }
//
//    @Override
//    public List<Member> findAll() {
//        return null;
//    }
//
//    @Override
//    public void clearStore() {
//
//    }
//
//}
import demoHello.demo.helloSpring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository {
    private final DataSource dataSource;
    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override // 고전스타일 .. 안쓰긴 함
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)"; // 변수보다는 상수로 빼는게 낫긴함
        Connection conn = null; //리소스 다 반환해야해서 null로 되야함 -> 데베 커넥션 안쌓이도록
        PreparedStatement pstmt = null;
        ResultSet rs = null; //결과받기
        try {
            conn = getConnection(); // 커넥션 가져오기
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //RETURN_GENERATED_KEYS : db에insert해서 값 얻을때 필요
            pstmt.setString(1, member.getName());
            pstmt.executeUpdate(); // db에 실제값 업데이트 되서 날라감
            rs = pstmt.getGeneratedKeys(); // 조건 넣어서 맞는 데이터 값 꺼내줌
            if (rs.next()) {
                member.setId(rs.getLong(1)); // 값이 있으면 꺼내고 셋팅함
            } else {
                throw new SQLException("id 조회 실패"); //실패했을때 - 예외발생이 많이 일어나기에 try-catch문 잘 작성해야함
            }
            return member;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    @Override // 조회
    public Optional<Member> findById(Long id) {
        String sql = "insert into member(name) values(?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try { // 커넥션 클로즈
            conn = getConnection();
            pstmt = conn.prepareStatement(sql); // 커넥션 갖고 sql문으로 날리고
            pstmt.setLong(1, id); // id로 조회
            rs = pstmt.executeQuery();
            if(rs.next()) { // 있으면 반환
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        } }


    @Override //id조회와 비슷
    public List<Member> findAll() {
        String sql = "select * from member";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Member> members = new ArrayList<>();
            while(rs.next()) {

                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }
            return members;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

//    @Override
    public void clearStore() {

    }

    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource); //dataSourceUtils통해 dataSource 받아야함 - 유지 위해
    }
    private void close (Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource); // 커넥션 닫을때도 DataSourceUtils 통해서 닫아야함!
    } }

