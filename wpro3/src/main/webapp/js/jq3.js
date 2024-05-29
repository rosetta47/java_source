$(document).ready(function() { // 1회용 함수
	$('#id_err').hide();
	$('#age_err1').hide();
	$('#age_err2').hide();
	$('#pwd_err1').hide();
	$('#pwd_err2').hide();
	
	$('#btnSend').click(function() {
		// 입력 자료 오류 검사 후 서버로 전송
		
		// id 검사
		let id = $('#userid').val(); // document.querySelector('#userid').value와 같음
		//alert(id);
		if(id.length < 2 || isNaN(id) === false) {
			//alert("에러 메세지");
			$('#id_err').show();
			return false;
		}else{
			$('#id_err').hide();
		}
			
		// age 검사
		let age = $('#age').val();
		if(age.length < 1) {
			//alert("에러 메세지");
			$('#age_err1').show();
			return false;
		}else{
			$('#age_err2').hide();
		}
		
		// age는 숫자만 허용
		/*for(let i=  0; i < age.length; i++) {
		// 방법 01. 한글자씩 추출 후 ASCII CODE 값 얻기
			let data = age.charAt(i).charCodeAt(0);
			//alert(data);
			if(data < 48 || data > 57) {
				$('#age_err02').show();
				return false;
			}else{
				$('#age_err02').hide();
			}
		}*/
		
		// 방법 02. 사용자 정의 함수 작성 후 호출
		/*if(!isPositiveInteger_myfunc(age) || age < 15 || age > 100) {
			('#age_err02').show();
			return false;
		}else{
			('#age_err02').hide();
		}*/
		
		// 방법 03. 사용자 정의 함수(정규표현식 사용) 작성 후 호출
		if(!isValidAge_myfunc(age)) {
		$('#age_err2').show();
			return false;
		}else{
		$('#age_err2').hide();
		}
		
		// 비밀번호 검사
		let pwd1 = $('#pwd1').val();
		if(pwd1.length < 1) {
			$('#pwd1').next().show(); // next() : next sibling을 의미함
			return false;
		}else{
			$('#pwd1').next().hide(); // next(), prev()
		}
		
		let pwd2 = $('#pwd1').val();
		if(pwd1 !== pwd2) {
			$('#pwd_err02').show();
		}else{
			$('#pwd_err02').hide();
		}
		
		// form 태그에서 입력한 자료를 서버 파일로 전송
		$('#signFrm').attr('action', 'jq3.jsp').attr('method', 'post').submit();
	});
});

// 방법 02. 내가 만든 함수
function isPositiveInteger_myfunc(para){
	let num = Number(para);
	return Number.isInteger(num) && num > 0;
}

// 방법 03. 내가 만든 정규표현식 사용 함수
function isValidAge_myfunc(para) {
	// 15 ~ 19, 20~ 99, 100
	let ageRegex = /^(1[5-9]|[2-9][0-9]|100)$/;
	
	 // age가 정규표현식과 일치하는지 테스트한 값 반환
	return ageRegex.test(para);
}
