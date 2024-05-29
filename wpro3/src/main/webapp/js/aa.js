$(document).ready(function(){
	$('#id_err').hide();
	$('#pwd_err1').hide();
	
	$('#btnSend').click(function(){
		
		let id = $('#userid').val();
		if(id.length < 2 || isNaN(id) === false){
		
			$('#id_err').show();
			return false;
		}else{
			$('#id_err').hide();
		}
	
		//비밀번호 검사
		let pwd1 = $('#pwd1').val();
		if(!pwd1.length <1){
			$('#pwd1').next().show();
			return false;
		}else{
			$('#pwd1').next().hide();
		}

		$("#signFrm").attr('action', 'aa.jsp').attr('method','get').submit();
		
	});
});

function formCheck(para){
	let num = Number(para);
	return Number.isInteger(num) && num > 0;
	event.preventDefault(para); 
}

