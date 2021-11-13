//메시지 보내기 버튼
var messageBtn= document.querySelector('#messageBtn');
//수정 버튼
var updateBtn = document.querySelectorAll('.updateBtn');
//삭제 버튼
var deleteBtn = document.querySelectorAll('.deleteBtn');
//아이디 입력 인풋
var idInput = document.querySelector('#idInput');
//비밀번호 입력 인풋
var passInput = document.querySelector('#passInput');
//메시지 내용 인풋
var messageInput = document.querySelector('#messageInput');
//수정 확인 버튼
var updateCheck = document.querySelector('#updateCheck');
//수정 취소 버튼
var updateCancel = document.querySelector('#updateCancel');
//원래 Form
var originalFrm = document.querySelector('.originalFrm');
//Hidden 풀리는 수정 Form
var updateFrm = document.querySelector('.updateFrm');
//업데이트 Form의 수정 할 이름 값
var updateIdInput = document.querySelector('#updateIdInput');
//업데이트 Form의 수정할 메시지 값
var updateMessageInput = document.querySelector('#updateMessageInput');
//업데이트 Form의 hidden처리 된 메시지 넘버(기본 키)
var updateMessageNum = document.querySelector('#updateMessageNum');
//업데이트 Form의 비밀번호 입력 Input
var updatePassInput = document.querySelector('#updatePassInput');
//라벨태그 전체 가져오기
var labels = document.querySelectorAll('label');



//수정 버튼 눌렀을때 새로운 Form block처리
updateBtn.forEach(function(target){
	target.addEventListener('click',function(){
		originalFrm.classList.add('hidden');
		updateFrm.classList.remove('hidden');
		var newMessage = target.parentNode.previousElementSibling;
		var newGuest = target.parentNode.previousElementSibling.previousElementSibling;
		var newMessageId = target.parentNode.previousElementSibling.previousElementSibling.previousElementSibling;
		
		updateIdInput.value = newMessage.innerText;
		updateMessageInput.value = newGuest.innerText;
		updateMessageNum.value = newMessageId.innerText
	});
});
//수정 취소 버튼시 List.jsp로 이동
updateCancel.addEventListener('click',function(){
	location.href="/guestbook/list.jsp"
});
//수정 확인 버튼시 cmd=update를 가지고 writeMessage.jsp로 이동
updateCheck.addEventListener('click',function(){
	updateFrm.submit();
});
//삭제 버튼시 writeMessage.jsp로 cmd=delete를 가지고 이동
deleteBtn.forEach(function(target){
	target.addEventListener('click',function(){
		originalFrm.classList.add('hidden');
		var newForm = document.createElement('form');
		newForm.method = 'post';
		newForm.action = '/guestbook/writeMessage.jsp?cmd=delete';
		
		var newPassInput = document.createElement('input');
		newPassInput.setAttribute('type','password');
		newPassInput.setAttribute('name','password');
		newPassInput.setAttribute('placeholder','삭제를 위해 비밀번호를 입력해주세요');
		newPassInput.setAttribute('id','newPassInput');
		
		var newBtn = document.createElement('input');
		newBtn.setAttribute('type','button');
		newBtn.setAttribute('value','확인');
		newBtn.setAttribute('id','newDeleteBtn');
		
		var newMessageIdInput = document.createElement('input');
		newMessageIdInput.setAttribute('id','updateMessageNum2');
		newMessageIdInput.setAttribute('name','messageId');
		newMessageIdInput.setAttribute('class','hidden');
		
		var newMessageId2 = target.parentNode.previousElementSibling.previousElementSibling.previousElementSibling;
		newMessageIdInput.value = newMessageId2.innerText;
		
		newForm.appendChild(newPassInput);
		newForm.appendChild(newBtn);
		newForm.appendChild(newMessageIdInput);
		
		document.body.appendChild(newForm);
		
		var newDeleteBtn = document.querySelector('#newDeleteBtn');
		var newPassInput = document.querySelector('#newPassInput');
		
		newDeleteBtn.addEventListener('click',function(){
			if(newPassInput.value.trim() === ''){
				alert('비밀번호를 입력해주세요');
				return;
			}
			newForm.submit();
		});
	});	
});


