package domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// sangdata 테이블과 연결

@Entity // JPA를 사용해 테이블과 매핑함 클래스에 붙여주는 이노테이션
@Table(name="sangdata")
public class SangpumTable {
	@Id // pk를 의미
	@Column(name="code") //원본테이블 이름을 써야되
	private int code; // code 이름 바꿔도되 여기서 쓰는 거니까
	@Column(name="sang", nullable = false) //null를 허용하지 않겠다
	private String sang;
	
	private int su;
	
	private int dan;
	
	public SangpumTable() {
		// jpa에서는 생성자 필수임
	}
	
	public SangpumTable(int code,String sang, int su, int dan) {
		// 오버로딩
		this.code=code;
		this.sang=sang;
		this.su=su;
		this.dan=dan;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getSang() {
		return sang;
	}

	public void setSang(String sang) {
		this.sang = sang;
	}

	public int getSu() {
		return su;
	}

	public void setSu(int su) {
		this.su = su;
	}

	public int getDan() {
		return dan;
	}

	public void setDan(int dan) {
		this.dan = dan;
	}
	
	
}
