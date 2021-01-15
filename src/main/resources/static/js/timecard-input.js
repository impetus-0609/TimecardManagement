// 入力欄をクリックすると発火します.
// クリック時にモーダルを表示させてください.

clickInput = (a) => {
	var element = document.getElementById(a);
	var nen = element.dataset.nen;
	var day = element.dataset.day;
	document.getElementById('test1').innerHTML = nen;
	document.getElementById('test2').innerHTML = day;
	var modal = document.getElementById('modal');
	modal.style.display = 'block';
	
};

closeModai = () => {
	var closeBtn = document.getElementById('closeBtn');
	modal.style.display = 'none';
};