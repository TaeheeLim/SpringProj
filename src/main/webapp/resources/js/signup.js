const firstPass = document.querySelector('#password').value;
const secondPass = document.querySelector('#password-check').value;

let idFormat = false;
let idDuplicate = false;
let passwordFormat = false;
let passwordMatch = false;

function checkID(){
	var uid = document.querySelector('#member-id');
    if(!/^[a-zA-Z0-9]{6,20}$/.test(uid.value)) {
    	alert('아이디는 숫자와 영문자 조합으로 6~20자리를 사용해야 합니다.')
    	uid.value = "";
    	uid.focus();
		idFormat = false;
    	return false;
    }
    var checkNum = uid.value.search(/[0-9]/g);
    var checkEng = uid.value.search(/[a-z]/ig);
    
    if(checkNum < 0 || checkEng < 0)
    {
        alert('아이디는 숫자와 영문자를 혼용하여야 합니다.');
        uid.value = "";
        uid.focus();
		idFormat = false;
        return false;
    }
	alert('사용 가능한 아이디 형식입니다.');
	idFormat = true;
}

function checkPassword(){
	var upw = document.querySelector('#password').value;
	var num = upw.search(/[0-9]/g);
	var eng = upw.search(/[a-z]/ig);
	var spe = upw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
	passwordFormat = false;
	if(upw.length < 8 || upw.length > 20){
		alert("8자리 ~ 20자리 이내로 입력해주세요.");
		return;
	}else if(upw.search(/\s/) != -1){
		alert("비밀번호는 공백 없이 입력해주세요.");
		return;
	}else if(num < 0 || eng < 0 || spe < 0 ){
		alert("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
		return;
	}
	passwordFormat = true;
}

function checkpw(){
    if(document.getElementById('password').value !='' && document.getElementById('password-check').value!=''){
        if(document.getElementById('password').value==document.getElementById('password-check').value){
            document.getElementById('pass-notice').innerHTML='비밀번호가 일치합니다.'
            document.getElementById('pass-notice').style.color='blue';
			passwordMatch = true;
        }
        else{
            document.getElementById('pass-notice').innerHTML='비밀번호가 일치하지 않습니다.';
            document.getElementById('pass-notice').style.color='red';
			passwordMatch = false;
        }
	}
}
const idBtn = document.querySelector('#id-check');
const signBtn = document.querySelector('#signBtn');
const emailList = document.querySelector('select[name="email-list"]');
const email2 = document.querySelector('#email2');
const inputId = document.querySelector('#member-id');


emailList.addEventListener('change',function(){
	const naverCheck = document.querySelector('#email-list').value;
		email2.value = naverCheck;
});

idBtn.addEventListener('click', function(){
	checkID();
});

signBtn.addEventListener('click',function(){
	checkPassword();
	console.log(idFormat);
	console.log(idDuplicate);
	console.log(passwordFormat);
	console.log(passwordMatch);
	if((idFormat === true) && (idDuplicate === true) && (passwordFormat === true) && 
		(passwordMatch === true)){
		document.querySelector('#frm').submit();
	} else {
		alert('아이디, 비밀번호 작성 형식을 확인해주세요');
	}
});

//idCheckBtn.addEventListener('click',function(){
//	document.querySelector('#frm').addEventListener('submit')
//});
const idDiv = document.querySelector('#id-div');



inputId.addEventListener('keyup',function(e){
	//const t = e.target.value.trim()
	const inputIdValue = inputId.value.trim();
	$.ajax({
		type: 'post',
		url: 'idCheck.jsp',
		data: {id:inputIdValue},
		success: function(res){
			if(res.trim() === 'YES'){
				idDiv.innerText = '아이디가 있습니다.'
				idDuplicate = false;	
			} else {
				idDiv.innerText = '아이디 사용가능합니다.'
				idDuplicate = true;
			}
		},
		error: function(xhr){
			console.log(xhr.status)
		}
	});
});
