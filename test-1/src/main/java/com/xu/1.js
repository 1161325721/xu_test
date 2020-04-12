var key="⑧@⑨@④@②@③@⑦@⑥@①@⑩@⑤@";
var k="⑧③②④⑨⑩④①⑨⑤⑧";
key=key.split("@").reverse().join("");
k=k.substring(k.length-5,k.length)+k.substring(0,k.length-5);

function f() {
    key=key.split("@").reverse().join("");
    k=k.substring(k.length-5,k.length)+k.substring(0,k.length-5);
    var str='';
    for (var i=k.length-1; i>=0;i--){
        str+=key.indexOf(k.charAt(i));
    }
}

function tel(){return str;}

$('tel').innerHTML = str;$('b_tel').innerHTML ='拨打'+str;
console.log(str);
function call(){window.location = 'tel:'+str;}
function sms(){window.location = 'sms:'+str+'';}