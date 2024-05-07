package pack;

import java.util.List;

import domain.SangpumTable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class SangpumCrud {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gojpa"); // factory가 들어가 있으면 싱글톤으로 만듬

		// EntityManager : thread 단위로 작업
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction(); // insert update delete를 하기위해서 필요해

		// 자료 추가
	/*	try {
			transaction.begin(); //출발함 
			SangpumTable sangtab = new SangpumTable(5, "도시락", 3, 6000);
			em.persist(sangtab); // persist로 추가하는 거임
			transaction.commit();
			
		} catch (Exception e) {
			
		} */
		
		
		// 자료수정
	/*	try {
			transaction.begin();
			SangpumTable sangtab = em.find(SangpumTable.class,"5");// 수정할 대상을 먼저 읽어옴 여기선 코드5번임
			if(sangtab == null) {
				System.out.println("해당 자료 없음");
			}else {
				String newName = "마스크";
				sangtab.setSang(newName); // 이게 업데이트 set에다가 주면 되
				transaction.commit();		
			}
			
		} catch (Exception e) {
			System.out.println("update err:" + e);
			transaction.rollback();
			return;
		} */
		
		//자료삭제
		try {
			transaction.begin();
			
			int findCode = 5; // 코드5번 찾아서 삭제하려고 함
			SangpumTable sangtab = em.find(SangpumTable.class, findCode); // 자료를 먼저 읽어옴
			
			if(sangtab == null) {
				System.out.println("해당 자료 없음");
				transaction.rollback();
			}else {
				em.remove(sangtab); // 삭제
				System.out.printf("자료 삭제 됨 : %s", findCode);
				transaction.commit();		
			}
			
		} catch (Exception e) {
			System.out.println("delete err:" + e);
			transaction.rollback();
			return;
		}
		
		
		// JPA를 사용한 DML 처리
		try {
			System.out.println("전체 자료 읽기1");
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<SangpumTable> query = cb.createQuery(SangpumTable.class);

			// 조회의 시작점을 의미함 root 객체 생성함으로써
			Root<SangpumTable> root = query.from(SangpumTable.class);
			query.select(root);
			List<SangpumTable> resultList = em.createQuery(query).getResultList();

			for (SangpumTable st : resultList) {
				System.out.println(st.getCode() + " " + st.getSang() + " " + st.getSu() + " " + st.getDan());
			}

			System.out.println("\n전체 자료 읽기2");
			// JPQL 사용 :TypedQuery를 이용해서
			/*
			 * TypedQuery<SangpumTable> queryq1 = em.createQuery(
			 * "select s from SangpumTable s", SangpumTable.class); List<SangpumTable> list
			 * = queryq1.getResultList(); // createQuery안 쓰는 이유 여긴 TypedQuery라서
			 */

			// 위에 두줄을 한줄로 표현하면
			List<SangpumTable> list = em.createQuery("select s from SangpumTable s", SangpumTable.class)
					.getResultList();

			for (SangpumTable st : list) {
				System.out.println(st.getCode() + " " + st.getSang() + " " + st.getSu() + " " + st.getDan());
			}

			System.out.println("\n부분 자료 읽기1");
			int findId = 1; // int findId = "1"; 같은말
			SangpumTable sangtab = em.find(SangpumTable.class, findId); // find는 pk 칼럼을 찾는거임
			if (sangtab == null) {
				System.out.println("자료 없음");
			} else {
				System.out.printf("%s %s %s %s\n", sangtab.getCode(), sangtab.getSang(), sangtab.getSu(),
						sangtab.getDan());
			}

			System.out.println("\n부분 자료 읽기2");
			TypedQuery<SangpumTable> typedQuery = em.createQuery(query.where(cb.equal(root.get("sang"), "장갑")));
			List<SangpumTable> resultList2 = typedQuery.getResultList();
			for (SangpumTable sangtab2 : resultList2) {
				System.out.printf("%s %s %s %s\n", sangtab2.getCode(), sangtab2.getSang(), sangtab2.getSu(),
						sangtab2.getDan());

			}

		} catch (Exception e) {
			System.out.println("err :" + e);
		} finally {
			em.close();
			emf.close(); // 필수
	}

	}

}
