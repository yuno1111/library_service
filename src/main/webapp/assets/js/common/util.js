function dateValidate(dt){
    var regex = /^\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/g;
    return regex.test(dt);
}

function isEmpty(str, wscheck=true){

    // 아무것도 입력하지 않았는지 체크
    if(str == '') return true;

    // 공백만 입력되었는지 체크
    let whitespace = /^\s+$/g;
    if(whitespace.test(str)) return true;
    if(str.replace(whitespace,'')=="") return true;
    if(str.match(/\s/g)&&wscheck) return true;

    return false;
}

function nameValidate(name) {
    let regex = /^[가-힝]{2,50}$/g;
    return regex.test(name);
}

function phoneNumberValidate(phone) {
    let regex = /[0-9]{9,12}$/;
    return regex.test(phone);
}

function makeDateString(date){
    return date.getFullYear()+"-"+leadingZero(date.getMonth()+1)+"-"+leadingZero(date.getDate())+" "+leadingZero(date.getHours())+":"+leadingZero(date.getMinutes())+":"+leadingZero(date.getSeconds());
}

function leadingZero(n) {
    return n<10?"0"+n:n;
}