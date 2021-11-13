//$(function(){
//	//id="btnAdd" 인 div 요소의 마지막 요소로써 복사한 데이터를 추가
//	$('#btnAdd').on('click',function(){
//		counter = counter + 1;
//		var varClone = $('.zero:eq(0)').clone();
//		varClone.children("input").eq(0).attr("name","cardList[" + counter + "].no");
//		varClone.children("input").eq(1).attr("name","cardList[" + counter + "].validMonth");
//		$('#divCard ').append(varClone);
//	});
//	//id="btnDel"
//	$('#btnDel').on('click',function(){
//		$('.zero ')
//		.last()
//		.animate({left: '+=150','opacity':0},500,function(){
//			$(this).remove();
//		});
//	});
//	
//});



var counter = 0;

function createInput(){
	//append될 div 부분
	const divCard = document.querySelector('#divCard');
	
	//DIV 부분
	var container = document.createElement('div');
	var cardNoDiv = document.createElement('div');
	var validationDiv = document.createElement('div');
	
	container.setAttribute("id", "con" + counter);
	
	//인풋 부분
	var cardNo = document.createElement('input');
	var validation = document.createElement('input');
	
	cardNo.setAttribute("type", "text");
	cardNo.setAttribute("name", "cardList[" + counter + "].no");
	cardNo.setAttribute("id","no"+counter);
	
	validation.setAttribute("type", "text");
	validation.setAttribute("name", "cardList[" + counter + "].validMonth");
	validation.setAttribute("id","val"+counter);
	
	//라벨부분
	var cardNoLabel = document.createElement('label');
	var validationLabel = document.createElement('label');
	cardNoLabel.setAttribute("for","no" + counter);
	cardNoLabel.innerHTML = "카드" + counter + " - 번호";
	validationLabel.setAttribute("for","val" + counter);
	validationLabel.innerHTML = "카드" + counter + " - 유효연월";
	
	cardNoDiv.append(cardNoLabel);
	cardNoDiv.append(cardNo);
	
	validationDiv.append(validationLabel);
	validationDiv.append(validation);
	
	container.append(cardNoDiv);
	container.append(validationDiv);
	
	divCard.append(container);
	counter = counter + 1;
}

function deleteInput(){
	const lastChild = document.querySelector('#divCard').lastChild;
	if(counter > 0){
		lastChild.remove();
		counter = counter -1;
	}
}