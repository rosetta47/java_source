function checkAll() { // 장바구니 전체 선택
	if (document.getElementById("all").checked == true) {  //id 를 사용하여 하나의 객체만을 호출
		for (var i = 0; i < 3; i++) document.getElementsByName("checkBox")[i].checked = true;   //name 을 사용하여 배열 형태로 담아 호출
	}
	if (document.getElementById("all").checked == false) {
		for (var i = 0; i < 3; i++) document.getElementsByName("checkBox")[i].checked = false;
	}
}
