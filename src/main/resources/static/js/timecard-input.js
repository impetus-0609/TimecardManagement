// 入力欄をクリックすると発火します.
// クリック時にモーダルを表示させてください.

clickInput = (a) => {
	var element = document.getElementById(a).value;
	alert(element)
	alert(target)
	document.getElementById("test").value = element
	var aaa = document.getElementById("test");
	aaa.innerHTML = "aaaaa";
	var modal = document.getElementById('modal');
	modal.style.display = 'block';
	
};

closeModai = () => {
	var closeBtn = document.getElementById('closeBtn');
	modal.style.display = 'none';
};