package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {

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
	// 리턴할형 없음 insertBoard(BoardDTO boardDTO) 메서드 정의
	public void insertBoard(BoardDTO boardDTO) {
		Connection con=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try {
			//1,2 디비연결 메서드 호출
			con=getConnection();
			// num 구하기
			int num=0;
			// 3. sql  max(num)
			String sql="select max(num) from board";
			pstmt=con.prepareStatement(sql);
			// 4. 실행 => 결과 저장
			rs=pstmt.executeQuery();
			// 5. 결과 다음행 데이터 있으면 max(num)+1 => num 구하기
			if(rs.next()) {
				num=rs.getInt("max(num)")+1;
			}
			//3 sql insert구문 만들기
			sql="insert into board(num,name,pass,subject,content,readcount,date,file) values(?,?,?,?,?,?,now(),?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, boardDTO.getName());
			pstmt.setString(3, boardDTO.getPass());
			pstmt.setString(4, boardDTO.getSubject());
			pstmt.setString(5, boardDTO.getContent());
			pstmt.setInt(6, boardDTO.getReadcount());
			//파일
			pstmt.setString(7, boardDTO.getFile());
			//4 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//마무리 기억장소 해제
			if(rs!=null) try { rs.close(); } catch (Exception e2) {	}
			if(pstmt!=null) try { pstmt.close(); } catch (Exception e2) {	}
			if(con!=null) try { con.close(); } catch (Exception e2) {	}	
		}
	}//
	
	// 리턴할형 List   getBoardList()메서드 정의
	// BoardDTO 여러개를 담을수 있는 자바 배열형태의 내장객체List에 담아서 리턴받아옴
	public List<BoardDTO> getBoardList(int startRow,int pageSize) {
		Connection con=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		// List 객체생성(BoardDTO 여러개를 담을수 있는 자바 배열형태의 내장객체)
		// 처음에는 10개 기억장소 할당 => 11개 부터는 또다른 10개 기억장소 추가 할당
		// List 배열내장객체 값을 저장 .add(DTO주소값) 순서대로 한칸씩 저장 
		// 값을 가져올때 .get(순서) 배열 한칸에 있는 값을 가져오기
		// 배열 크기 .size() 
		
		// 모든형을 배열에 저장 => 업캐스팅 데이터 저장
//		List boardList=new ArrayList();
		// BoardDTO 형만 배열에 저장 => 제네릭(Generic) : 데이터 타입 지정
		List<BoardDTO> boardList=new ArrayList<BoardDTO>();
		try {
			// 1,2 디비연결 메서드 호출
			con=getConnection();
			// 3 sql select 게시판 전체 글 가져오기(최근글 위로 정렬=> 내림차순)
//			String sql="select * from board order by num desc";
//			String sql="select * from board order by num desc limit 시작하는 행번호-1,한페이지 보여줄글개수";
			String sql="select * from board order by num desc limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			// 4 실행 => 결과 저장
			rs=pstmt.executeQuery();
			// 5 while 결과 => 다음행 => 데이터 있으면 열접근
			// => BoardDTO객체생성 set메서드호출 열데이터 저장
			// => 배열 한칸에 게시판글 BoardDTO주소값을 저장  .add(DTO주소값)
			while(rs.next()) {
				//게시판 글 한개를 BoardDTO 저장
				BoardDTO boardDTO=new BoardDTO();
				boardDTO.setNum(rs.getInt("num"));
				boardDTO.setPass(rs.getString("pass"));
				boardDTO.setName(rs.getString("name"));
				boardDTO.setSubject(rs.getString("subject"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setReadcount(rs.getInt("readcount"));
				boardDTO.setDate(rs.getTimestamp("date"));
				//파일
				boardDTO.setFile(rs.getString("file"));
				
				// => 배열 한칸에 게시판글 BoardDTO주소값을 저장  .add(DTO주소값)
//				System.out.println(boardDTO);
				boardList.add(boardDTO);
			}
			      
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//마무리 기억장소 해제
			if(rs!=null) try { rs.close(); } catch (Exception e2) {	}
			if(pstmt!=null) try { pstmt.close(); } catch (Exception e2) {	}
			if(con!=null) try { con.close(); } catch (Exception e2) {	}	
		}
		// List 배열내장객체 주소값을 리턴
		return boardList;
	}//
	
	// 리턴할형 BoardDTO  getBoard(int num)메서드 정의 
	public BoardDTO getBoard(int num) {
		BoardDTO boardDTO=null;
		
		Connection con=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try {
			// 1,2 디비연결 메서드 호출
			con=getConnection();
			// 3 sql select 게시판 num 글 가져오기
			String sql="select * from board where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			// 4 실행 => 결과 저장
			rs=pstmt.executeQuery();
			// 5 while 결과 => 다음행 => 데이터 있으면 열접근
			// => BoardDTO객체생성 set메서드호출 열데이터 저장
			if(rs.next()) {
				//게시판 글 한개를 BoardDTO 저장
				boardDTO=new BoardDTO();
				boardDTO.setNum(rs.getInt("num"));
				boardDTO.setPass(rs.getString("pass"));
				boardDTO.setName(rs.getString("name"));
				boardDTO.setSubject(rs.getString("subject"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setReadcount(rs.getInt("readcount"));
				boardDTO.setDate(rs.getTimestamp("date"));
				//파일
				boardDTO.setFile(rs.getString("file"));
			}
			      
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//마무리 기억장소 해제
			if(rs!=null) try { rs.close(); } catch (Exception e2) {	}
			if(pstmt!=null) try { pstmt.close(); } catch (Exception e2) {	}
			if(con!=null) try { con.close(); } catch (Exception e2) {	}	
		}
		return boardDTO;
	}//
	
	// 리턴할형 없음 updateReadcount(int num)메서드 정의
	public void updateReadcount(int num) {
		Connection con=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try {
			//1,2 디비연결 메서드 호출
			con=getConnection();
			//3 sql update구문 만들기
			String sql="update board set readcount = readcount +1 where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			//4 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//마무리 기억장소 해제
			if(rs!=null) try { rs.close(); } catch (Exception e2) {	}
			if(pstmt!=null) try { pstmt.close(); } catch (Exception e2) {	}
			if(con!=null) try { con.close(); } catch (Exception e2) {	}	
		}
	}
	
	// 리턴할형없음 updateBoard(BoardDTO boardDTO) 메서드 정의 
	// subject content 수정  , 조건 num=?
	public void updateBoard(BoardDTO boardDTO) {
		Connection con=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try {
			//1,2 디비연결 메서드 호출
			con=getConnection();
			//3 sql update구문 만들기
			String sql="update board set subject=?, content=?, file=? where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, boardDTO.getSubject());
			pstmt.setString(2, boardDTO.getContent());
			pstmt.setString(3, boardDTO.getFile());
			pstmt.setInt(4, boardDTO.getNum());
			//4 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//마무리 기억장소 해제
			if(rs!=null) try { rs.close(); } catch (Exception e2) {	}
			if(pstmt!=null) try { pstmt.close(); } catch (Exception e2) {	}
			if(con!=null) try { con.close(); } catch (Exception e2) {	}	
		}
	}
	
	// 리턴할형없음 deleteBoard(int num) 메서드 정의 , 조건 num=?
	public void deleteBoard(int num) {
		Connection con=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try {
			//1,2 디비연결 메서드 호출
			con=getConnection();
			//3 sql update구문 만들기
			String sql="delete from board where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			//4 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//마무리 기억장소 해제
			if(rs!=null) try { rs.close(); } catch (Exception e2) {	}
			if(pstmt!=null) try { pstmt.close(); } catch (Exception e2) {	}
			if(con!=null) try { con.close(); } catch (Exception e2) {	}	
		}
	}
	
	// 리턴할형int  getBoardCount() 메서드 정의  select count(*) from board
	public int getBoardCount() {
		int count=0;
		Connection con=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try {
			// 1,2 디비연결 메서드 호출
			con=getConnection();
			// 3 sql select 게시판 num 글 가져오기
			String sql="select count(*) from board";
			pstmt=con.prepareStatement(sql);
			// 4 실행 => 결과 저장
			rs=pstmt.executeQuery();
			// 5 while 결과 => 다음행 => 데이터 있으면 열접근
			if(rs.next()) {
				count=rs.getInt("count(*)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//마무리 기억장소 해제
			if(rs!=null) try { rs.close(); } catch (Exception e2) {	}
			if(pstmt!=null) try { pstmt.close(); } catch (Exception e2) {	}
			if(con!=null) try { con.close(); } catch (Exception e2) {	}	
		}
		return count;
	}
	
	
}//클래스
