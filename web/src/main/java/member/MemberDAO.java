package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {

	//디비연결 메서드 정의
	public Connection getConnection() throws Exception{
		// throws Exception : 예외처리는 함수호출하는 곳에서 하겠다
		
		// Connection Pool : 미리 디비연결을 서버에서 하고 디비연결 자원을 불러다가 사용
		//                 : 서버에서 하나만 정의하고 자원의 이름을 불러다가 사용, 수정 최소화
		
		Context init=new InitialContext();
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
		Connection con=ds.getConnection();
		return con;
	}
	
	//  public void insertMember(MemberDTO 주소저장변수) 메서드 정의
	public void insertMember(MemberDTO memberDTO) {
		Connection con=null;
		PreparedStatement  pstmt=null;
		try {
			//1,2 디비연결 메서드 호출
			con=getConnection();
			//3 sql insert구문 만들기
			String sql="insert into member(id,pass,name,date,email,address,phone,mobile,postcode,address2) values(?,?,?,now(),?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getPass());
			pstmt.setString(3, memberDTO.getName());
			pstmt.setString(4, memberDTO.getEmail());
			pstmt.setString(5, memberDTO.getAddress());
			pstmt.setString(6, memberDTO.getPhone());
			pstmt.setString(7, memberDTO.getMobile());
			pstmt.setString(8, memberDTO.getPostcode());
			pstmt.setString(9, memberDTO.getAddress2());
			//4 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//마무리 기억장소 해제
			if(pstmt!=null) try { pstmt.close(); } catch (Exception e2) {	}
			if(con!=null) try { con.close(); } catch (Exception e2) {	}	
		}
	}
	
	// 리턴할형 MemberDTO  userCheck(String id,String pass) 메서드 정의
	public MemberDTO userCheck(String id,String pass) {
		MemberDTO memberDTO=null;
		Connection con=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql 
			String sql="select * from member where id=? and pass=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			//4 실행 => 결과 저장
			rs=pstmt.executeQuery();
			//5 결과 다음행 이동 데이터 있으면 MemberDTO 객체생성 set메서드 열접근 값 저장
			if(rs.next()) {
				memberDTO=new MemberDTO();
				memberDTO.setId(rs.getString("id"));
				memberDTO.setPass(rs.getString("pass"));
				memberDTO.setName(rs.getString("name"));
				memberDTO.setDate(rs.getTimestamp("date"));
				memberDTO.setEmail(rs.getString("email"));
				memberDTO.setAddress(rs.getString("address"));
				memberDTO.setPhone(rs.getString("phone"));
				memberDTO.setMobile(rs.getString("mobile"));
				memberDTO.setPostcode(rs.getString("postcode"));
				memberDTO.setAddress2(rs.getString("address2"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try { rs.close(); } catch (Exception e2) {	}
			if(pstmt!=null) try { pstmt.close(); } catch (Exception e2) {	}
			if(con!=null) try { con.close(); } catch (Exception e2) {	}	
		}
		return memberDTO;
	}//
	
	public MemberDTO userCheck(String id) {
		MemberDTO memberDTO=null;
		Connection con=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql 
			String sql="select * from member where id=? and pass=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			//4 실행 => 결과 저장
			rs=pstmt.executeQuery();
			//5 결과 다음행 이동 데이터 있으면 MemberDTO 객체생성 set메서드 열접근 값 저장
			if(rs.next()) {
				memberDTO=new MemberDTO();
				memberDTO.setId(rs.getString("id"));
				memberDTO.setPass(rs.getString("pass"));
				memberDTO.setName(rs.getString("name"));
				memberDTO.setDate(rs.getTimestamp("date"));
				memberDTO.setEmail(rs.getString("email"));
				memberDTO.setAddress(rs.getString("address"));
				memberDTO.setPhone(rs.getString("phone"));
				memberDTO.setMobile(rs.getString("mobile"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try { rs.close(); } catch (Exception e2) {	}
			if(pstmt!=null) try { pstmt.close(); } catch (Exception e2) {	}
			if(con!=null) try { con.close(); } catch (Exception e2) {	}	
		}
		return memberDTO;
	}//
	
	// public 리턴할형 MemberDTO 함수이름 getMember(String id)  메서드 정의
	public MemberDTO getMember(String id) {
		MemberDTO memberDTO=null;
		Connection con=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql 
			String sql="select * from member where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			//4 실행 => 결과 저장
			rs=pstmt.executeQuery();
			//5 결과 다음행 이동 데이터 있으면 MemberDTO 객체생성 set메서드 열접근 값 저장
			if(rs.next()) {
				memberDTO=new MemberDTO();
				memberDTO.setId(rs.getString("id"));
				memberDTO.setPass(rs.getString("pass"));
				memberDTO.setName(rs.getString("name"));
				memberDTO.setDate(rs.getTimestamp("date"));
				memberDTO.setEmail(rs.getString("email"));
				memberDTO.setAddress(rs.getString("address"));
				memberDTO.setPhone(rs.getString("phone"));
				memberDTO.setMobile(rs.getString("mobile"));
				memberDTO.setPostcode(rs.getString("postcode"));
				memberDTO.setAddress2(rs.getString("address2"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try { rs.close(); } catch (Exception e2) {	}
			if(pstmt!=null) try { pstmt.close(); } catch (Exception e2) {	}
			if(con!=null) try { con.close(); } catch (Exception e2) {	}	
		}
		return memberDTO;
	}//
	
	//public void updateMember(MemberDTO 주소저장변수) 메서드 정의
	public void updateMember(MemberDTO updateDTO) {
		Connection con=null;
		PreparedStatement  pstmt=null;
		try {
			//1,2 디비연결 메서드 호출
			con=getConnection();
			//3 sql insert구문 만들기
			String sql="update member set name=?,email=?,address=?,phone=?,mobile=? where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, updateDTO.getName());
			pstmt.setString(2, updateDTO.getEmail());
			pstmt.setString(3, updateDTO.getAddress());
			pstmt.setString(4, updateDTO.getPhone());
			pstmt.setString(5, updateDTO.getMobile());
			pstmt.setString(6, updateDTO.getId());
			//4 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//마무리 기억장소 해제
			if(pstmt!=null) try { pstmt.close(); } catch (Exception e2) {	}
			if(con!=null) try { con.close(); } catch (Exception e2) {	}	
		}
	}
	
	public void deleteMember(String id, String pass) {
		Connection con = null;
		PreparedStatement  pstmt = null;		
		try {
			//1, 2단계 디비연결 메서드 호출
			con = getConnection();

			//3단계  연결정보를 이용해서 sql구문 만들기 =>  PreparedStatement
			// sql 폼에서 입력한 아이디 디비에 아이디 일치하고 ,
//			     폼에서 입력한 비밀번호 디비에  비밀번호 일치
			String sql="delete from member where id=? and pass=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			//4단계   PrepardStatement sql구문 실행 (insert,update,delete)	
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 에러 발생 여부 상관없이 마무리작업 => 내장객체 기억장소 해제
			System.out.println("마무리작업");
			if(pstmt != null) try {	pstmt.close(); } catch (Exception e2) {		}
			if(con != null) try { con.close(); } catch (Exception e2) {		}			
		}
		
	}//
	
}//클래스


