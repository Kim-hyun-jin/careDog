 function addComma(num){
    var len, point, str; 
       
    num = num + ""; 
    point = num.length % 3 ;
    len = num.length; 
   
    str = num.substring(0, point); 
    while (point < len) { 
        if (str != "") str += ","; 
        str += num.substring(point, point + 3); 
        point += 3; 
    } 
    
    return str;
}

function phoneFormat(phoneNumber) {
  // 특수문자 제거
  const value = phoneNumber.replace(/[^0-9]/g, '');
  
  // 00 OR 000 지정
  const firstLength = value.length > 9 ? 3 : 2;

  // ({2,3}) - ({3,4}) - ({4})
  return [
    // 첫번째 구간 (00 or 000)
    value.slice(0, firstLength),
    // 두번째 구간 (000 or 0000)
    value.slice(firstLength, value.length - 4),
    // 남은 마지막 모든 숫자
    value.slice(value.length - 4),
  ].join('-');
}

function check(){
	if(!$("input:radio[name='size']").is(":checked")){
		alert("애견 사이즈를 선택 해주세요!!");
		return false;
	}	
	if(!$("input:radio[name='gen']").is(":checked")){
		alert("중성화 여부를 체크해주세요!");
		return false;
	}	
}

// 요일구하기
function getDayOfWeek(day){

    const week = ['일', '월', '화', '수', '목', '금', '토'];

    const dayOfWeek = week[new Date(day).getDay()];

    return dayOfWeek;

}

// 일수 차이 구하기
function dayD(st, en) {
		const start = new Date(st);
		const end = new Date(en);
		
		const diffTime = (end.getTime() - start.getTime()) / (1000*60*60*24);
		
		return diffTime;
}