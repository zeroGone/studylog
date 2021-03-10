const content =
    '호이스팅\n' +
    '==========\n' +
    '\n' +
    '- 자바스크립트 Parser가 스크립트를 실행하기 전에 전체를 한 번 훑고\n' +
    '스크립트 내의 변수와 함수를 <u>**최상단에 올려 선언**</u>하는 것을 말한다\n' +
    '\n' +
    '- 실제 메모리에는 변화가 없다\n' +
    '\n' +
    '- 호이스팅의 대상은 var 변수와 함수 선언문이다. let,const 변수와 함수 표현문은 호이스팅되지 않는다.\n' +
    '\n' +
    '```\n' +
    'var value = "Hello World!";\n' +
    '```\n' +
    '위의 코드가 실제 실행되었을 때 이처럼 실행된다\n' +
    '\n' +
    '```\n' +
    'var value;\n' +
    'value = "Hello World!";\n' +
    '```\n' +
    '전자가 실제 실행되었을 때 후자처럼 value 변수가 호이스팅되서 **최상단에 선언** 된다\n' +
    '\n' +
    '- 따라서 후에 선언된 var 변수를 사용했을 경우 **값이 할당이 안되어** undefined 가 반환되지만 let, const 변수는 **선언조차 안되었기 때문에 에러**가 발생한다\n' +
    '\n' +
    '```\n' +
    'console.log(value); // undefined\n' +
    'console.log(letValue); //error!\n' +
    '\n' +
    'var value = "Hello World!";\n' +
    'let letVlaue = "Hello Let";\n' +
    '```\n' +
    '위의 코드가 실제 실행되었을 때 이처럼 실행된다\n' +
    '\n' +
    '```\n' +
    'var value;\n' +
    'console.log(value); //undefined\n' +
    'console.log(letValue);  //error!\n' +
    '\n' +
    'value = "Hello World!";\n' +
    'let letValue = "Hello Let";\n' +
    '```\n' +
    '\n' +
    '---------------------\n' +
    '\n' +
    '- 함수 선언문의 호이스팅\n' +
    '\n' +
    '```\n' +
    'printHello(); // "Hello"\n' +
    '\n' +
    'function printHello(){\n' +
    '  console.log("Hello");\n' +
    '}\n' +
    '```\n' +
    '위의 코드에서 함수는 호이스팅되어 최상단에 선언되기 때문에 실제로는 아래 코드와 같다\n' +
    '```\n' +
    'function printHello(){\n' +
    '  console.log("Hello");\n' +
    '}\n' +
    '\n' +
    'printHello(); // "Hello"\n' +
    '```\n' +
    '\n' +
    '- 하지만 함수 표현문의 경우 호이스팅되지 않는다\n' +
    '\n' +
    '```\n' +
    'printHello(); // error\n' +
    '\n' +
    'var printHello = function(){\n' +
    '  console.log("Hello");\n' +
    '}\n' +
    '```\n' +
    '\n' +
    '위의 코드에서 printHello 함수가 실제로 실행되었을 때는 변수로 선언되기 때문에 함수가 아니라는 에러가 뜬다. 아래 코드는 위 코드가 실제 실행되었을 때이다.\n' +
    '\n' +
    '```\n' +
    'var printHello;\n' +
    '\n' +
    'printHello(); // this is not function!!\n' +
    '\n' +
    'printHello = function(){\n' +
    '  console.log("Hello");\n' +
    '}\n' +
    '```\n' +
    '\n' +
    '\n' +
    '- 함수 선언문과 함수 표현문은 실제 동작하는 것은 다르고, 함수 표현문은 **디버깅 하기가 힘들기 때문에** 충분히 생각하고 사용하자\n' +
    '\n' +
    '-----------\n' +
    '\n' +
    '> 원문\n' +
    '[[JavaScript] 호이스팅(Hoisting)\n' +
    '](https://gmlwjd9405.github.io/2019/04/22/javascript-hoisting.html)\n';


const viewer = toastui.Editor.factory({
    el: document.querySelector('.editor-container'),
    viewer: true,
    height: 'auto',
    initialValue: content
});